package slick.repositories

import java.time.LocalDateTime
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import javax.inject.{Inject, Singleton}
import slick.jdbc.{JdbcProfile, GetResult}
import scala.concurrent.{Future, ExecutionContext}
import slick.models.Todo

@Singleton
class TodoRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ec: ExecutionContext)
extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val query = new TableQuery(tag => new TodoTable(tag))

  /*  DBIO Methods */

  def all(): Future[Seq[Todo]] = db.run(query.result)

  // Tableとのカラムマッピング
  private class TodoTable(_tableTag: Tag)
      extends Table[Todo](_tableTag, Some("slick_practice"), "todo") {

    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    val todo: Rep[String] =
      column[String]("todo", O.Length(120, varying = true))
    val createdAt: Rep[LocalDateTime] = column[LocalDateTime]("created_at")
    val updatedAt: Rep[LocalDateTime] = column[LocalDateTime]("updated_at")

    implicit def GetResultTodo(implicit
        e0: GetResult[Long],
        e1: GetResult[String],
        e2: GetResult[LocalDateTime]
    ): GetResult[Todo] = GetResult { prs =>
      import prs._
      Todo.tupled(
        (Some(<<[Long]), <<[String], <<[LocalDateTime], <<[LocalDateTime])
      )
    }

    def * = (id.?, todo, createdAt, updatedAt) <> (Todo.tupled, Todo.unapply)

    def ? = (
      (
        Rep.Some(id),
        Rep.Some(todo),
        Rep.Some(createdAt),
        Rep.Some(updatedAt)
      )
    ).shaped.<>(
      { r =>
        import r._;
        _1.map(_ => Todo.tupled((Option(_1.get), _2.get, _3.get, _4.get)))
      },
      (_: Any) =>
        throw new Exception("Inserting into ? projection not supported")
    )

  }

}

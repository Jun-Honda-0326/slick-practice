package com.example
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import java.time.{LocalDateTime}
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Todo.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Todo
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param todo Database column todo SqlType(VARCHAR), Length(120,true)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP)
   *  @param updatedAt Database column updated_at SqlType(TIMESTAMP) */
  case class TodoRow(id: Long, todo: String, createdAt: LocalDateTime, updatedAt: LocalDateTime)
  /** GetResult implicit for fetching TodoRow objects using plain SQL queries */
  implicit def GetResultTodoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[LocalDateTime]): GR[TodoRow] = GR{
    prs => import prs._
    TodoRow.tupled((<<[Long], <<[String], <<[LocalDateTime], <<[LocalDateTime]))
  }
  /** Table description of table todo. Objects of this class serve as prototypes for rows in queries. */
  class Todo(_tableTag: Tag) extends profile.api.Table[TodoRow](_tableTag, Some("slick_practice"), "todo") {
    def * = (id, todo, createdAt, updatedAt) <> (TodoRow.tupled, TodoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(todo), Rep.Some(createdAt), Rep.Some(updatedAt))).shaped.<>({r=>import r._; _1.map(_=> TodoRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column todo SqlType(VARCHAR), Length(120,true) */
    val todo: Rep[String] = column[String]("todo", O.Length(120,varying=true))
    /** Database column created_at SqlType(TIMESTAMP) */
    val createdAt: Rep[LocalDateTime] = column[LocalDateTime]("created_at")
    /** Database column updated_at SqlType(TIMESTAMP) */
    val updatedAt: Rep[LocalDateTime] = column[LocalDateTime]("updated_at")
  }
  /** Collection-like TableQuery object for table Todo */
  lazy val Todo = new TableQuery(tag => new Todo(tag))
}

package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{ ControllerComponents, BaseController, Request, AnyContent }
import play.api.data.Form
import play.api.data.Form._
import play.api.i18n.I18nSupport
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import slick.models.Todo
import slick.repositories.TodoRepository

@Singleton
class TodoController @Inject()(
  val controllerComponents: ControllerComponents,
  todoRepository:           TodoRepository
)(implicit ec: ExecutionContext) extends BaseController with I18nSupport {


  // todoの一覧表示
  def list() = Action async { implicit request: Request[AnyContent] =>
    for {
      results <- todoRepository.all()
    } yield {
      Ok(views.html.todo.list(results))
    }
  }















}

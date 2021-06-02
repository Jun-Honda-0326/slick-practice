package slick.models

import java.time.LocalDateTime

case class Todo(
  id:        Option[Long],
  todo:      String,
  createdAt: LocalDateTime = LocalDateTime.now,
  updatedAt: LocalDateTime = LocalDateTime.now
)

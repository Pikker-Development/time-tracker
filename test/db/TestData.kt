package db

import klite.Email
import todos.Todo
import users.Role.USER
import users.User

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User("Test", "User", Email("test@test.ee"), USER)
  val todo = Todo("Buy groceries")
}

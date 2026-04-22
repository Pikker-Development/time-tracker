package db

import klite.Email
import customers.Customer
import project.Project
import todos.Todo
import users.Role.USER
import users.User

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User("Test", "User", Email("test@test.ee"), USER)
  val todo = Todo("Buy groceries")
  val customer = Customer(Id(), "Customer1")
  val project = Project(Id(), customer.id, "Project1", currency = "EUR", hourlyRate = 10.toBigDecimal())
}

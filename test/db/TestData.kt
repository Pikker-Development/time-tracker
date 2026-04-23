package db

import customers.Customer
import klite.Email
import project.Project
import users.Role.USER
import users.User

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User("Test", "User", Email("test@test.ee"), USER)
  val customer = Customer(Id(), "Customer1")
  val project = Project(Id(), customer.id, "Project1", currency = "EUR", hourlyRate = 10.toBigDecimal())
}

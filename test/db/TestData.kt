package db

import customers.Customer
import klite.Email
import project.Project
import users.Role.USER
import users.User
import java.time.LocalDate
import java.time.ZoneOffset.UTC

/** Immutable domain object samples for unit tests */
object TestData {
  val date = LocalDate.of(2025, 3, 3)
  val now = date.atStartOfDay().toInstant(UTC)


  val user = User("Test", "User", Email("test@test.ee"), USER, createdAt = now)
  val customer = Customer(Id(), "Customer1")
  val project = Project(Id(), customer.id, "Project1", currency = "EUR", hourlyRate = 10.toBigDecimal())
}

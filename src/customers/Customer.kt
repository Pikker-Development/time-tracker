package customers

import db.Entity
import db.Id
import klite.Email
import klite.Phone

data class Customer(
  override val id: Id<Customer> = Id(),
  val name: String,
  val legalName: String = "",
  val businessRegistryCode: String = "",
  val vaiId: String ="",
  val invoiceEmail: Email,
  val phone: Phone
): Entity<Customer>

package customers

import db.Entity
import db.Id
import klite.Email
import klite.Phone

data class Customer(
  override val id: Id<Customer> = Id(),
  val name: String,
  val legalName: String? = null,
  val businessRegistryCode: String? = null,
  val vatId: String? = null,
  val invoiceEmail: Email? = null,
  val phone: Phone? = null
): Entity<Customer>

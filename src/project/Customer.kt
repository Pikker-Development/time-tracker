package project

import db.Entity
import db.Id

data class Customer(
  override val id: Id<Customer> = Id(),
  val name: String,
  val legalName: String?,
  val businessRegistryCode: String?,
  val vatId: String?,
  val invoiceEmail: String?,
  val phone: String?,
): Entity<Customer>

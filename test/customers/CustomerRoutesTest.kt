package customers

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.BaseMocks
import db.TestData.customer
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test

class ProjectRoutesTest: BaseMocks() {
  val routes = create<CustomerRoutes>()

  @Test fun create() {
    val newCustomer = routes.create(customer)
    expect(newCustomer).toEqual(customer)
    verify { customerRepository.save(newCustomer) }
  }

  @Test fun list() {
    val allCustomers = listOf(customer)
    every { customerRepository.list() } returns allCustomers
    expect(routes.list()).toEqual(allCustomers)
  }
}

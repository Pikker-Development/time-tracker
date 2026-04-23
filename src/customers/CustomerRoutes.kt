package customers

import auth.Access
import klite.annotations.POST
import users.Role.ADMIN

@Access(ADMIN)
class CustomerRoutes(private val repository: CustomerRepository) {
  @POST fun create(customer: Customer) = repository.save(customer)
}

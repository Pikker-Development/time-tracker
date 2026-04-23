package customers

import auth.Access
import klite.annotations.GET
import klite.annotations.POST
import users.Role.ADMIN

@Access(ADMIN)
class CustomerRoutes(private val repository: CustomerRepository) {
  @GET fun list() = repository.list()
  @POST fun create(customer: Customer) = repository.save(customer)
}

package customers

import auth.Access
import klite.annotations.GET
import users.Role.ADMIN

@Access(ADMIN)
class CustomerRoutes(private val repository: CustomerRepository) {
  @GET("/customers") fun all() = repository.list()
}

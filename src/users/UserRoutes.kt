package users

import auth.Access
import klite.annotations.GET

@Access(isAdmin = true)
class UserRoutes(
  val userRepository: UserRepository
) {
  @GET
  fun list() = userRepository.list()
}

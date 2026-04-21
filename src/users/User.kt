package users

import db.Entity
import db.Id
import klite.Email
import klite.oauth.OAuthUser

enum class Role {
  ADMIN, USER
}

data class User(
  override val firstName: String,
  override val lastName: String,
  override val email: Email,
  val role: Role = Role.ADMIN,
  override val id: Id<User> = Id()
): Entity<User>, OAuthUser

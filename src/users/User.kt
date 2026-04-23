package users

import db.Entity
import db.Id
import klite.Email
import klite.jdbc.UpdatableEntity
import klite.jdbc.nowSec
import klite.oauth.OAuthUser
import java.net.URI
import java.time.Instant

enum class Role {
  ADMIN, USER
}

data class User(
  override val firstName: String,
  override val lastName: String,
  override val email: Email,
  val role: Role = Role.ADMIN,
  val avatarUrl: URI? = null,
  val createdAt: Instant = nowSec(),
  override var updatedAt: Instant? = null,
  override val id: Id<User> = Id()
): Entity<User>, OAuthUser, UpdatableEntity

package project

import customers.Customer
import db.Entity
import db.Id
import klite.jdbc.UpdatableEntity
import klite.jdbc.nowSec
import users.Role
import users.Role.ADMIN
import users.User
import java.time.Instant

data class ProjectMember(
  val projectId: Id<Project>,
  val userId: Id<User>,
  val role: Role = ADMIN,
  override var updatedAt: Instant? = null,
  val createdAt: Instant = nowSec(),
  override val id: Id<ProjectMember> = Id(),
  ): Entity<ProjectMember>, UpdatableEntity

package project

import db.CrudRepository
import db.Id
import klite.jdbc.select
import users.User
import javax.sql.DataSource

class ProjectRepository (db: DataSource): CrudRepository<Project>(db, "projects") {

  fun listForMember(userId: Id<User>): List<Project> =
    db.select("$table join project_members m on $table.id = m.projectId", ProjectMember::userId to userId) { mapper() }
}

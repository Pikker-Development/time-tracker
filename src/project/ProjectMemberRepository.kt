package project

import db.CrudRepository
import db.Id
import klite.jdbc.create
import klite.jdbc.select
import javax.sql.DataSource

class ProjectMemberRepository(db: DataSource): CrudRepository<ProjectMember>(db, "project_members") {

  fun list(projectId: Id<Project>): List<ProjectMemberUser> =
    db.select("$table join users u on userId = u.id", ProjectMember::projectId to projectId) {
      ProjectMemberUser(create(), create("u."))
    }
}

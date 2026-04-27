package project

import db.CrudRepository
import javax.sql.DataSource

class ProjectMemberRepository(db: DataSource): CrudRepository<ProjectMember>(db, "project_members") {
}

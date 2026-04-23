package project

import auth.Access
import users.Role.ADMIN
import klite.annotations.POST

@Access(ADMIN)
class ProjectRoutes(
  val projectRepository: ProjectRepository) {

  @POST fun save(project: Project) : Project {
    projectRepository.save(project)
    return project
  }
}

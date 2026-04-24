package project

import auth.Access
import db.Id
import klite.annotations.GET
import users.Role.ADMIN
import klite.annotations.POST
import klite.annotations.PathParam

@Access(ADMIN)
class ProjectRoutes(
  val projectRepository: ProjectRepository) {

  @GET("/:id")
  fun get(@PathParam id: Id<Project>) = projectRepository.get(id)

  @POST fun save(project: Project) : Project {
    projectRepository.save(project)
    return project
  }

  @GET fun list() = projectRepository.list()

}

package project

import auth.Access
import db.Id
import klite.annotations.AttrParam
import klite.annotations.GET
import users.Role.ADMIN
import klite.annotations.POST
import klite.annotations.PathParam
import users.User

@Access(ADMIN)
class ProjectRoutes(
  val projectRepository: ProjectRepository,
  val projectMemberRepository: ProjectMemberRepository)
{

  @GET("/:id")
  fun get(@PathParam id: Id<Project>) = projectRepository.get(id)

  @POST fun create(@AttrParam user: User, project: Project) : Project {
    projectRepository.save(project)
    projectMemberRepository.save(ProjectMember(project.id, user.id))
    return project
  }

  @POST("/:id")fun save(project: Project, @PathParam id: Id<Project>) :Project {
    require(id == project.id) { "Wrong id" }
    projectRepository.save(project)
    return project
  }

  @GET fun list(@AttrParam user: User) =
  projectRepository.listForMember(user.id)

}

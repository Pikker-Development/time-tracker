package projects

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.BaseMocks
import db.TestData.project
import db.TestData.user
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import project.ProjectRoutes

class ProjectRoutesTest: BaseMocks() {
  val routes = create<ProjectRoutes>()

  @Test fun get() {
    expect(routes.get(project.id)).toEqual(project)
  }

  @Test fun save() {
    val newProject = routes.save(user, project)
    expect(newProject).toEqual(project)
    verify {
      projectRepository.save(newProject)
      projectMemberRepository.save(match {
        it.projectId == project.id && it.userId == user.id
      })
    }
  }

  @Test fun list() {
    val projects = listOf(project)
    every { projectRepository.listForMember(user.id) } returns projects
  }

}

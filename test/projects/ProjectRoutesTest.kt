package projects

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.BaseMocks
import db.TestData.project
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
  val newProject = routes.save(project)
  expect(newProject).toEqual(project)
  verify { projectRepository.save(newProject) }
}

  @Test fun list() {
    val allProjects = listOf(project)
    every { projectRepository.list() } returns allProjects
  }

}

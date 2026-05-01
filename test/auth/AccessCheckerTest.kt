package auth

import db.BaseMocks
import db.TestData.admin
import db.TestData.user
import io.mockk.every
import io.mockk.verify
import klite.ForbiddenException
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import users.AuthRole.ADMIN

class AccessCheckerTest: BaseMocks() {
  val checker = create<AccessChecker>()

  @Test fun `allows public access`() = runTest {
    every { exchange.session["userId"] } returns null
    every { exchange.route.annotations } returns listOf(Public())
    checker.before(exchange)
  }

  @Test fun `forbids unauthorized access`() = runTest {
    every { exchange.session["userId"] } returns null
    assertThrows<ForbiddenException> { checker.before(exchange) }
  }

  @Test fun `access granted`() = runTest {
    every { exchange.session["userId"] } returns user.id.toString()
    every { exchange.route.annotations } returns listOf(Access(user.authRole))
    checker.before(exchange)
    verify {
      exchange.attr("user", user)
      userRepository.setAppUser(user)
    }
  }

  @Test fun `forbids access without matching role`() = runTest {
    every { exchange.session["userId"] } returns user.id.toString()
    every { exchange.route.annotations } returns listOf(Access(ADMIN))
    assertThrows<ForbiddenException> { checker.before(exchange) }
    verify { exchange.attr("user", user) }
  }

  @Test fun `Access annotation overrides Public (eg on class)`() = runTest {
    every { exchange.session["userId"] } returns user.id.toString()
    every { exchange.route.annotations } returns listOf(Public(), Access(ADMIN))
    assertThrows<ForbiddenException> { checker.before(exchange) }
  }

  @Test fun `allows admin access`() = runTest {
    every { exchange.session["userId"] } returns admin.id.toString()
    every { exchange.route.annotations } returns listOf(Access(admin.authRole))
    checker.before(exchange)
    verify { exchange.attr("user", admin) }
  }

  @Test fun `requires @Access annotation`() = runTest {
    every { exchange.session["userId"] } returns user.id.toString()
    assertThrows<IllegalStateException> { checker.before(exchange) }
  }
}

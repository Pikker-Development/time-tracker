package auth

import ch.tutteli.atrium.api.fluent.en_GB.notToEqual
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.BaseMocks
import db.TestData.user
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import klite.Email
import klite.HttpExchange
import klite.NotFoundException
import klite.Session
import klite.smtp.EmailSender
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import users.User

class EmailAuthRoutesTest: BaseMocks() {
  val emailSender = mockk<EmailSender>(relaxed = true)
  val routes = EmailAuthRoutes(userRepository, emailSender)

  val email = Email("test@test.ee")
  val code = "123456"

  val session = Session(mutableMapOf(
    "emailCode" to "$email:$code",
    "codeTime" to (System.currentTimeMillis() / 1000).toString()
  ))

  @Test fun `sendCode stores code in session and sends email`() {
    every { exchange.session } returns session

    routes.sendCode(EmailRequest(email), exchange)

    verify { emailSender.send(email, any()) }
    expect(session["emailCode"]).toEqual("$email:${session["emailCode"]?.substringAfter(":")}")
    expect(session["codeTime"]).notToEqual(null)
  }

  @Test fun `loginWithCode succeeds with valid code`() {
    every { exchange.session } returns session
    every { userRepository.by(User::email to email) } returns user

    val result = routes.loginWithCode(EmailCodeRequest(email, code), exchange)

    expect(result).toEqual(user)
  }

  @Test fun `loginWithCode throws on expired code`() {
    session["codeTime"] = "0"
    every { exchange.session } returns session

    assertThrows<IllegalArgumentException> {
      routes.loginWithCode(EmailCodeRequest(email, code), exchange)
    }
  }

  @Test fun `loginWithCode throws on invalid code`() {
    every { exchange.session } returns session

    assertThrows<IllegalArgumentException> {
      routes.loginWithCode(EmailCodeRequest(email, "000000"), exchange)
    }
  }

  @Test fun `loginWithCode throws when user not found`() {
    every { exchange.session } returns session
    every { userRepository.by(User::email to email) } returns null

    assertThrows<NotFoundException> {
      routes.loginWithCode(EmailCodeRequest(email, code), exchange)
    }
  }
}

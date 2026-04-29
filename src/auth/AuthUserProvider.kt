package auth

import klite.Config
import klite.HttpExchange
import klite.oauth.OAuthTokenResponse
import klite.oauth.OAuthUser
import klite.oauth.OAuthUserProvider
import klite.oauth.UserProfile
import users.User
import users.UserRepository

class AuthUserProvider(
  private val userRepository: UserRepository
): OAuthUserProvider {
  private val admins = Config["INITIAL_ADMINS"].split(",")

  override fun provide(profile: UserProfile, tokenResponse: OAuthTokenResponse, exchange: HttpExchange): OAuthUser {
    var user = userRepository.by(User::email to profile.email)
    if (user == null) {
      val isAdmin = admins.contains(profile.email.toString())
      user = User(profile.firstName, profile.lastName, profile.email, isAdmin, profile.avatarUrl)
      userRepository.save(user)
    } else {
      userRepository.save(user)
    }
    return user
  }
}

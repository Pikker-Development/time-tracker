package auth

import klite.Config
import klite.HttpExchange
import klite.oauth.OAuthTokenResponse
import klite.oauth.OAuthUser
import klite.oauth.OAuthUserProvider
import klite.oauth.UserProfile
import users.Role
import users.User
import users.UserRepository

class AuthUserProvider(
  private val userRepository: UserRepository
): OAuthUserProvider {
  private val ownDomain = Config["OWN_DOMAIN"]

  override fun provide(profile: UserProfile, tokenResponse: OAuthTokenResponse, exchange: HttpExchange): OAuthUser {
    var user = userRepository.by(User::email to profile.email)
    if (user == null) {
      val isAdmin = profile.email.domain == ownDomain
      user = User( profile.firstName, profile.lastName, profile.email, if(isAdmin) Role.ADMIN else Role.USER)
      userRepository.save(user)
    } else {
      userRepository.save(user)
    }
    return user
  }
}

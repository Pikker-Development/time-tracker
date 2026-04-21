import auth.AccessChecker
import auth.AuthUserProvider
import db.initDB
import klite.*
import klite.annotations.annotated
import klite.http.httpClient
import klite.json.JsonBody
import todos.TodoRoutes
import java.nio.file.Path
import kotlin.reflect.full.primaryConstructor
import kotlin.time.Duration.Companion.days
import klite.oauth.GoogleOAuthClient
import klite.oauth.OAuthRoutes
import klite.oauth.OAuthUserProvider

fun main() {
  if (!Config.isProd) Config.useEnvFile()

  Server(
    sessionStore = CookieSessionStore(cookie = Cookie("S", "", httpOnly = true, secure = Config.isProd, maxAge = 365.days)),
    httpExchangeCreator = XForwardedHttpExchange::class.primaryConstructor!!
  ).apply {
    initDB()

    register(httpClient())

    assets("/", AssetsHandler(Path.of("ui/public"), useIndexForUnknownPaths = true))

    context("/oauth") {
      register<OAuthUserProvider>(AuthUserProvider::class)
      register<GoogleOAuthClient>()
      annotated<OAuthRoutes>()
    }

    context("/api") {
      useOnly<JsonBody>()

      post("/js-error") { logger("js-error").error(rawBody) }

      before<AccessChecker>()

      annotated<TodoRoutes>("/todos")
    }
    start()
  }
}

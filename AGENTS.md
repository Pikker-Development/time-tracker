* Prefer simple code
* No semicolons
* Follow existing formatting
* Avoid duplication - extract similar code and reuse
* Backend code is written in Kotlin using Klite framework; code must be short and concise
  * Klite guide: https://raw.githubusercontent.com/keksworks/klite/refs/heads/main/llms.txt
  * Gradle source directories are simplified (just `src` & `test`)
  * In Kotlin, prefer expression body functions
  * Repositories can extend CrudRepository; Repository tests extend DBTest and run on a real database
  * Other tests are pure unit tests
  * In tests, prefer using pre-created entities from `test/db/TestData.kt`; modify only needed fields using .copy()
  * In expectations, also prefer using .copy() and full equality checks instead of field-by-field
  * Always add tests for new backend functions
  * Route handlers are in app module and should be registered in src/app/ApiRoutes.kt
  * For route/service layers tests, extend test/db/BaseMocks.kt to avoid duplicating mock creation and basic setup
  * PostgreSQL database changesets are in `db` directory following file per table structure
* Frontend is in `ui` directory and written using Svelte+TypeScript with TailwindCSS
  * Prefer Svelte 4 syntax (without runes), use event handlers without colons
  * Tests written using vitest; every new component need to have at least one basic render test
  * Backend types are converted to TypeScript interfaces for type safety in frontend using `./gradlew types.ts` and are stored in ui/src/api/types.ts - this is run automatically after compilation.
* Always add translations for all supported languages in `ui/i18n`
* Never commit code yourself, give developer an opportunity to review changes first

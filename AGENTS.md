# ptmshell

Spring Shell 4 CLI app (Spring Boot 4.0.7) that reads CSV files of git repo URLs and runs bash clone/pull commands.

## Build & toolchain

- **Java 25** (toolchain), **Gradle 9.4.1** wrapper
- Use `./gradlew` (alias `g` in README but not in repo — define your own if needed)
- **Format**: `./gradlew spotlessApply` (Google Java Format via Spotless)
- **Test**: `./gradlew test --tests FullClassName.methodName`
  - Single test example: `./gradlew test --tests ReadCSVTest.pullAllTest`
  - All tests: `./gradlew test`
- No CI/CD in this repo

## Structure

Single-module Gradle project under `co.ptm.ptmshell`:
- `PtmshellApplication.java` — Spring Boot entrypoint
- `service/ReadCSV.java` — reads CSV (`@Service`) and runs bash commands via `ProcessBuilder`
- `model/Repo.java` — Lombok `@Data` + `@Builder`; derives directory name from repo URL
- `src/main/resources/*.csv` — three CSV files (spring repos, spring-cloud repos, additional repos)

## Notable quirks

- **Null safety**: `@NullMarked` on both packages via `package-info.java` (JSpecify)
- **Tests** contain hard-coded absolute paths (`/home/b/...`, `/Users/surajgurung/...`) — will not work outside the author's machine without editing
- **`IO.println()`** used in tests with no import — IntelliJ IDEA-specific; not a standard library class
- **Lombok** enabled — `@Slf4j`, `@Data`, `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor`
- **Apache Commons CSV** for parsing `SN,repo` format CSV files

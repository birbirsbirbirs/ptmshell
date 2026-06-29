# ptmshell

Spring Shell 4 CLI app (Spring Boot 4.1.0) that reads CSV files and runs bash clone/pull via `ProcessBuilder`.

## Build

- **Java 25** (Gradle toolchain), **Spring Boot 4.1.0**, **Spring Shell BOM 4.0.2**
- `./gradlew spotlessApply` (Google Java Format) — removes unused imports, trims trailing whitespace, ensures trailing newline
- **Format check runs on every build** — `spotlessJavaCheck` is wired into `check` lifecycle. Use `-x spotlessJavaCheck` to skip.
- `./gradlew test --tests co.ptm.ptmshell.service.ReadCSVTest.pullAllTest`

## Structure

Single module under `co.ptm.ptmshell`:
- `service/ReadCSV.java` — `@Service` with `bashCommand(dir, cmd)` and `readCsv(filePath)`
- `model/Repo.java` — Lombok `@Data @Builder`; `getDirectory()` extracts the last path segment (before `.git`) from the URL
- `src/main/resources/*.csv` — `SN,repo` header, parsed via Apache Commons CSV

## Quirks & gotchas

- **Both main packages are `@NullMarked`** (JSpecify) — nullable fields must be annotated `@Nullable`
- **`IO.println()` in tests** — this is an IntelliJ IDEA internal class. Tests using it will **not compile from the CLI** via Gradle. They only work when run inside IntelliJ with its test runner.
- **`ReadCSV.repoList` is a mutable instance field** — each `readCsv()` call appends to the same list (never cleared). Calling it twice leaks duplicates.
- **Integration tests are `@Disabled`** and contain hard-coded absolute paths (`/home/b/...`, `/Users/...`). They require local git repos and network access.
- **No CI/CD** — no GitHub Actions or other CI config.

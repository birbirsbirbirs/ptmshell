# Copilot Instructions

## Project

This is a Java 25 Spring Boot 4 application using Gradle and Spring Shell. The application entry point is `src/main/java/co/ptm/ptmshell/PtmshellApplication.java`.

## Build, test, and formatting

- Run the application: `.\gradlew.bat bootRun`
- Build and run the complete test task: `.\gradlew.bat test`
- Run one test class: `.\gradlew.bat test --tests "co.ptm.ptmshell.model.RepoTest"`
- Run one test method: `.\gradlew.bat test --tests "co.ptm.ptmshell.service.ReadCSVTest.pullAllTest"`
- Single-test commands:
  - `./gradlew test --tests ReadCSVTest.pullAllTest`
  - `./gradlew test --tests ReadCSVTest.pullAllTestCloud`
  - `./gradlew test --tests ReadCSVTest.pullAllAdditional`
  - `./gradlew test --tests ReadCSVTest.pullAllCraft`
  - `./gradlew test --tests ReadCSVTest.pullAllFloci`
- Check formatting: `.\gradlew.bat spotlessCheck`
- Apply formatting: `.\gradlew.bat spotlessApply`

Gradle 9.4.1 is pinned by the wrapper. Java 25 is required by the Gradle toolchain. Spotless uses Google Java Format and also removes unused imports.

Most `ReadCSVTest` cases are external-repository integration tests: they execute `git clone` or `git pull --all` through Java `ProcessBuilder`. They require Git, network access, and matching clone directories under `<user.home>/repo-clones` before they can run successfully. The CSV resources map repository groups to `repos_spring.csv`, `repos_cloud_spring.csv`, `additional_repos.csv`, `craft.csv`, and `floci.csv`.

## Architecture

- `PtmshellApplication` is the only Spring Boot entry point and has no Spring Shell command implementation yet.
- `model.Repo` represents a CSV row (`SN`, `repo`) plus an optional local directory name. Lombok generates accessors/builders, while `getDirectory()` derives the repository directory from the URL (for example, `spring-framework.git` becomes `spring-framework`) unless an explicit directory was set.
- `service.ReadCSV` is a Spring `@Service`. `readCsv(String file)` uses Apache Commons CSV to append `Repo` records to its instance-level `repoList`; `executeCommand(String dir, String command)` uses Java `ProcessBuilder` and the platform shell (`cmd.exe` on Windows, `sh` on Unix) in the requested working directory.
- The tests exercise both CSV parsing and real Git operations. `RepoTest` is a small model behavior test, and `PtmshellApplicationTests.contextLoads()` verifies Spring startup.

## Code conventions

- Keep Java formatting consistent with Spotless/Google Java Format.
- Packages under `co.ptm.ptmshell.model` and `co.ptm.ptmshell.service` are annotated `@NullMarked` through package `package-info.java`; preserve JSpecify nullness annotations in public APIs.
- CSV readers expect the exact headers `SN,repo`. Preserve that format or update `ReadCSV.readCsv` and the resources together.
- When changing shell or clone behavior, account for the fact that commands are executed through Bash rather than directly through platform-specific Git process APIs.

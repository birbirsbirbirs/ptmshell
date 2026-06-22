# AGENTS.md

This file contains compact, high-signal instructions for OpenCode agents working in this repository.

---

## General

- This is a Java Spring Boot project. The main application entry point is `PtmshellApplication` in `src/main/java/co/ptm/ptmshell/PtmshellApplication.java`.
- There is no existing `AGENTS.md`, CI workflows, linting, or task runners detected in the repository.
- No package manager or environment config files (Maven/Gradle, .env) were found.
- Agents should assume no special configured setup scripts or automated environment preparations.

## Test Commands

- Developer test commands are explicitly documented in `README.md`:
  ```
  g test --tests ReadCSVTest.pullAllTest
  g test --tests ReadCSVTest.pullAllTestCloud
  g test --tests ReadCSVTest.pullAllAdditional
  ```
- These are the fastest way to run specific, high-value test subsets related to repo cloning and pulling.

## Testing Setup and Quirks

- Tests in `ReadCSVTest` rely on cloning and pulling multiple external Git repositories.
- These repos are listed in CSV files located at `src/main/resources/`:
  - `repos_spring.csv` (Spring framework-related repos)
  - `repos_cloud_spring.csv` (Spring Cloud-related repos)
  - `additional_repos.csv` (Other additional repos)
- The tests invoke git commands with absolute local paths, e.g.:
  - `/Users/surajgurung/spring-repos`
  - `/Users/surajgurung/spring-cloud-repos`
  - `/Users/surajgurung/additional-repos`
- Agents must ensure these directories exist and are accessible when running tests to avoid failures.
- The absolute paths suggest that tests assume a specific environment setup external to the project root.

## Working Directory

- Agent operations should assume the project root as the working directory.
- Reads, runs, and git operations in tests depend on relative project paths or absolute paths defined in tests.

## Summary

- Use the documented `g test --tests <TestName>` commands from the README for targeted testing.
- Prepare the local environment by ensuring the referenced directories for cloning/pulling repos exist.
- The project is a Spring Boot app; main class at `PtmshellApplication.java`.
- No automated pipeline or environment config is provided; manual setup is required for repo directories.

---

This guidance is distilled from existing code, tests, and the README. Follow it to avoid common pitfalls related to test setup and repo context.
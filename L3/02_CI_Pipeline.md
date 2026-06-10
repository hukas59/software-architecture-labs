# Continuous Integration (CI) Pipeline

**Project:** `software-architecture-labs` &nbsp;|&nbsp; **Feature:** Group / Ungroup figures (JHotDraw 7)

## What CI is
Continuous Integration is the practice of merging every developer's work into a
shared mainline frequently, where each merge automatically triggers a build and
test run. This catches integration errors early instead of at release time.

## The pipeline
The pipeline is defined by a single GitHub Actions workflow:
`.github/workflows/ci.yml`. It runs automatically on:

- every **push** to the `main` branch, and
- every **pull request** targeting `main`.

### What it does
| Step | Action | Purpose |
|------|--------|---------|
| 1 | `actions/checkout@v4` | Check out the repository on the runner. |
| 2 | `actions/setup-java@v4` (Temurin **JDK 8**, Maven cache) | Provide a JDK that accepts JHotDraw 7's Java 1.6 source level, and cache `~/.m2` for speed. |
| 3 | `mvn -B package --file jhotdraw/jhotdraw7/pom.xml` | Build the JHotDraw 7 Maven module. The `package` goal runs `compile → test → package`, so it both **builds** and **runs the test phase** in one step. |

### Notes on tests
The JHotDraw 7 module (`org.jhotdraw:jhotdraw:7.7.0`) ships **no unit tests**
(`src/test` does not exist) and its `pom.xml` skips the Surefire plugin. The test
phase therefore runs but executes 0 tests. A real change to the Group/Ungroup
feature would add e.g. a `GroupFigureTest` under `src/test/java`, and Surefire
would then execute it automatically through the same `package` step — no workflow
change needed.

### GitHub Packages / `.maven-settings.xml`
The lab mentions creating a `.maven-settings.xml` to pull shared jars from GitHub
Packages. This project does **not** need one: the `jhotdraw7/pom.xml` has no
external dependencies, so there are no private packages to authenticate against.
If a dependency on a GitHub Packages artifact were added, the workflow would pass
`--settings .maven-settings.xml` to Maven and the file would hold the registry
URL plus a `${GITHUB_TOKEN}` server entry.

## Result
Once pushed, the workflow appears under the repository's **Actions** tab and runs
on every push/PR, giving a green/red build status that gates integration.

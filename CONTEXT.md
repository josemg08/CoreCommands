# Project Context: CoreCommands

## Overview
CoreCommands is an Intellij Plugin project to display a toolwindow with a list of buttons to quickly run commonly used terminal commands.

## Directory Structure
- **src/main/kotlin/**: Contains the main Kotlin source code, organized under the `com` package.
- **src/main/resources/**: Contains resources such as icons and metadata (e.g., `META-INF`).
- **build/**: Output directory for compiled classes, JARs, reports, and temporary files generated during the build process.
- **gradle/**: Contains Gradle wrapper files for consistent build tooling.

## Build System
- Uses Gradle with Kotlin DSL (`build.gradle.kts`).
- Supports building, packaging, and possibly instrumenting code (as seen in `instrumented/` and `instrumentCode/`).
- Output JARs are found in `build/libs/`.

## Notable Features
- **Instrumentation**: The presence of `coroutines-javaagent.jar` and `instrumented/` folders suggests support for code instrumentation, possibly for profiling, monitoring, or enhancing runtime behavior.
- **Plugin Support**: The `plugin.xml` and `META-INF` structure indicate that this project may be an IntelliJ Platform plugin or similar JVM-based plugin.
- **OpenTelemetry**: Log files such as `open-telemetry-meters.*.json` suggest integration with OpenTelemetry for metrics and observability.

## Usage
- Build the project using `./gradlew build`.
- Artifacts are generated in `build/libs/`.
- Instrumentation and telemetry features may require additional configuration.
package ar.imagin.corecommands.config

import com.google.gson.GsonBuilder
import com.intellij.openapi.project.Project
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class CommandConfigManager(project: Project) {
    private val configFile = File(project.basePath + "/.idea/CoreCommands.json")
    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun getCommands(): List<CommandConfig> {
        if (!configFile.exists()) {
            createDefaultConfig()
        }
        return try {
            FileReader(configFile).use { reader ->
                gson.fromJson(reader, Array<CommandConfig>::class.java).toList()
            }
        } catch (_: Exception) {
            // If there's any error reading the file, return default commands
            createDefaultConfig()
            getCommands()
        }
    }

    private fun createDefaultConfig() {
        if (!configFile.exists()) {
            val defaultCommands = listOf(
                CommandConfig(
                    buttonText = "Assemble Debug",
                    description = "Builds the debug version of your app (app.debug).",
                    command = "./gradlew assembleDebug"
                ),
                CommandConfig(
                    buttonText = "Build Project",
                    description = "Commands for Android development clean the project.",
                    command = "./gradlew clean"
                ),
                CommandConfig(
                    buttonText = "Run lint checks",
                    description = "Run lint checks.",
                    command = "./gradlew lint"
                )
            )

            configFile.parentFile.mkdirs()
            FileWriter(configFile).use { writer ->
                gson.toJson(defaultCommands, writer)
            }
        }
    }
}
package com.corecommands.views

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

internal class CommandsToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val commandRows = initCommandRows(project)
        val toolWindowContent = CommandsToolWindowContent(commandRows = commandRows)
        val content = ContentFactory.getInstance().createContent(toolWindowContent.contentPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    /**.___
     * Initializes the command rows with the project context.
     * This allows each command to be executed in the context of the current project.
     *
     * param project The current project context.
     * param buttonText The text to display on the button.
     * param description The description of the command to be displayed.
     * param command The command to be executed when the button is clicked.
    __.*/
    private fun initCommandRows(project: Project): List<CommandRow> {
        return listOf(
            CommandRow(
                project = project,
                buttonText = "Reformat",
                description = "Reformats the current file according to code style settings. This can help keep your code clean and consistent with project standards.",
                command = "./local_helpers.sh -f RUN_FORMAT"
            ),
            CommandRow(
                project = project,
                buttonText = "Build Project",
                description = "Build all project sources and resources. Use this to ensure your code builds successfully before running.",
                command = "./gradlew app:assembleGoogleDebug"
            )
        )
    }
}
package ar.imagin.corecommands.views

import ar.imagin.corecommands.config.CommandConfigManager
import ar.imagin.corecommands.terminal.TerminalRunner
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

internal class CommandsToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val commandRows = initCommandRows(project)
        val toolWindowContent = CommandsToolWindowContent(commandRows = commandRows)
        val content = ContentFactory.getInstance().createContent(toolWindowContent.scrollPane, "", false)
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
        val terminalRunner = TerminalRunner(project = project)
        val configManager = CommandConfigManager(project)

        return configManager.getCommands().map { config ->
            CommandRow(
                terminalRunner = terminalRunner,
                buttonText = config.buttonText,
                description = config.description,
                command = config.command
            )
        }
    }
}
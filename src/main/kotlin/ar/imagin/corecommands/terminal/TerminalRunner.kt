package ar.imagin.corecommands.terminal

import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.terminal.ui.TerminalWidget
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

class TerminalRunner(project: Project) {

    private val terminalName = "Core Commands"

    private val terminalToolWindowManager = TerminalToolWindowManager.getInstance(project)
    private var shellWidget: TerminalWidget? = null

    fun getShell() {
        shellWidget = terminalToolWindowManager.createShellWidget(
            null,
            terminalName,
            true,
            true
        )

        shellWidget?.let { setupTerminalCloseListener(it) }
    }

    fun setupTerminalCloseListener(terminalWidget: TerminalWidget) {
        val cleanupLogic = Disposable {
            // This code block runs when the terminalWidget is closed.
            shellWidget = null // Clear the reference to the closed widget
        }

        // Register your cleanup logic. When `terminalWidget` is disposed,
        // `cleanupLogic` will also be disposed, and its lambda will run.
        Disposer.register(terminalWidget, cleanupLogic)
    }

    fun runCommandInTerminal(command: String) {
        if (shellWidget == null) {
            getShell()
        }

        shellWidget?.sendCommandToExecute(command)
    }
}
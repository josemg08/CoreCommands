package com.corecommands.terminal

import com.intellij.openapi.project.Project
import org.jetbrains.plugins.terminal.ShellTerminalWidget
import org.jetbrains.plugins.terminal.TerminalView

class TerminalRunner(private val project: Project) {

    private val terminalName = "Core Commands"

    private val terminalView = TerminalView.getInstance(project)
    private var shell: ShellTerminalWidget? = null

    fun getShell() {
        shell = terminalView.createLocalShellWidget(project.basePath, terminalName)
    }

    fun runCommandInTerminal(command: String) {
        if (shell == null || terminalView.getWidgets().isEmpty()) {
            getShell()
            shell!!.executeCommand(command)
        } else {
            if (shell!!.hasRunningCommands()) {
                // TODO
            } else {
                shell!!.executeCommand(command)
            }
        }
    }
}
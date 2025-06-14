import com.intellij.openapi.project.Project
import org.jetbrains.plugins.terminal.TerminalView

fun runCommandInTerminal(project: Project, command: String) {
    val terminalView = TerminalView.getInstance(project)
    terminalView.createLocalShellWidget(project.basePath, "My Terminal").executeCommand(command)
}
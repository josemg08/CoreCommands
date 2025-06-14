package com.corecommands.views

import com.corecommands.terminal.TerminalRunner
import com.intellij.ui.JBColor
import javax.swing.JLabel

class CommandRow(
    terminalRunner: TerminalRunner,
    buttonText: String,
    description: String,
    command: String
) {
    val button = instanceButton(
        text = buttonText,
        toolTip = description,
        onClick = {
            terminalRunner.runCommandInTerminal(
                command = command
            )
        }
    )

    val descriptionLabel =
        JLabel("<html><div style='margin-left:12px;width:220px;'>$description</div></html>").apply {
            foreground = JBColor(0x787878, 0x787878)
            font = font.deriveFont(13f)
        }
}
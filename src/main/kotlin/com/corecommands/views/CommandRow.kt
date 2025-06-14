package com.corecommands.views

import com.intellij.openapi.project.Project
import com.intellij.ui.JBColor
import runCommandInTerminal
import javax.swing.JLabel

class CommandRow(
    project: Project,
    buttonText: String,
    description: String,
    command: String
) {
    val button = instanceButton(
        text = buttonText,
        toolTip = description,
        onClick = {
            runCommandInTerminal(
                project = project,
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
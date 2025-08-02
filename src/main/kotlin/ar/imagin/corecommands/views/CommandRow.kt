package ar.imagin.corecommands.views

import ar.imagin.corecommands.terminal.TerminalRunner
import com.intellij.ui.JBColor
import javax.swing.JLabel
import javax.swing.JPanel
import java.awt.GridBagLayout
import java.awt.GridBagConstraints

class CommandRow(
    terminalRunner: TerminalRunner,
    buttonText: String,
    description: String,
    command: String,
) {
    val containerPanel = JPanel(GridBagLayout()).apply {
        // Remove any default gaps between components
        (layout as GridBagLayout).rowHeights = intArrayOf(0)
        (layout as GridBagLayout).rowWeights = doubleArrayOf(0.0)
    }

    val button = instanceButton(
        text = buttonText,
        toolTip = description,
        onClick = {
            terminalRunner.runCommandInTerminal(command = command)
        }
    )

    val descriptionLabel = JLabel("<html><div style='margin-left:12px;width:220px;'>$description</div></html>").apply {
        foreground = JBColor(0x787878, 0x787878)
        font = font.deriveFont(13f)
    }

    init {
        val gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.SOUTH  // Align button to bottom of its cell
        gbc.fill = GridBagConstraints.HORIZONTAL
        containerPanel.add(button, gbc)
    }
}
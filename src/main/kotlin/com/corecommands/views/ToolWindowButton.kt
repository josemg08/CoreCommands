package com.corecommands.views

import com.intellij.ui.JBColor
import java.awt.Dimension
import javax.swing.JButton

fun instanceButton(
    text: String,
    toolTip: String,
    onClick: () -> Unit
): JButton {
    val button =
        JButton("<html><div style='width: 100%; margin: 0; padding: 0; text-align: center'>${text}</div></html>")
    button.putClientProperty("html.disable", null)  // Enable HTML rendering
    button.addActionListener { onClick() }
    button.toolTipText = toolTip
    button.foreground = JBColor.BLACK

    val preferredSize = button.preferredSize
    button.preferredSize = Dimension(150, preferredSize.height + 10)
    button.minimumSize = Dimension(150, preferredSize.height + 10)
    button.maximumSize = Dimension(150, preferredSize.height + 10)

    return button
}
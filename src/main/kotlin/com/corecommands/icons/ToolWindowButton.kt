package com.corecommands.icons

import com.intellij.ui.JBColor
import javax.swing.JButton

fun instanceButton(
    text: String,
    toolTip: String,
    onClick: () -> Unit
): JButton {
    val button = JButton(text)
    button.addActionListener { onClick() }
    button.toolTipText = toolTip
    button.foreground = JBColor.BLACK
    return button
}
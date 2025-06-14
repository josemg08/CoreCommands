package com.corecommands.icons

import com.intellij.ui.JBColor
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.border.LineBorder

fun instanceButton(
    text: String,
    toolTip: String,
    onClick: () -> Unit
): JButton {
    val button = JButton(text)
    button.toolTipText = toolTip
    button.addActionListener { onClick() }
    button.border = BorderFactory.createCompoundBorder(
        LineBorder(JBColor.BLACK, 2, true),
        BorderFactory.createEmptyBorder(8, 24, 8, 24)
    )
    button.isFocusPainted = false
    button.isContentAreaFilled = false
    button.isOpaque = false
    button.foreground = JBColor.BLACK
    button.addMouseListener(object : MouseAdapter() {
        override fun mouseEntered(e: MouseEvent) {
            button.isContentAreaFilled = true
            button.background = JBColor(0x282828, 0xE0E0E0)
        }
        override fun mouseExited(e: MouseEvent) {
            button.isContentAreaFilled = false
        }
        override fun mousePressed(e: MouseEvent) {
            button.background = JBColor(0x464646, 0xF5F5F5)
        }
        override fun mouseReleased(e: MouseEvent) {
            button.background = JBColor(0x282828, 0xE0E0E0)
        }
    })
    return button
}
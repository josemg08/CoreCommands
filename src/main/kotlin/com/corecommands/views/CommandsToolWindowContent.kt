package com.corecommands.views

import com.intellij.util.ui.JBInsets
import java.awt.BorderLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.BorderFactory
import javax.swing.JPanel

class CommandsToolWindowContent(commandRows: List<CommandRow>) {
    val contentPanel: JPanel = JPanel()

    init {
        contentPanel.setLayout(BorderLayout(0, 20))
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0))
        contentPanel.add(createControlsPanel(commandRow = commandRows), BorderLayout.CENTER)
    }

    fun createControlsPanel(commandRow: List<CommandRow>): JPanel {
        val controlsPanel = JPanel()
        controlsPanel.layout = GridBagLayout()
        controlsPanel.border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
        val gridConstraintsLayout = GridBagConstraints()

        gridConstraintsLayout.insets = JBInsets.create(10, 0)
        gridConstraintsLayout.anchor = GridBagConstraints.NORTHWEST // Align at the top
        gridConstraintsLayout.fill = GridBagConstraints.HORIZONTAL
        gridConstraintsLayout.weightx = 1.0

        addRowsToGridConstraints(
            rows = commandRow,
            gridConstraintsLayout,
            controlsPanel
        )

        // Make sure the panel takes all vertical space but aligns content at the top
        val filler = JPanel()
        filler.isOpaque = false
        gridConstraintsLayout.gridy = 2
        gridConstraintsLayout.gridx = 0
        gridConstraintsLayout.gridwidth = 2
        gridConstraintsLayout.weighty = 1.0
        gridConstraintsLayout.fill = GridBagConstraints.BOTH
        controlsPanel.add(filler, gridConstraintsLayout)

        return controlsPanel
    }

    fun addRowsToGridConstraints(rows: List<CommandRow>, gridConstraints: GridBagConstraints, panel: JPanel) {
        for ((index, row) in rows.withIndex()) {
            gridConstraints.gridy = index
            gridConstraints.gridx = 0
            gridConstraints.weightx = 0.0
            panel.add(row.button, gridConstraints)
            gridConstraints.gridx = 1
            gridConstraints.weightx = 1.0
            panel.add(row.descriptionLabel, gridConstraints)
        }
    }
}
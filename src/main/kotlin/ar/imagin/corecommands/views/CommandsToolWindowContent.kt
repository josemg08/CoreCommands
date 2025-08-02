package ar.imagin.corecommands.views

import com.intellij.ui.components.JBScrollPane
import com.intellij.util.ui.JBInsets
import java.awt.BorderLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.BorderFactory
import javax.swing.JPanel

class CommandsToolWindowContent(commandRows: List<CommandRow>) {
    val contentPanel: JPanel = JPanel()
    val scrollPane: JBScrollPane

    init {
        contentPanel.setLayout(BorderLayout(0, 20))
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0))  // Removed top padding
        contentPanel.add(createControlsPanel(commandRow = commandRows), BorderLayout.CENTER)

        // Create scroll pane and add content panel to it
        scrollPane = JBScrollPane(contentPanel)
        scrollPane.border = BorderFactory.createEmptyBorder()
        scrollPane.verticalScrollBarPolicy = JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollPane.horizontalScrollBarPolicy = JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    }

    fun createControlsPanel(commandRow: List<CommandRow>): JPanel {
        val controlsPanel = JPanel()
        controlsPanel.layout = GridBagLayout()
        controlsPanel.border = BorderFactory.createEmptyBorder(10, 20, 20, 20)
        val gridConstraintsLayout = GridBagConstraints()

        gridConstraintsLayout.insets = JBInsets.create(10, 0)
        gridConstraintsLayout.anchor = GridBagConstraints.NORTHWEST
        gridConstraintsLayout.fill = GridBagConstraints.HORIZONTAL
        gridConstraintsLayout.weightx = 1.0
        gridConstraintsLayout.weighty = 0.0  // Set to 0.0 for regular rows

        addRowsToGridConstraints(
            rows = commandRow,
            gridConstraintsLayout,
            controlsPanel
        )

        // Add a single filler panel at the very end
        val filler = JPanel()
        filler.isOpaque = false
        gridConstraintsLayout.gridy = commandRow.size  // Set to after the last command row
        gridConstraintsLayout.gridx = 0
        gridConstraintsLayout.gridwidth = 2
        gridConstraintsLayout.weighty = 1.0  // Only the filler should expand
        gridConstraintsLayout.fill = GridBagConstraints.BOTH
        controlsPanel.add(filler, gridConstraintsLayout)

        return controlsPanel
    }

    fun addRowsToGridConstraints(rows: List<CommandRow>, gridConstraints: GridBagConstraints, panel: JPanel) {
        for ((index, row) in rows.withIndex()) {
            gridConstraints.gridy = index
            gridConstraints.gridx = 0
            gridConstraints.weightx = 0.0
            panel.add(row.containerPanel, gridConstraints)
            gridConstraints.gridx = 1
            gridConstraints.weightx = 1.0
            panel.add(row.descriptionLabel, gridConstraints)
        }
    }
}
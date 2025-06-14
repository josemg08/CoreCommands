package com.corecommands.views

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.JBColor
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBInsets
import runCommandInTerminal
import java.awt.BorderLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*

internal class CommandsToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val toolWindowContent = CommandsToolWindowContent(project = project)
        val content = ContentFactory.getInstance().createContent(toolWindowContent.contentPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private class CommandsToolWindowContent(project: Project) {
        val contentPanel: JPanel = JPanel()

        init {
            contentPanel.setLayout(BorderLayout(0, 20))
            contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0))
            contentPanel.add(createControlsPanel(project = project), BorderLayout.CENTER)
        }

        fun createControlsPanel(project: Project): JPanel {
            val controlsPanel = JPanel()
            controlsPanel.layout = GridBagLayout()
            controlsPanel.border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
            val gridConstraintsLayout = GridBagConstraints()

            gridConstraintsLayout.insets = JBInsets.create(10, 0)
            gridConstraintsLayout.anchor = GridBagConstraints.NORTHWEST // Align at the top
            gridConstraintsLayout.fill = GridBagConstraints.HORIZONTAL
            gridConstraintsLayout.weightx = 1.0

            addRowsToGridConstraints(
                rows = listOf(
                    CommandRow(
                        buttonText = "Reformat",
                        description = "Reformats the current file according to code style settings. This can help keep your code clean and consistent with project standards.",
                        onClick = { runReformatCommandOnTerminal(project) }
                    ),
                    CommandRow(
                        buttonText = "Compile Project",
                        description = "Compiles all project sources and resources. Use this to ensure your code builds successfully before running or deploying.",
                        onClick = { runBuildCommandOnTerminal(project) }
                    )
                ),
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

        fun runReformatCommandOnTerminal(project: Project) {
            runCommandInTerminal(
                project = project,
                command = "./local_helpers.sh -f RUN_FORMAT"
            )
        }

        fun runBuildCommandOnTerminal(project: Project) {
            runCommandInTerminal(
                project = project,
                command = "./gradlew app:assembleGoogleDebug"
            )
        }

        private class CommandRow(
            buttonText: String,
            description: String,
            onClick: () -> Unit
        ) {
            val button = instanceButton(
                buttonText,
                description,
                onClick
            )

            val descriptionLabel =
                JLabel("<html><div style='margin-left:12px;width:220px;'>$description</div></html>").apply {
                    foreground = JBColor(0x787878, 0x787878)
                    font = font.deriveFont(13f)
                }
        }
    }
}
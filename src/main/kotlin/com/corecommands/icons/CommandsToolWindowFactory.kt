package com.corecommands.icons

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.JBColor
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBInsets
import java.awt.BorderLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.util.*
import javax.swing.*

/*object SdkIcons {
    val Sdk_default_icon: Icon = getIcon("/icons/androidMustache.svg", SdkIcons::class.java)
}*/

internal class CommandsToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val toolWindowContent = CalendarToolWindowContent(toolWindow)
        val content = ContentFactory.getInstance().createContent(toolWindowContent.contentPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private class CalendarToolWindowContent(toolWindow: ToolWindow) {
        val contentPanel: JPanel = JPanel()
        private val currentDate = JLabel()
        private val timeZone = JLabel()
        private val currentTime = JLabel()

        init {
            contentPanel.setLayout(BorderLayout(0, 20))
            contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0))
            contentPanel.add(createCalendarPanel(), BorderLayout.PAGE_START)
            contentPanel.add(createControlsPanel(toolWindow), BorderLayout.CENTER)
            runReformatCommandOnTerminal()
        }

        fun createCalendarPanel(): JPanel {
            val calendarPanel = JPanel()
            setIconLabel(currentDate, ANDROID_MUSTACHE_PATH)
            calendarPanel.add(currentDate)
            calendarPanel.add(timeZone)
            calendarPanel.add(currentTime)
            return calendarPanel
        }

        fun setIconLabel(label: JLabel, imagePath: String) {
            label.setIcon(ImageIcon(Objects.requireNonNull(javaClass.getResource(imagePath))))
        }

        fun createControlsPanel(toolWindow: ToolWindow): JPanel {
            val controlsPanel = JPanel()
            controlsPanel.layout = GridBagLayout()
            controlsPanel.border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
            val gridConstraintsLayout = GridBagConstraints()

            gridConstraintsLayout.insets = JBInsets.create(10, 0)
            gridConstraintsLayout.anchor = GridBagConstraints.NORTHWEST // Align at the top
            gridConstraintsLayout.fill = GridBagConstraints.HORIZONTAL
            gridConstraintsLayout.weightx = 1.0

            addRowsToGridConstraints(
                listOf(
                    CommandRow(
                        buttonText = "Reformat",
                        description = "Reformats the current file according to code style settings. This can help keep your code clean and consistent with project standards.",
                        onClick = this::runReformatCommandOnTerminal),
                    CommandRow(
                        buttonText = "Compile Project",
                        description = "Compiles all project sources and resources. Use this to ensure your code builds successfully before running or deploying.",
                        onClick = this::runBuildCommandOnTerminal)
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

        fun runReformatCommandOnTerminal() {
            //TODO
        }

        fun runBuildCommandOnTerminal() {
            //TODO
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

            val descriptionLabel = JLabel("<html><div style='margin-left:12px;width:220px;'>$description</div></html>").apply {
                foreground = JBColor(0x787878, 0x787878)
                font = font.deriveFont(13f)
            }
        }

        companion object {
            private const val ANDROID_MUSTACHE_PATH = "/icons/androidMustache.svg"
        }
    }
}
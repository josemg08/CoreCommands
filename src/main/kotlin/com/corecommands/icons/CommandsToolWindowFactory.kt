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
import java.net.URL
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
            label.setIcon(ImageIcon(Objects.requireNonNull<URL?>(javaClass.getResource(imagePath))))
        }

        fun createControlsPanel(toolWindow: ToolWindow): JPanel {
            val controlsPanel = JPanel()
            controlsPanel.layout = GridBagLayout()
            controlsPanel.border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
            val gbc = GridBagConstraints()
            // Use JBInsets for DPI-aware insets (correct usage)

            gbc.insets = JBInsets.create(10, 0)
            gbc.anchor = GridBagConstraints.NORTHWEST // Align at the top
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.weightx = 1.0

            // Button 1: Reformat
            val reformatButton = instanceButton(
                "Reformat",
                "Reformat the current file using project code style.",
                this::runReformatCommandOnTerminal
            )

            // Multi-line description label with margin
            val reformatDesc = JLabel("<html><div style='margin-left:12px;width:220px;'>Reformats the current file according to code style settings. This can help keep your code clean and consistent with project standards.</div></html>")
            reformatDesc.foreground = JBColor(0x787878, 0x787878)
            reformatDesc.font = reformatDesc.font.deriveFont(13f)

            // Button 2: Compile Project
            val buildProjectButton = instanceButton(
                "Compile Project",
                "Compile the entire project.",
                this::runBuildCommandOnTerminal
            )

            val buildDesc = JLabel("<html><div style='margin-left:12px;width:220px;'>Compiles all project sources and resources. Use this to ensure your code builds successfully before running or deploying.</div></html>")
            buildDesc.foreground = JBColor(0x787878, 0x787878)
            buildDesc.font = buildDesc.font.deriveFont(13f)

            // Add first row
            gbc.gridy = 0
            gbc.gridx = 0
            gbc.weightx = 0.0
            controlsPanel.add(reformatButton, gbc)
            gbc.gridx = 1
            gbc.weightx = 1.0
            controlsPanel.add(reformatDesc, gbc)

            // Add second row
            gbc.gridy = 1
            gbc.gridx = 0
            gbc.weightx = 0.0
            controlsPanel.add(buildProjectButton, gbc)
            gbc.gridx = 1
            gbc.weightx = 1.0
            controlsPanel.add(buildDesc, gbc)

            // Make sure the panel takes all vertical space but aligns content at the top
            val filler = JPanel()
            filler.isOpaque = false
            gbc.gridy = 2
            gbc.gridx = 0
            gbc.gridwidth = 2
            gbc.weighty = 1.0
            gbc.fill = GridBagConstraints.BOTH
            controlsPanel.add(filler, gbc)

            return controlsPanel
        }

        fun runReformatCommandOnTerminal() {
            //TODO
        }

        fun runBuildCommandOnTerminal() {
            //TODO
        }

        companion object {
            private const val ANDROID_MUSTACHE_PATH = "/icons/androidMustache.svg"
        }
    }
}
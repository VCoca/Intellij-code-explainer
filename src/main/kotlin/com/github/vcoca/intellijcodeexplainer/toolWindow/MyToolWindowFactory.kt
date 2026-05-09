package com.github.vcoca.intellijcodeexplainer.toolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.github.vcoca.intellijcodeexplainer.MyBundle
import com.github.vcoca.intellijcodeexplainer.services.MyProjectService
import javax.swing.JButton
import com.intellij.ui.components.JBTextArea


class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val inputArea = JBTextArea()
            val outputArea = JBTextArea()

            val explainButton = JButton("Explain")

            explainButton.addActionListener {
                outputArea.text = "AI explanation goes here"
            }
            add(explainButton)
            add(inputArea)
            add(outputArea)
        }
    }
}

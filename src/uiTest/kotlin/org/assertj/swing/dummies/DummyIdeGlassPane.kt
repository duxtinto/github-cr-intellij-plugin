package org.assertj.swing.dummies

import com.intellij.openapi.Disposable
import com.intellij.openapi.ui.Painter
import com.intellij.openapi.wm.IdeGlassPane
import java.awt.Component
import java.awt.Cursor
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import javax.swing.JPanel

class DummyIdeGlassPane : JPanel(), IdeGlassPane {
    override fun setCursor(cursor: Cursor?, requestor: Any) {
    }

    override fun addMouseMotionPreprocessor(listener: MouseMotionListener?, parent: Disposable?) {
    }

    override fun addPainter(component: Component?, painter: Painter?, parent: Disposable?) {
    }

    override fun removeMouseMotionPreprocessor(listener: MouseMotionListener?) {
    }

    override fun addMousePreprocessor(listener: MouseListener?, parent: Disposable?) {
    }

    override fun removeMousePreprocessor(listener: MouseListener?) {
    }

    override fun removePainter(painter: Painter?) {
    }
}
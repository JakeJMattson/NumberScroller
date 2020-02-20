package me.jakejmattson.numberscroller

import org.jnativehook.mouse.*
import java.awt.*
import java.awt.event.KeyEvent

class ScrollListener : NativeMouseWheelListener {
    private var currentKey = 1
    private var robot: Robot? = null
    override fun nativeMouseWheelMoved(nativeMouseWheelEvent: NativeMouseWheelEvent) {
        currentKey -= nativeMouseWheelEvent.wheelRotation
        if (currentKey < 0) currentKey = 9 else if (currentKey > 9) currentKey = 0
        robot!!.keyPress(KeyEvent.VK_0 + currentKey)
    }

    init {
        try {
            robot = Robot()
        } catch (e: AWTException) {
            e.printStackTrace()
        }
    }
}
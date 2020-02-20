package me.jakejmattson.numberscroller

import org.jnativehook.*
import java.util.logging.*

fun main() {
    disableLogging()
    try {
        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeMouseWheelListener(ScrollListener())
    } catch (e: NativeHookException) {
        println("Failed to register native hook!")
    }
}

private fun disableLogging() {
    val logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
    logger.level = Level.WARNING
    logger.useParentHandlers = false
}
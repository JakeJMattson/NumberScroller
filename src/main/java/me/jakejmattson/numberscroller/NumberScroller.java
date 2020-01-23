package me.jakejmattson.numberscroller;

import org.jnativehook.*;

import java.util.logging.*;

public class NumberScroller {

    public static void main(String[] args) {
        disableLogging();

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseWheelListener(new ScrollListener());
        } catch (NativeHookException e) {
            System.out.println("Failed to register native hook!");
        }
    }

    private static void disableLogging() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
    }
}

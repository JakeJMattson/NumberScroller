package io.github.jakejmattson.numberscroller;

import org.jnativehook.*;

import java.util.logging.*;

public class NumberScroller {

    public static void main(String[] args)
    {
        disableLogging();
        ScrollListener listener = new ScrollListener();

        try
        {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseWheelListener(listener);
        }
        catch (NativeHookException e)
        {
            e.printStackTrace();
        }
    }

    private static void disableLogging()
    {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
    }
}

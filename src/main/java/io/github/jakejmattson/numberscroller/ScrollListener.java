package io.github.jakejmattson.numberscroller;

import org.jnativehook.mouse.*;

import java.awt.*;

public class ScrollListener implements NativeMouseWheelListener {

    int currentKey = 1;
    Robot robot;

    public ScrollListener() {

        try {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        currentKey -= nativeMouseWheelEvent.getWheelRotation();

        if (currentKey < 1)
            currentKey = 10;
        else if (currentKey > 10)
            currentKey = 1;

        System.out.println(currentKey);
    }
}

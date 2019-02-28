package io.github.jakejmattson.numberscroller;

import org.jnativehook.mouse.*;

import java.awt.*;
import java.awt.event.*;

public class ScrollListener implements NativeMouseWheelListener {

    private int currentKey = 1;
    private Robot robot;

    public ScrollListener() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        currentKey -= nativeMouseWheelEvent.getWheelRotation();

        if (currentKey < 0)
            currentKey = 9;
        else if (currentKey > 9)
            currentKey = 0;

        robot.keyPress(KeyEvent.VK_0 + currentKey);
    }
}

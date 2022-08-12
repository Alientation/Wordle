package com.alientation.gui.graphics.events.types;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;

import java.awt.event.MouseEvent;

public class MouseScrolledEvent extends MouseButtonEvent {
    private int scrollLength;

    public MouseScrolledEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_SCROLLED, window, mouseEvent);
        this.scrollLength = scrollLength;
    }

    public int getScrollLength() {
        return scrollLength;
    }
}

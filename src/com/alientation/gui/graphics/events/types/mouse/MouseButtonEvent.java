package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;

import java.awt.event.MouseEvent;

public class MouseButtonEvent extends Event {
    protected MouseEvent mouseEvent;
    protected MouseButtonEvent(Type type, Window window, MouseEvent mouseEvent) {
        super(type, window);
        this.mouseEvent = mouseEvent;
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }
}

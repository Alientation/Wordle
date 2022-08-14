package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MouseMovedEvent extends MouseButtonEvent {

    public MouseMovedEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_MOVED, window, mouseEvent);
    }
}

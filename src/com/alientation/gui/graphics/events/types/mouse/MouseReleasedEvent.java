package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MouseReleasedEvent extends MouseButtonEvent {

    public MouseReleasedEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_RELEASED, window, mouseEvent);
    }
}

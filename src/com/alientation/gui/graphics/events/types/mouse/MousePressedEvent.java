package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MousePressedEvent extends MouseButtonEvent {

    public MousePressedEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_PRESSED, window, mouseEvent);
    }

}
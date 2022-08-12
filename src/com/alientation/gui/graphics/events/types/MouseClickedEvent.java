package com.alientation.gui.graphics.events.types;

import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MouseClickedEvent extends MouseButtonEvent {
    public MouseClickedEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_CLICKED, window, mouseEvent);
    }
}

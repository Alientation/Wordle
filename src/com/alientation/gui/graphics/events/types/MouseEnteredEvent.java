package com.alientation.gui.graphics.events.types;

import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MouseEnteredEvent extends MouseButtonEvent {
    public MouseEnteredEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_ENTERED, window, mouseEvent);
    }
}

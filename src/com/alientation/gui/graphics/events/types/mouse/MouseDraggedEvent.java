package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MouseDraggedEvent extends MouseButtonEvent {
    public MouseDraggedEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_RELEASED, window, mouseEvent);
    }
}

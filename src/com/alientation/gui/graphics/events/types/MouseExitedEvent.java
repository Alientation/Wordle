package com.alientation.gui.graphics.events.types;


import com.alientation.gui.graphics.Window;

import java.awt.event.MouseEvent;

public class MouseExitedEvent extends MouseButtonEvent {
    public MouseExitedEvent(Window window, MouseEvent mouseEvent) {
        super(Type.MOUSE_EXITED, window, mouseEvent);
    }
}

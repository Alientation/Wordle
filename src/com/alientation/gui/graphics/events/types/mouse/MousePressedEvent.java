package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.MouseEvent;

public class MousePressedEvent extends MouseButtonEvent {

    public MousePressedEvent(Renderable renderable, MouseEvent mouseEvent) {
        super(Type.MOUSE_PRESSED, renderable, mouseEvent);
    }

}
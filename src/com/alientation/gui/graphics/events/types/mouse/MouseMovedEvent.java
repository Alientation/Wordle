package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.MouseEvent;

public class MouseMovedEvent extends MouseButtonEvent {
    public MouseMovedEvent(Renderable renderable, MouseEvent mouseEvent) {
        super(Type.MOUSE_MOVED, renderable, mouseEvent);
    }
}

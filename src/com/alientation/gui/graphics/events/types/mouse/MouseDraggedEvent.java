package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.MouseEvent;

public class MouseDraggedEvent extends MouseButtonEvent {
    public MouseDraggedEvent(Renderable renderable, MouseEvent mouseEvent) {
        super(Type.MOUSE_RELEASED, renderable, mouseEvent);
    }
}

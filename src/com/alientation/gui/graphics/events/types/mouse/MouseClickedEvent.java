package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.MouseEvent;

public class MouseClickedEvent extends MouseButtonEvent {
    public MouseClickedEvent(Renderable renderable, MouseEvent mouseEvent) {
        super(Type.MOUSE_CLICKED, renderable, mouseEvent);
    }
}

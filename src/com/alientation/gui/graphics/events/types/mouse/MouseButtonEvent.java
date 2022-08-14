package com.alientation.gui.graphics.events.types.mouse;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.MouseEvent;

public class MouseButtonEvent extends Event {
    protected MouseEvent mouseEvent;
    protected MouseButtonEvent(Type type, Renderable renderable, MouseEvent mouseEvent) {
        super(type, renderable);
        this.mouseEvent = mouseEvent;
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }
}

package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;
import com.alientation.gui.graphics.renderable.Renderable;

/**
 * Event wrapper to add more data to these events
 */
public class KeyEvent extends Event {
    private java.awt.event.KeyEvent keyEvent;

    public KeyEvent(Type type, Renderable renderable, java.awt.event.KeyEvent keyEvent) {
        super(type, renderable);
        this.keyEvent = keyEvent;
    }

    public java.awt.event.KeyEvent getKeyEvent() {
        return keyEvent;
    }
}

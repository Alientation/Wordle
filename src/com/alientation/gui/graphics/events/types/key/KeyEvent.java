package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;

/**
 * Event wrapper to add more data to these events
 */
public class KeyEvent extends Event {
    private java.awt.event.KeyEvent keyEvent;

    public KeyEvent(Type type, Window window, java.awt.event.KeyEvent keyEvent) {
        super(type, window);
        this.keyEvent = keyEvent;
    }

    public java.awt.event.KeyEvent getKeyEvent() {
        return keyEvent;
    }
}

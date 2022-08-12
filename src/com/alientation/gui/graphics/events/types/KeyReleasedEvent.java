package com.alientation.gui.graphics.events.types;

import com.alientation.gui.graphics.Window;

import java.awt.event.KeyEvent;

public class KeyReleasedEvent extends com.alientation.gui.graphics.events.types.KeyEvent {
    public KeyReleasedEvent(Window window, KeyEvent keyEvent) {
        super(Type.KEY_RELEASED, window, keyEvent);
    }
}

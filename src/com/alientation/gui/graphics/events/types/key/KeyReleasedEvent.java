package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;

import java.awt.event.KeyEvent;

public class KeyReleasedEvent extends com.alientation.gui.graphics.events.types.key.KeyEvent {
    public KeyReleasedEvent(Window window, KeyEvent keyEvent) {
        super(Type.KEY_RELEASED, window, keyEvent);
    }
}

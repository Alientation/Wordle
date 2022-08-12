package com.alientation.gui.graphics.events.types;

import com.alientation.gui.graphics.Window;

import java.awt.event.KeyEvent;

public class KeyPressedEvent extends com.alientation.gui.graphics.events.types.KeyEvent {
    public KeyPressedEvent(Window window, KeyEvent keyEvent) {
        super(Type.KEY_PRESSED, window, keyEvent);
    }
}

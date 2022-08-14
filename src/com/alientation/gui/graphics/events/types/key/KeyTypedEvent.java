package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;

import java.awt.event.KeyEvent;

public class KeyTypedEvent extends com.alientation.gui.graphics.events.types.key.KeyEvent {
    public KeyTypedEvent(Window window, KeyEvent keyEvent) {
        super(Event.Type.KEY_RELEASED, window, keyEvent);
    }
}

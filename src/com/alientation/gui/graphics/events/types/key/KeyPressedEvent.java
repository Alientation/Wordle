package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.KeyEvent;

public class KeyPressedEvent extends com.alientation.gui.graphics.events.types.key.KeyEvent {
    public KeyPressedEvent(Renderable renderable, KeyEvent keyEvent) {
        super(Type.KEY_PRESSED, renderable, keyEvent);
    }
}

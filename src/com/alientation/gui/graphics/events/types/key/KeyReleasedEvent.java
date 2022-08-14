package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.KeyEvent;

public class KeyReleasedEvent extends com.alientation.gui.graphics.events.types.key.KeyEvent {
    public KeyReleasedEvent(Renderable renderable, KeyEvent keyEvent) {
        super(Type.KEY_RELEASED, renderable, keyEvent);
    }
}

package com.alientation.gui.graphics.events.types.key;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.Event;
import com.alientation.gui.graphics.renderable.Renderable;

import java.awt.event.KeyEvent;

public class KeyTypedEvent extends com.alientation.gui.graphics.events.types.key.KeyEvent {
    public KeyTypedEvent(Renderable renderable, KeyEvent keyEvent) {
        super(Event.Type.KEY_RELEASED, renderable, keyEvent);
    }
}

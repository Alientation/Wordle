package com.alientation.gui.graphics.events;

import com.alientation.gui.graphics.Window;

public abstract class Event {

    public enum Type {
        MOUSE_MOVED,
        MOUSE_ENTERED,
        MOUSE_EXITED,
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_CLICKED,
        MOUSE_DRAGGED,
        MOUSE_SCROLLED,
        KEY_PRESSED,
        KEY_RELEASED,
        KEY_TYPED;
    }
    private Type type;
    private Window window;
    boolean cancelled, handled;
    protected Event(Type type, Window window) {
        this.type = type;
        this.window = window;
    }

    public Type getType() {
        return type;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isHandled() {
        return handled;
    }
}

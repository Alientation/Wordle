package com.alientation.gui.graphics.events;

import com.alientation.gui.graphics.renderable.Renderable;

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
        KEY_TYPED
    }
    private final Type type;
    private final Renderable renderable;
    boolean cancelled, handled;
    private final long initTime;
    protected Event(Type type, Renderable renderable) {
        this.type = type;
        this.renderable = renderable;
        this.initTime = System.currentTimeMillis();
    }

    public Type getType() { return type; }
    public Renderable getRenderable() { return renderable; }
    public boolean isCancelled() { return cancelled; }
    public void cancel() { this.cancelled = true; }
    public boolean isHandled() { return handled; }
    public long getInitTime() { return initTime; }
}

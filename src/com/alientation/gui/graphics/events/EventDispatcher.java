package com.alientation.gui.graphics.events;

import com.alientation.gui.graphics.Window;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {
    /**
     * Window the events are contained within
     */
    private Window window;

    /**
     * List of event listeners that is used to access events before it is handled
     */
    private List<EventListener> eventListenerList;

    /**
     * List of event handlers that is used to handle the events with appropriate responses
     */
    private List<EventHandler> eventHandlerList;
    public EventDispatcher(Window window) {
        this.window = window;
        this.eventListenerList = new ArrayList<>();
        this.eventHandlerList = new ArrayList<>();
    }

    public void registerEventListener(EventListener listener) {
        if (eventListenerList.contains(listener)) return;
        this.eventListenerList.add(listener);
    }

    public void registerEventHandler(EventHandler handler) {
        if (eventHandlerList.contains(handler)) return;
        this.eventHandlerList.add(handler);
    }

    public void dispatch(Event event) {
        //dispatch to registered event handlers
        /*if (event.handled)
            return;
        if (event.getType() == type)
            event.handled = handler.onEvent(event);
        */
    }
}

package com.alientation.gui.graphics.events;

import com.alientation.gui.graphics.renderable.Renderable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Dispatches events first to listeners then to handlers that handle said event
 */
public class EventDispatcher {
    /**
     * Renderable the events are contained within
     */
    private final Renderable renderable;


    /**
     * List of event listeners that is used to access events before it is handled
     */
    private List<EventListener> eventListenerList;

    /**
     * List of event handlers that is used to handle the events with appropriate responses
     */
    private List<EventHandler> eventHandlerList;
    public EventDispatcher(Renderable renderable) {
        this.renderable = renderable;
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
        for (EventListener eventListener : eventListenerList) {
            if (event.cancelled) return;

            for (Method method : eventListener.getClass().getMethods()) {
                if (!method.isAnnotationPresent(com.alientation.gui.annotations.EventListener.class) ||
                        method.getParameterCount() != 1 ||
                        method.getParameterTypes()[0] != event.getClass()
                ) continue;

                method.setAccessible(true);

                try {
                    method.invoke(eventListener,event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (EventHandler eventHandler : eventHandlerList) {
            if (event.handled || event.cancelled) return;

            for (Method method : eventHandler.getClass().getMethods()) {
                if (!method.isAnnotationPresent(com.alientation.gui.annotations.EventHandler.class) ||
                        method.getParameterCount() != 1 ||
                        method.getParameterTypes()[0] != event.getClass()
                ) continue;

                method.setAccessible(true);

                try {
                    event.handled = (Boolean) method.invoke(eventHandler, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

package com.alientation.gui.graphics.events;

import com.alientation.gui.graphics.renderable.Renderable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final List<EventListener> eventListenerList;

    /**
     * List of event handlers that is used to handle the events with appropriate responses
     */
    private final List<EventHandler> eventHandlerList;

    private final Map<Class,List<Method>> eventListenersMap;
    private final Map<Class,List<Method>> eventHandlersMap;
    private final Map<Method,EventListener> methodEventListenerMap;
    private final Map<Method,EventHandler> methodEventHandlerMap;
    public EventDispatcher(Renderable renderable) {
        this.renderable = renderable;
        this.eventListenerList = new ArrayList<>();
        this.eventHandlerList = new ArrayList<>();
        this.eventListenersMap = new HashMap<>();
        this.eventHandlersMap = new HashMap<>();
        this.methodEventListenerMap = new HashMap<>();
        this.methodEventHandlerMap = new HashMap<>();
    }

    public void registerEventListener(EventListener listener) {
        if (eventListenerList.contains(listener)) return;
        this.eventListenerList.add(listener);
        update();
    }

    public void unregisterEventListener(EventListener listener) {
        this.eventListenerList.remove(listener);
        update();
    }

    public void registerEventHandler(EventHandler handler) {
        if (eventHandlerList.contains(handler)) return;
        this.eventHandlerList.add(handler);
        update();
    }
    public void unregisterEventHandler(EventHandler handler) {
        this.eventHandlerList.remove(handler);
        update();
    }

    public void update() {
        eventListenersMap.clear();
        eventHandlersMap.clear();
        methodEventHandlerMap.clear();
        methodEventListenerMap.clear();
        for (EventListener eventListener : eventListenerList) {
            for (Method method : eventListener.getClass().getMethods()) {
                if (!method.isAnnotationPresent(com.alientation.gui.annotations.EventListener.class) ||
                        method.getParameterCount() != 1 ||
                        !Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                    continue;
                if (!eventListenersMap.containsKey(method.getParameterTypes()[0]))
                    eventListenersMap.put(method.getParameterTypes()[0],new ArrayList<>());
                eventListenersMap.get(method.getParameterTypes()[0]).add(method);
                methodEventListenerMap.put(method,eventListener);
            }
        }

        for (EventHandler eventHandler : eventHandlerList) {
            for (Method method : eventHandler.getClass().getMethods()) {
                if (!method.isAnnotationPresent(com.alientation.gui.annotations.EventHandler.class) ||
                        method.getParameterCount() != 1 ||
                        !Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                    continue;
                if (!eventHandlersMap.containsKey(method.getParameterTypes()[0]))
                    eventHandlersMap.put(method.getParameterTypes()[0],new ArrayList<>());
                eventHandlersMap.get(method.getParameterTypes()[0]).add(method);
                methodEventHandlerMap.put(method,eventHandler);
            }
        }
    }

    public void dispatch(Event event) {
        //dispatch to registered event listeners
        if (eventListenersMap.containsKey(event.getClass())) {
            for (Method method : eventListenersMap.get(event.getClass())) {
                if (methodEventListenerMap.containsKey(method)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(methodEventListenerMap.get(method), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        //dispatch to registered event handlers
        if (eventHandlersMap.containsKey(event.getClass())) {
            for (Method method : eventHandlersMap.get(event.getClass())) {
                if (methodEventHandlerMap.containsKey(method)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(methodEventHandlerMap.get(method), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Renderable getRenderable() { return renderable; }
    public List<EventListener> getEventListenerList() { return eventListenerList; }
    public List<EventHandler> getEventHandlerList() { return eventHandlerList; }
}

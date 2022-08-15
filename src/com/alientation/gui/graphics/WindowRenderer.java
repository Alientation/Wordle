package com.alientation.gui.graphics;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.RenderableComponent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WindowRenderer {
    private final Window window;
    private final List<RenderableComponent> sortedZIndex;

    public WindowRenderer(Window window) {
        this.window = window;
        this.sortedZIndex = new ArrayList<>();
    }

    public void update() {
        this.sortedZIndex.clear();

        //bfs through to find all renderables in this window
        Queue<RenderableComponent> bfs = new LinkedList<>();
        for (RenderableComponent renderableComponent : window.renderable.getSubreferences())
            bfs.offer(renderableComponent);

        Set<Renderable> visitedRenderables = new HashSet<>(); //sanity check, this shouldn't be a problem, but who knows

        RenderableComponent cur;
        while (!bfs.isEmpty()) {
            cur = bfs.poll();
            if (visitedRenderables.contains(cur)) {
                System.out.println("RenderableComponent " + cur + " is a subreference to multiple Renderables");
                continue;
            }
            visitedRenderables.add(cur);
            sortedZIndex.add(cur);
            for (RenderableComponent renderable : cur.getSubreferences())
                bfs.offer(renderable);
        }

        sortedZIndex.sort(Comparator.comparingInt(RenderableComponent::getZIndex));
    }

    /**
     * Renders only the renderableComponents after the first one that requires a render update.
     * Still would want maybe to check to make sure renderable components are within their bounds
     *
     * @param g Graphical output
     * @return Whether a render update was actually required or not
     */
    public boolean render(Graphics g) {
        boolean requiresRenderUpdate = window.renderable.requireRenderUpdate();
        window.renderable.setRequireRenderUpdate(false);
        if (requiresRenderUpdate)
            window.renderable.render(g);
        for (RenderableComponent renderableComponent : sortedZIndex) {
            if (renderableComponent.requireRenderUpdate()) {
                requiresRenderUpdate = true;
                renderableComponent.setRequireRenderUpdate(false);
            }
            if (requiresRenderUpdate)
                renderableComponent.render(g);
        }
        return requiresRenderUpdate;
    }

    public Window getWindow() { return window; }
    public List<RenderableComponent> getSortedZIndex() { return sortedZIndex; }
}

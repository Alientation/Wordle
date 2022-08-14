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

    public void render(Graphics g) {
        for (RenderableComponent renderableComponent : sortedZIndex)
            renderableComponent.render(g);
    }

    public Window getWindow() { return window; }
    public List<RenderableComponent> getSortedZIndex() { return sortedZIndex; }
}

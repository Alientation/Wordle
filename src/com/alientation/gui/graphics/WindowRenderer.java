package com.alientation.gui.graphics;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.RenderableComponent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WindowRenderer {
    private final Window window;
    private final List<Renderable> sortedZIndex;

    public WindowRenderer(Window window) {
        this.window = window;
        this.sortedZIndex = new ArrayList<>();
    }

    public void update() {
        this.sortedZIndex.clear();

        //bfs through to find all renderables in this window
        Queue<Renderable> bfs = new LinkedList<>();
        bfs.offer(window.renderable);

        Set<Renderable> visitedRenderables = new HashSet<>(); //sanity check, this shouldn't be a problem, but who knows

        Renderable cur;
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
        sortedZIndex.sort(Comparator.comparingInt(Renderable::getZIndex));
        /*
        for (Renderable renderable : sortedZIndex) {
            System.out.println(renderable.id());
        }*/
    }

    /**
     * Renders only the renderableComponents after the first one that requires a render update.
     * Still would want maybe to check to make sure renderable components are within their bounds
     *
     * @param g Graphical output
     * @return Whether a render update was actually required or not
     */
    public boolean render(Graphics g) {
        boolean requiredRenderUpdate = false;
        for (Renderable renderable : sortedZIndex) {
            if (renderable.requireRenderUpdate()) {
                requiredRenderUpdate = true;
                renderable.setRequireRenderUpdate(false);
            }
            //if (requiredRenderUpdate) TODO FIX BUG - requireRenderUpdate doesn't actually account for everything...
                //renderable.render(g);
        }
        if (requiredRenderUpdate) {
            g.setColor(Color.BLUE);
            g.fillRect(0,0,window.getWidth(),window.getHeight());
            for (Renderable renderable : sortedZIndex)
                renderable.render(g);
        }
        return requiredRenderUpdate;
    }

    public Window getWindow() { return window; }
    public List<Renderable> getSortedZIndex() { return sortedZIndex; }
}

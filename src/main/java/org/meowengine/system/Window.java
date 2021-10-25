package org.meowengine.system;

import org.joml.Vector2i;

public abstract class Window {

    private long id;

    protected WindowSize windowSize;
    protected boolean isShown;

    Window(long id) {
        this.windowSize = new WindowSize(0, 0);
        this.id = id;
    }

    public Vector2i getWindowSize() {
        return new Vector2i(windowSize.x, windowSize.y);
    }

    void updateWindowSize(int width, int height) {
        windowSize.updateWindowSize(width, height);
    }

    long getWindowId() {
        return id;
    }
}
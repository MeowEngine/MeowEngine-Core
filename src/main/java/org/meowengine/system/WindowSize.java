package org.meowengine.system;

public class WindowSize {
    public int x;
    public int y;

    WindowSize(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void updateWindowSize(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}

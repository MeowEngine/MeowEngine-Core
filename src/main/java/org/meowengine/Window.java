package org.meowengine;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

public class Window {

    long windowId;
    private WindowSize windowSize;

    private Window() { }

    public Window createGLFWWindow() {
        Window window = new Window();
        //TODO window.windowId = GLFW.glfwCreateWindow();

        return window;
    }

    public Vector2i getWindowSize() {
        return new Vector2i(windowSize.x, windowSize.y);
    }

    long getWindowId() {
        return windowId;
    }

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


}

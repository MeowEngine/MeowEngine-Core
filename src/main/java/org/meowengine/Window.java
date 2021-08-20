package org.meowengine;

import lombok.extern.slf4j.Slf4j;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

@Slf4j
public class Window {

    private long windowId;
    private WindowSize windowSize;

    // TODO Connect windowSize with callback in Application

    private Window() { }

    public static Window createGLFWWindow(ApplicationSettings settings) {
        Window window = new Window();
        window.windowId = GLFW.glfwCreateWindow(settings.getWindowWidth(),
                settings.windowHeight,
                settings.windowName,
                0, 0);
        if (window.windowId == 0) {
            log.error("Failed to initialize GLFW Window");
            throw new RuntimeException("Failed to create window");
        }
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

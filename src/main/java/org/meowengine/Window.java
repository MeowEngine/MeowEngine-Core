package org.meowengine;

import lombok.extern.slf4j.Slf4j;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

@Slf4j
public class Window {

    private long windowId;
    private WindowSize windowSize;
    private boolean isShown;

    private Window() { }

    public static Window createGLFWWindow() {
        return createGLFWWindow(ApplicationSettings.getDefault());
    }

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
        window.windowSize = new WindowSize(settings.windowWidth, settings.windowHeight);
        return window;
    }

    public Vector2i getWindowSize() {
        return new Vector2i(windowSize.x, windowSize.y);
    }

    /**
     * Toggling window visibility
     * @return new state of window visibility
     */
    public boolean toggleWindowVisibility() {
        if(isShown) {
            GLFW.glfwHideWindow(windowId);
        } else {
            GLFW.glfwShowWindow(windowId);
        }
        isShown = !isShown;
        return isShown;
    }

    long getWindowId() {
        return windowId;
    }

    void updateWindowSize(int width, int height) {
        windowSize.updateWindowSize(width, height);
    }

    public static class WindowSize {
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

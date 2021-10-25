package org.meowengine.system;

import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;
import org.lwjgl.system.MemoryStack;
import org.meowengine.ApplicationSettings;

import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

@Slf4j
public class GLFW extends WindowingSystem {

    // All windows created by GLFW is here
    private final Set<GLFWWindow> createdWindows;

    private GLFWWindow currentWindow;

    protected GLFW() {
        super();
        GLFWErrorCallback.createPrint(System.out).set();
        log.trace("Creating GLFW Windowing system");

        // Initializing GLFW
        log.debug("Starting GLFW");
        boolean status = glfwInit();
        if (status) {
            log.debug("Hello glfw " + glfwGetVersionString());
        } else {
            log.error("Failed to start GLFW");
            throw new RuntimeException("Failed to start GLFW");
        }

        // Setting default hints
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        createdWindows = new HashSet<>();
    }

    static {
        singleton = new GLFW();
    }

    @Override
    public Window createWindow() {
        return createWindow(ApplicationSettings.getDefault());
    }

    public Window createWindow(ApplicationSettings settings) {
        long id = glfwCreateWindow(settings.getWindowWidth(),
                settings.getWindowHeight(),
                settings.windowName,
                0, 0);

        if (id == 0) {
            log.error("Failed to create window");
            throw new RuntimeException("Failed to create window");
        }

        GLFWWindow window = new GLFWWindow(id);
        createdWindows.add(window);
        window.updateWindowSize(settings.getWindowWidth(), settings.getWindowHeight());
        return window;
    }


    @Override
    public void hideWindow(Window window) {
        glfwHideWindow(window.getWindowId());
    }

    @Override
    public void showWindow(Window window) {
        glfwShowWindow(window.getWindowId());
    }

    @Override
    public boolean toggleWindowVisibility(Window window) {
        if (window.isShown) {
            hideWindow(window);
        } else {
            showWindow(window);
        }
        window.isShown = !window.isShown;
        return window.isShown;
    }

    @Override
    public void terminate() {
        createdWindows.forEach(window -> {
            glfwFreeCallbacks(window.getWindowId());
            glfwDestroyWindow(window.getWindowId());
            glfwTerminate();
        });

        try {
            //noinspection ConstantConditions
            glfwSetErrorCallback(null).free();
        } catch (NullPointerException exception) {
            log.info("Maybe not set glfwErrorCallback");
            exception.printStackTrace();
        }
    }

    // TODO: 10/7/2021 There is an idea about creation ApplicationWindow and Window classes

    @Override
    public void makeWindowCurrent(Window window) {
        glfwMakeContextCurrent(window.getWindowId());
    }

    @Override
    public boolean isWindowShouldClosed(Window window) {
        return glfwWindowShouldClose(window.getWindowId());
    }

    @Override
    public void setWindowCloseRequest(Window window, boolean r) {
        glfwSetWindowShouldClose(window.getWindowId(), r);
    }

    /**
     * Set window in center of the screen
     */
    public void centralizeWindow(Window window) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window.getWindowId(), pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            assert vidmode != null;
            glfwSetWindowPos(
                    window.getWindowId(),
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
    }

    // ---- < CALLBACKS > ----

    protected void CallbackWindowResize(long window, int width, int height) {

    }

    protected void CallbackWindowCloseSignal(long window) {
        // TODO: 10/8/2021 There is glfwSetWindowCloseCallback
        //glfwSetWindowCloseCallback()
        //glfwSetWindowShouldClose(window, false);
    }

}

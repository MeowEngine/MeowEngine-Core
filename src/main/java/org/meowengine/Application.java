package org.meowengine;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryStack;
import org.meowengine.graphics.Node;
import org.meowengine.graphics.gui.GuiDrawer;

import java.nio.IntBuffer;
import java.util.Map;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;
import static org.lwjgl.system.MemoryStack.stackPush;

@Slf4j
public abstract class Application {
    protected final Node rootNode;
    protected Window window;
    protected Camera camera;
    protected GuiDrawer gui;
    protected Callback debugProc;

    protected ApplicationSettings applicationSettings;

    protected Application() {
        rootNode = new Node();
        applicationSettings = ApplicationSettings.getDefault();
    }

    protected Application(ApplicationSettings appSettings) {
        rootNode = new Node();
        applicationSettings = appSettings;
    }

    @SneakyThrows
    public final void Run() {
        log.info("Hello LWJGL " + Version.getVersion());
        Class.forName("org.meowengine.EngineProperties");

        init();
        loop();

        log.info("Freeing callbacks and destroying window");

        if (debugProc != null) {
            debugProc.free();
        }

        glfwFreeCallbacks(window.getWindowId());
        glfwDestroyWindow(window.getWindowId());
        glfwTerminate();

        try {
            //noinspection ConstantConditions
            glfwSetErrorCallback(null).free();
        } catch (NullPointerException exception) {
            log.info("Maybe not set glfwErrorCallback");
            exception.printStackTrace();
        }


        Disposer.dispose();
        log.info("Exciting...");
    }

    protected void init() {
        GLFWErrorCallback.createPrint(System.out).set();
        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize glfw\n");
        }
        log.info("Hello glfw " + glfwGetVersionString());

        // glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


        window = Window.createGLFWWindow(applicationSettings);


        log.info("Successfully created new window");

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


        glfwMakeContextCurrent(window.getWindowId());
        glfwSetWindowSizeCallback(window.getWindowId(), this::CallbackWindowResize);
        glfwSetWindowCloseCallback(window.getWindowId(), this::CallbackWindowCloseSignal);

        glfwSwapInterval(1);

        glfwShowWindow(window.getWindowId());
        GL.createCapabilities();
        glViewport(0, 0, 1280, 720);

        debugProc = GLUtil.setupDebugMessageCallback();


        log.info("OpenGL version " + glGetString(GL_VERSION));

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_MIRRORED_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        log.info("Try to show you best game...");

        SimpleInit();

        if (camera == null) {
            log.error("Camera isn't initialized");
        }
    }

    protected void loop() {
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        long time = System.nanoTime();

        while (!glfwWindowShouldClose(window.getWindowId())) {

            update(System.nanoTime() - time);
            SimpleUpdate(System.nanoTime() - time);
            draw(System.nanoTime() - time);
            SimpleDraw(System.nanoTime() - time);
            time = System.nanoTime();


        }
        log.info("Exiting from main loop");

    }

    protected void draw(long spendTime) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    protected void update(long spendTime) {
        glfwSwapBuffers(window.getWindowId());
        glfwPollEvents();
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        int error = glGetError();
        if (error != GL_NO_ERROR) {
            log.error("OPENGL ERROR " + error);
        }
    }


    /**
     * This function is automatically called when application starts.
     */
    public abstract void SimpleInit();

    /**
     * This function is a part of game loop.
     * Here you can draw anything you want.
     *
     * @param spendTime Time that consumed from start of loop (ms)
     */
    public abstract void SimpleDraw(long spendTime);

    /**
     * This function is a part of game loop.
     * Here you can update your physics or input.
     *
     * @param spendTime Time that consumed from start of loop (ms)
     */
    public abstract void SimpleUpdate(long spendTime);

    protected abstract void OnWindowResize(int new_width, int new_height);


    /**
     * This function called when user tried to close window
     *
     * @return if return value is true window starts exit loop.
     */
    protected abstract boolean OnWindowCloseSignal();

    // Notice first param, it's long. GLFW return here not only one window resize
    protected void CallbackWindowResize(long window, int width, int height) {
        this.window.updateWindowSize(width, height);
        OnWindowResize(width, height);
    }

    protected void CallbackWindowCloseSignal(long window) {
        glfwSetWindowShouldClose(window, false);
        if (OnWindowCloseSignal()) {
            glfwSetWindowShouldClose(window, true);
        }
    }


}

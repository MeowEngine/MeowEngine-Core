package org.meowengine;

import lombok.extern.slf4j.Slf4j;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;


import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;


/**
 * Hello world!
 *
 */
@Slf4j
@Renderer
@ApplicationType(renderer = RendererType.GL)
public abstract class GLApplication extends LegacyApplication {


    public GLApplication() {
        super();
    }




    /**
     * This function allow you to run your game.
     */
    @Override
    public final void Run() {
        log.info("Hello LWJGL " + Version.getVersion());

        contentManager.addSource(GLApplication.class.getClassLoader());

        init();
        loop();

        log.info("Freeing callbacks and destroying window");

        if (debugProc != null) {
            debugProc.free();
        }

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();

        try {
            glfwSetErrorCallback(null).free();
        } catch (NullPointerException exception) {
            log.info("Maybe not set glfwErrorCallback");
            exception.printStackTrace();
        }


        Disposer.dispose();
        log.info("Exciting...");
    }

    @Override
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


        window = glfwCreateWindow(1280, 720, "opensb", 0, 0);
        if (window == 0)
            throw new RuntimeException("Failed to create window");

        log.info("Successfully created new window");

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            assert vidmode != null;
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }


        glfwMakeContextCurrent(window);
        glfwSetWindowSizeCallback(window, this::CallbackWindowResize);
        glfwSetWindowCloseCallback(window, this::CallbackWindowCloseSignal);

        glfwSwapInterval(1);

        glfwShowWindow(window);
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

    @Override
    protected void loop() {
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        long time = System.nanoTime();

        while (!glfwWindowShouldClose(window)) {

            update(System.nanoTime() - time);
            SimpleUpdate(System.nanoTime() - time);
            draw(System.nanoTime() - time);
            SimpleDraw(System.nanoTime() - time);
            time = System.nanoTime();


        }
        log.info("Exiting from main loop");

    }

    @Override
    protected void draw(long spendTime) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }




    @Override
    protected void update(long spendTime) {
        glfwSwapBuffers(window);
        glfwPollEvents();
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        int error = glGetError();
        if (error != GL_NO_ERROR) {
            log.error("OPENGL ERROR " + error);
        }
    }

    @Override
    protected void CallbackWindowResize(long window, int width, int height) {
        OnWindowResize(width, height);
    }

    @Override
    protected void CallbackWindowCloseSignal(long window) {
        glfwSetWindowShouldClose(window, false);
        if (OnWindowCloseSignal()) {
            glfwSetWindowShouldClose(window, true);
        }
    }
}

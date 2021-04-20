package org.meowengine;

import org.lwjgl.system.Callback;
import org.meowengine.content.ContentManager;
import org.meowengine.graphics.Node;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

public abstract class LegacyApplication {
    protected final ContentManager contentManager;
    protected final Node rootNode;
    protected long window;
    protected Camera camera;
    protected Callback debugProc;

    protected LegacyApplication() {
        this.contentManager = new ContentManager();
        this.rootNode = new Node();
    }

    public abstract void Run();

    protected void init() {}
    protected void loop() {}
    protected void draw(long spendTime) {}
    protected void update(long spendTime) {}


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

    protected abstract void OnFramebufferResize(int new_width, int new_height);


    /**
     * This function called when user tried to close window
     *
     * @return if return value is true window starts exit loop.
     */
    protected abstract boolean OnWindowCloseSignal();

    protected abstract void CallbackWindowResize(long window, int width, int height);

    protected abstract void CallbackWindowCloseSignal(long window);
}

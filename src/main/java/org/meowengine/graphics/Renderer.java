package org.meowengine.graphics;

public class Renderer {

    private static Renderer singleton;

    private boolean isRendering;

    private Renderer() {
        isRendering = false;
    }

    public static Renderer getSingleton() {
        if (singleton == null) {
            singleton = new Renderer();
        }
        return singleton;
    }

    public synchronized void begin() {
        isRendering = true;
    }

    public synchronized void drawObject(SceneObject drawable) {
        drawable.draw();
    }


    public synchronized void end() {
        isRendering = false;
    }


}

package org.meowengine.system;

@FunctionalInterface
public interface WindowResizeCallback {

    void resize(int width, int height);
}

package org.meowengine.graphics.gui;

import org.meowengine.graphics.buffers.VertexArray;
import org.meowengine.graphics.shaders.ShaderProgram;

public abstract class GuiObject {

    private VertexArray vao;

    private ShaderProgram shader;

    GuiObject() {
        vao = new VertexArray();
    }


    public void draw() {
        VertexArray.draw(vao);
    }
}

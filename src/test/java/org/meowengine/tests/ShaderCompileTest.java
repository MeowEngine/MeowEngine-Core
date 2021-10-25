package org.meowengine.tests;

import org.meowengine.Application;
import org.meowengine.graphics.shaders.Shader;
import org.meowengine.graphics.shaders.ShaderType;

public class ShaderCompileTest extends Application {


    @Override
    public void SimpleInit() {
        new Shader("TestShaders/fragment.glsl", ShaderType.FragmentShader);
        new Shader("TestShaders/vertex.glsl", ShaderType.VertexShader);
    }

    @Override
    public void SimpleDraw(long spendTime) {

    }

    @Override
    public void SimpleUpdate(long spendTime) {
        sendCloseSignal();
    }

    @Override
    protected void OnWindowResize(int new_width, int new_height) {

    }

    @Override
    protected boolean OnWindowCloseSignal() {
        return false;
    }
}

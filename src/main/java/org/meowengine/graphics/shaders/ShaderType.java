package org.meowengine.graphics.shaders;

import static org.lwjgl.opengl.GL20C.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20C.GL_VERTEX_SHADER;

public enum ShaderType {
    VertexShader {
        @Override
        public int getTypeId() {
            return GL_VERTEX_SHADER;
        }
    },
    FragmentShader {
        @Override
        public int getTypeId() {
            return GL_FRAGMENT_SHADER;
        }
    };

    public abstract int getTypeId();
}

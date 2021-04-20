package org.meowengine.graphics.buffers;

import static org.lwjgl.opengl.GL15.*;

public enum DrawType {
    StaticDraw {
        public int getDrawTypeId() {
            return GL_STATIC_DRAW;
        }
    },
    DynamicDraw {
        public int getDrawTypeId() {
            return GL_DYNAMIC_DRAW;
        }
    },
    StreamDraw {
        public int getDrawTypeId() {
            return GL_STREAM_DRAW;
        }
    };

    public abstract int getDrawTypeId();
}

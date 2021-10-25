package org.meowengine.graphics.buffers;

import lombok.Getter;
import org.lwjgl.opengl.GL33C;

import java.util.HashMap;
import java.util.Map;


import static org.lwjgl.opengl.GL33C.*;

public class VertexArray {

    @Getter
    private final int id;

    private static Map<Class<? extends Number>, Integer> type_map;

    private final DrawMode drawMode;

    static {
        type_map = new HashMap<>();
        type_map.put(Integer.class, GL_INT);
        type_map.put(Float.class, GL_FLOAT);
        type_map.put(Double.class, GL_DOUBLE);
        type_map.put(Byte.class, GL_BYTE);
        type_map.put(Short.class, GL_SHORT);
    }

    public VertexArray() {
        this.id = glGenVertexArrays();
        this.drawMode = DrawMode.TRIANGLES;
    }

    public VertexArray(DrawMode drawMode) {
        this.id = glGenVertexArrays();
        this.drawMode = drawMode;
    }

    /**
     * This method is used for working with custom shaders
     * @param bufferToUse This buffer will be linked with vertex array object
     * @param location in location for shader
     */
    public void setVertexAttrib(VertexBuffer bufferToUse,
                                int location) {
        GL33C.glBindVertexArray(id);
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, bufferToUse.getId());

        glVertexAttribPointer(location,
                bufferToUse.getDataSize(),
                type_map.get(bufferToUse.getDataType()),
                false,
                bufferToUse.getStride(), 0);
        GL33C.glEnableVertexAttribArray(location);
        GL33C.glBindVertexArray(0);
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
    }

    public static void use(VertexArray vao) {
        glBindVertexArray(vao.getId());
    }

    public static void unuse() {
        glBindVertexArray(0);
    }

    public static void draw(VertexArray vao) {
        glBindVertexArray(vao.getId());
// fixme        glDrawElements(GL_TRIANGLES, 4, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    public enum DrawMode {

        TRIANGLES {
            @Override
            public int getDrawMode() {
                return GL_TRIANGLES;
            }
        },

        LINES {
            @Override
            public int getDrawMode() {
                return GL_LINES;
            }
        };


        public abstract int getDrawMode();
    }
}

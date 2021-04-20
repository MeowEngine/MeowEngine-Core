package org.meowengine.graphics.buffers;

import lombok.Getter;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL33C;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;

/**
 * VertexBuffer is a class for adding special vertex data for VertexArrayObject
 * @param <T>
 */


public class VertexBuffer {

    @Getter
    private final int id;

    @Getter
    private int dataSize;

    @Getter
    private int stride;

    private Class dataType;

    private VertexBuffer() {
        id = GL33.glGenBuffers();
    }

    public static VertexBuffer createVertexBuffer(IntBuffer data, int dataSize, DrawType drawType) {
        VertexBuffer buffer = new VertexBuffer();
        buffer.dataSize = dataSize;
        buffer.stride = dataSize * 4;
        buffer.dataType = Integer.class;

        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, buffer.id);
        GL33C.glBufferData(GL33C.GL_ARRAY_BUFFER, data, drawType.getDrawTypeId());
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
        return buffer;
    }

    public static VertexBuffer createVertexBuffer(int[] data, int dataSize, DrawType drawType) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length).put(data);
        buffer.position(0);
        return createVertexBuffer(buffer, dataSize, drawType);
    }

    public static VertexBuffer createVertexBuffer(ByteBuffer data, int dataSize, DrawType drawType) {
        VertexBuffer buffer = new VertexBuffer();
        buffer.dataSize = dataSize;
        buffer.stride = dataSize;
        buffer.dataType = Byte.class;

        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, buffer.id);
        GL33C.glBufferData(GL33C.GL_ARRAY_BUFFER, data, drawType.getDrawTypeId());
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
        return buffer;
    }

    public static VertexBuffer createVertexBuffer(byte[] data, int dataSize, DrawType drawType) {
        ByteBuffer buffer = MemoryUtil.memAlloc(data.length).put(data);
        buffer.position(0);
        return createVertexBuffer(buffer, dataSize, drawType);
    }

    public static VertexBuffer createVertexBuffer(FloatBuffer data, int dataSize, DrawType drawType) {
        VertexBuffer buffer = new VertexBuffer();
        buffer.dataSize = dataSize;
        buffer.stride = dataSize * 4;
        buffer.dataType = Float.class;

        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, buffer.id);
        GL33C.glBufferData(GL33C.GL_ARRAY_BUFFER, data, drawType.getDrawTypeId());
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
        return buffer;
    }

    public static VertexBuffer createVertexBuffer(float[] data, int dataSize, DrawType drawType) {
        FloatBuffer buffer = MemoryUtil.memCallocFloat(data.length).put(data);
        buffer.position(0);
        return createVertexBuffer(buffer, dataSize, drawType);
    }

    public static VertexBuffer createVertexBuffer(ShortBuffer data, int dataSize, DrawType drawType) {
        VertexBuffer buffer = new VertexBuffer();
        buffer.dataSize = dataSize;
        buffer.stride = dataSize * 2;
        buffer.dataType = Short.class;

        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, buffer.id);
        GL33C.glBufferData(GL33C.GL_ARRAY_BUFFER, data, drawType.getDrawTypeId());
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
        return buffer;
    }

    public static VertexBuffer createVertexBuffer(short[] data, int dataSize, DrawType drawType) {
        ShortBuffer buffer = MemoryUtil.memAllocShort(data.length).put(data);
        buffer.position(0);
        return createVertexBuffer(buffer, dataSize, drawType);
    }

    public static VertexBuffer createVertexBuffer(DoubleBuffer data, int dataSize, DrawType drawType) {
        VertexBuffer buffer = new VertexBuffer();
        buffer.dataSize = dataSize;
        buffer.stride = dataSize * 8;
        buffer.dataType = Double.class;

        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, buffer.id);
        GL33C.glBufferData(GL33C.GL_ARRAY_BUFFER, data, drawType.getDrawTypeId());
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
        return buffer;
    }

    public static VertexBuffer createVertexBuffer(double[] data, int dataSize, DrawType drawType) {
        DoubleBuffer buffer = MemoryUtil.memAllocDouble(data.length).put(data);
        buffer.position(0);
        return createVertexBuffer(buffer, dataSize, drawType);
    }

    Class getDataType() {
        return dataType;
    }

    public static void use(VertexBuffer buffer) {
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, buffer.getId());
    }

    public static void unuse() {
        GL33C.glBindBuffer(GL33C.GL_ARRAY_BUFFER, 0);
    }







}

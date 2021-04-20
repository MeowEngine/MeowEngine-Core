package org.meowengine.graphics.buffers;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL33C;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;


/**
 * This class is used for economy memory when drawing vertex arrays.
 * For more information see <a href="https://learnopengl.com/Getting-started/Hello-Triangle">Buffer objects</a>
 */
public class ElementBuffer {

    @Getter
    private final int id;

    @Getter
    @Setter
    private DrawType drawType;

    /**
     * Creates Element Buffer for using it in OpenGL
     * </br>
     * For more information see <a href="https://www.khronos.org/opengl/wiki/Buffer_Object">Buffer object</a>
     * @param drawType <div>Specify one of the draw types declared by opengl.
     *                 <ul>
     *                  <li>DrawType.StaticDraw (GL_STATIC_DRAW)</li>
     *                  <li>DrawType.DynamicDraw (GL_DYNAMIC_DRAW)</li>
     *                  <li>DrawType.StreamDraw (GL_STREAM_DRAW)</li>
     *                 </ul></div>
     */
    public ElementBuffer(DrawType drawType) {
        id = GL33.glGenBuffers();
        this.drawType = drawType;
    }

    /**
     * Creates Element Buffer for using it in OpenGL
     * </br>
     * For more information see <a href="https://www.khronos.org/opengl/wiki/Buffer_Object">Buffer object</a>
     * @param drawType <div>Specify one of the draw types declared by opengl.
     *                 <ul>
     *                  <li>DrawType.StaticDraw (GL_STATIC_DRAW)</li>
     *                  <li>DrawType.DynamicDraw (GL_DYNAMIC_DRAW)</li>
     *                  <li>DrawType.StreamDraw (GL_STREAM_DRAW)</li>
     *                 </ul></div>
     * @param dataArray <div>Source of element buffer</div>
     */
    public ElementBuffer(DrawType drawType, int[] dataArray) {
        id = GL33.glGenBuffers();
        this.drawType = drawType;
        this.setData(dataArray);
    }

    /**
     * Creates Element Buffer for using it in OpenGL
     * </br>
     * For more information see <a href="https://www.khronos.org/opengl/wiki/Buffer_Object">Buffer object</a>
     * @param drawType <div>Specify one of the draw types declared by opengl.
     *                 <ul>
     *                  <li>DrawType.StaticDraw (GL_STATIC_DRAW)</li>
     *                  <li>DrawType.DynamicDraw (GL_DYNAMIC_DRAW)</li>
     *                  <li>DrawType.StreamDraw (GL_STREAM_DRAW)</li>
     *                 </ul></div>
     * @param dataBuffer <div>Source of element buffer</div>
     */
    public ElementBuffer(DrawType drawType, IntBuffer dataBuffer) {
        id = GL33.glGenBuffers();
        this.drawType = drawType;
        this.setData(dataBuffer);
    }

    /**
     * Inserting data into element buffer
     * @param dataArray Source of element buffer
     */
    public synchronized void setData(int[] dataArray) {
        installBuffer(MemoryUtil.memCallocInt(dataArray.length).put(dataArray).position(0));
    }

    /**
     * Inserting data into element buffer
     * @param dataBuffer Source of element buffer
     */
    public synchronized void setData(IntBuffer dataBuffer) {
        installBuffer(dataBuffer);
    }

    //TODO gl threadsafe
    private synchronized void installBuffer(IntBuffer dataBuffer) {
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, id);
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, dataBuffer, drawType.getDrawTypeId());
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public static void use(ElementBuffer buffer) {
        GL33C.glBindBuffer(GL33C.GL_ELEMENT_ARRAY_BUFFER, buffer.getId());
    }

    public static void unuse() {
        GL33C.glBindBuffer(GL33C.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

}

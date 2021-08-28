package org.meowengine.graphics.shaders;

import lombok.extern.slf4j.Slf4j;
import org.joml.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL33C.*;

@Slf4j
public class ShaderProgram {

    private final int id;

    public ShaderProgram() {
        id = glCreateProgram();
    }

    public final ShaderProgram attachShader(Shader shader) {
        glAttachShader(id, shader.getId());
        glLinkProgram(id);
        int status = glGetProgrami(id, GL_LINK_STATUS);
        if (status != 1) {
            log.error("Unsuccessful shader program link \n{}", glGetProgramInfoLog(id));
        } else {
            if (log.isTraceEnabled())
                log.trace("ShaderProgram link performed");
        }

        return this;
    }

    protected int getId() {
        return id;
    }

    protected final void use() {
        glUseProgram(id);
    }

    protected final void unuse() {
        glUseProgram(0);
    }

    protected final void injectUniform(String uniformName, int value) {
        glUniform1i(glGetUniformLocation(id, uniformName), value);
    }

    protected final void injectUniform(String uniformName, float value) {
        glUniform1f(glGetUniformLocation(id, uniformName), value);
    }

    protected final void injectUniform(String uniformName, int value0, int value1) {
        glUniform2i(glGetUniformLocation(id, uniformName), value0, value1);
    }

    protected final void injectUniform(String uniformName, Vector2i value) {
        glUniform2i(glGetUniformLocation(id, uniformName), value.x(), value.y());
    }

    protected final void injectUniform(String uniformName, float value0, float value1) {
        glUniform2f(glGetUniformLocation(id, uniformName), value0, value1);
    }

    protected final void injectUniform(String uniformName, Vector2f value) {
        glUniform2f(glGetUniformLocation(id, uniformName), value.x(), value.y());
    }

    protected final void injectUniform(String uniformName, int value0, int value1, int value2) {
        glUniform3i(glGetUniformLocation(id, uniformName), value0, value1, value2);
    }

    protected final void injectUniform(String uniformName, Vector3i value) {
        glUniform3i(glGetUniformLocation(id, uniformName), value.x(), value.y(), value.z());
    }

    protected final void injectUniform(String uniformName, float value0, float value1, float value2) {
        glUniform3f(glGetUniformLocation(id, uniformName), value0, value1, value2);
    }

    protected final void injectUniform(String uniformName, Vector3f value) {
        glUniform3f(glGetUniformLocation(id, uniformName), value.x(), value.y(), value.z());
    }

    protected final void injectUniform(String uniformName, int value0, int value1, int value2, int value3) {
        glUniform4i(glGetUniformLocation(id, uniformName), value0, value1, value2, value3);
    }

    protected final void injectUniform(String uniformName, Vector3i value012, int value3) {
        glUniform4i(glGetUniformLocation(id, uniformName), value012.x(), value012.y(), value012.z(), value3);
    }

    protected final void injectUniform(String uniformName, Vector4i value) {
        glUniform4i(glGetUniformLocation(id, uniformName), value.x(), value.y(), value.z(), value.w());
    }

    protected final void injectUniform(String uniformName, float value0, float value1, float value2, float value3) {
        glUniform4f(glGetUniformLocation(id, uniformName), value0, value1, value2, value3);
    }

    protected final void injectUniform(String uniformName, Vector3f value012, float value3) {
        glUniform4f(glGetUniformLocation(id, uniformName), value012.x(), value012.y(), value012.z(), value3);
    }

    protected final void injectUniform(String uniformName, Vector4f value) {
        glUniform4f(glGetUniformLocation(id, uniformName), value.x(), value.y(), value.z(), value.w());
    }

    protected final void injectUniform(String uniformName, Matrix2f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(4);
        value.get(fb);
        glUniformMatrix2fv(glGetUniformLocation(id, uniformName), false, fb);
        MemoryUtil.memFree(fb);
    }

    protected final void injectUniform(String uniformName, Matrix3f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(9);
        value.get(fb);
        glUniformMatrix3fv(glGetUniformLocation(id, uniformName), false, fb);
        MemoryUtil.memFree(fb);
    }

    protected final void injectUniform(String uniformName, Matrix3x2f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(6);
        value.get(fb);
        glUniformMatrix3x2fv(glGetUniformLocation(id, uniformName), false, fb);
        MemoryUtil.memFree(fb);
    }

    protected final void injectUniform(String uniformName, Matrix4f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(16);
        value.get(fb);
        glUniformMatrix4fv(glGetUniformLocation(id, uniformName), false, fb);
        MemoryUtil.memFree(fb);
    }

    protected final void injectUniform(String uniformName, Matrix4x3f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(12);
        value.get(fb);
        glUniformMatrix4fv(glGetUniformLocation(id, uniformName), false, fb);
        MemoryUtil.memFree(fb);
    }


}

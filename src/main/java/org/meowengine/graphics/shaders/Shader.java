package org.meowengine.graphics.shaders;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.opengl.GL33;
import org.meowengine.content.Resource;
import org.meowengine.exceptions.DisposeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
public class Shader extends Resource {

    private int id;

    @SneakyThrows
    public Shader(File file, ShaderType shaderType) {
        super(file);
        id = GL33.glCreateShader(shaderType.getTypeId());

        var sourceStream = new FileInputStream(file);
        Scanner scanner = new Scanner(sourceStream).useDelimiter("\\A");
        String source = scanner.hasNext() ? scanner.next() : "";

        shaderCompile(source);
    }

    public Shader(String file, ShaderType shaderType) {
        super(file);
        id = GL33.glCreateShader(shaderType.getTypeId());

        var sourceStream = getClass().getClassLoader().getResourceAsStream(file);
        //noinspection ConstantConditions
        Scanner scanner = new Scanner(sourceStream).useDelimiter("\\A");
        String source = scanner.hasNext() ? scanner.next() : "";

        shaderCompile(source);
    }


    private void shaderCompile(String source) {
        GL33.glShaderSource(id,source);
        GL33.glCompileShader(id);
        int err = GL33.glGetShaderi(id, GL33.GL_COMPILE_STATUS);
        if (err != 1) {
            log.error("Failed to compile shader {}", GL33.glGetShaderInfoLog(id));
        } else {
            if (log.isTraceEnabled())
                log.trace("Shader compile performed");
        }
    }

    int getId() {
        return id;
    }

    boolean isCompiled() {
        return GL33.glGetShaderi(id, GL33.GL_COMPILE_STATUS) == 1;
    }

    @Override
    public void dispose() throws DisposeException {

    }
}

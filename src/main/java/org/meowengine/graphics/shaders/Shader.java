package org.meowengine.graphics.shaders;

import lombok.extern.slf4j.Slf4j;
import org.meowengine.Disposer;
import org.meowengine.content.Asset;
import org.meowengine.content.ContentManager;

import java.util.Scanner;

import static org.lwjgl.opengl.GL33C.*;

@Slf4j
public class Shader extends Asset {

    private final int id;

    public Shader(ContentManager contentManager, String location, ShaderType shaderType) {
        super(contentManager, location);
        String source;

        var sourceStream = contentManager.getResourceAsStream(location);
        if (sourceStream.isPresent()) {
            Scanner scanner = new Scanner(sourceStream.get()).useDelimiter("\\A");
            source = scanner.hasNext() ? scanner.next() : "";
        } else {
            log.error("Failed to load shader in " + location);
            log.error("File not found");
            throw new RuntimeException("File not found");
        }

        id = glCreateShader(shaderType.getTypeId());
        Disposer.addShader(id);
        glShaderSource(id, source);
        glCompileShader(id);
        int status = glGetShaderi(id, GL_COMPILE_STATUS);
        if (status != 1) {
            log.error("Failed to compile shader");
            log.error(glGetShaderInfoLog(id));
        } else {
            log.debug("Successful shader compiling [id = {}]", id);
        }
    }

    final int getId() {
        return id;
    }


}

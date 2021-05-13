package org.meowengine.graphics.shaders;

import lombok.extern.slf4j.Slf4j;
import org.meowengine.content.Resource;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Shader extends Resource {


    public Shader(File file) {
        super(file);

    }

    public Shader(String file, ClassLoader classLoader) {
        super(file, classLoader);


    }

    //FIXME
    //private final int id;
    /*
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
    }*/

    final int getId() {
        //return id;
        return 0;
    }


    @Override
    public void close() {

    }
}

package org.meowengine.graphics;

import org.meowengine.content.Resource;
import org.meowengine.exceptions.DisposeException;

import java.io.File;
import java.io.IOException;

public class Material extends Resource {

    protected Material(File file) {
        super(file);
    }

    protected Material(String file) {
        super(file);
    }

    //private final Texture texture;
    //private final ShaderProgram shaderProgram;

    //FIXME

    @Override
    public void dispose() throws DisposeException {

    }
}

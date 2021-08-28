package org.meowengine.exceptions;

import java.io.File;

public class ResourceNotFound extends IllegalStateException {

    public ResourceNotFound(File file) {
        super("Static resource " + file.getAbsolutePath() + " not found");
    }

    public ResourceNotFound(String file) {
        super("Classpath resource " + file + " not found in classpath");
    }

}

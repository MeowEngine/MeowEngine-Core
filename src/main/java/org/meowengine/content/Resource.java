package org.meowengine.content;

import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.CallbackI;
import org.meowengine.exceptions.ResourceNotFound;

import java.io.File;
import java.util.Optional;

@Slf4j
public abstract class Resource {

    /**
     * Loads resource as static resource from local directory
     * @param file
     * @see Resource#Resource(String, ClassLoader) for loading from classpath
     */
    protected Resource(File file) {
        if (!file.exists()) {
            throw new ResourceNotFound(file);
        }
    }

    /**
     * Loads resource as static resource from local directory
     * @param file filename
     * @see Resource#Resource(String, ClassLoader) for loading from classpath
     */
    protected Resource(String file) {
        File res = new File(file);
        if (!res.exists()) {
            throw new ResourceNotFound(res);
        }
    }

    /**
     * Loads resource from classpath
     * @param file filename in classpath
     * @param classLoader classpath with resource
     */
    protected Resource(String file, ClassLoader classLoader) {
        var opt = Optional.ofNullable(classLoader.getResourceAsStream(file));
        if (opt.isEmpty()) {
            throw new ResourceNotFound(file, classLoader);
        }
    }
}

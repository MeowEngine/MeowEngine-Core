package org.meowengine.content;

import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.CallbackI;
import org.meowengine.exceptions.ResourceNotFound;

import java.io.Closeable;
import java.io.File;
import java.util.Optional;

@Slf4j
public abstract class Resource extends Disposable {

    /**
     * Loads resource as static resource from local directory
     * @param file File
     */
    protected Resource(File file) {
        if (!file.exists()) {
            throw new ResourceNotFound(file);
        }
    }

    /**
     * Loads resource as static resource from classpath
     * @param file filepath
     */
    protected Resource(String file) {
         if (getClass().getClassLoader().getResourceAsStream(file) == null) {
             throw new ResourceNotFound(file);
         }
    }
    
}

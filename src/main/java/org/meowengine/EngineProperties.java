package org.meowengine;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class EngineProperties {

    private static final Map<String, String> storedProperties;

    static {
        storedProperties = new HashMap<>();
        try {
            log.debug("Loading engine properties file");
            Properties properties = new Properties();
            properties.load(EngineProperties.class.getClassLoader().getResourceAsStream("engine.properties"));
            properties.forEach((key, val) -> storedProperties.put(key.toString(), val.toString()));
        } catch (IOException ex) {
            log.error("Failed to load engine properties file");
            System.exit(-1);
        }
    }

    public static String getProperty(String key) {
        return storedProperties.get(key);
    }

}

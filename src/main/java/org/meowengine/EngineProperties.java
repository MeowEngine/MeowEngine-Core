package org.meowengine;

import lombok.extern.slf4j.Slf4j;
import org.meowengine.di.Component;
import org.meowengine.di.Property;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@Component
public class EngineProperties {

    @Property("org.meowengine.windowName")
    public String windowName;

    public EngineProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("engine.properties"));

        } catch (IOException | NullPointerException exception) {
            log.warn("Error while reading engine base properties file. Maybe it doesnt exists at all? Loading engine defined base properties");

        }
    }


}

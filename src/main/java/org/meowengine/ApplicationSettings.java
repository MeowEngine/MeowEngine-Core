package org.meowengine;

import ch.qos.logback.classic.Logger;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Data
@Builder
public class ApplicationSettings {

    public String windowName;
    public int windowHeight;
    public int windowWidth;

    public static ApplicationSettings getDefault() {
        try {
            return ApplicationSettings.builder()
                    .windowName(EngineProperties.getProperty("meowengine.ApplicationSettings.windowName"))
                    .windowWidth(Integer.parseInt(EngineProperties.getProperty("meowengine.ApplicationSettings.windowWidth")))
                    .windowHeight(Integer.parseInt(EngineProperties.getProperty("meowengine.ApplicationSettings.windowHeight")))
                    .build();
        } catch (NumberFormatException exception) {
            LoggerFactory.getLogger(ApplicationSettings.class).error("Failed to parse number in engine base properties file");
            System.exit(-1);
            return null;
        }
    }

}

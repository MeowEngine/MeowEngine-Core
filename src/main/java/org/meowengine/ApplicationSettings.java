package org.meowengine;

import ch.qos.logback.classic.Logger;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.meowengine.system.WindowingSystem;
import org.slf4j.LoggerFactory;

@Slf4j
@Data
@Builder
public class ApplicationSettings {

    public String windowName;
    public int windowHeight;
    public int windowWidth;
    public WindowingSystem windowingSystem;

    public static ApplicationSettings getDefault() {
        try {
            var defaultSettings = ApplicationSettings.builder()
                    .windowName(EngineProperties.getProperty("meowengine.ApplicationSettings.windowName"))
                    .windowWidth(Integer.parseInt(EngineProperties.getProperty("meowengine.ApplicationSettings.windowWidth")))
                    .windowHeight(Integer.parseInt(EngineProperties.getProperty("meowengine.ApplicationSettings.windowHeight")));

            try {
                Class.forName(EngineProperties.getProperty("meowengine.ApplicationSettings.windowingSystem"))
                        .getDeclaredMethod("getInstance", (Class<?>) null);
            } catch (ClassNotFoundException e) {
                log.error("Base windowing system isn`t defined. Developer a moran");
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                log.error("Base windowing system doesnt have or it did not found getInstance method. Developer a moran");
                e.printStackTrace();
            }

            return defaultSettings.build();
        } catch (NumberFormatException exception) {
            log.error("Failed to parse number in engine base properties file");
            System.exit(-1);
            return null;
        }
    }
}

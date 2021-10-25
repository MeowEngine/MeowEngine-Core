package org.meowengine.graphics.gui;

import lombok.extern.slf4j.Slf4j;
import org.meowengine.system.Window;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GuiDrawer {

    private Map<GuiObject, PositionData> elementsToDraw;

    private final Window window;

    public GuiDrawer(Window window) {
        this.window = window;
        elementsToDraw = new HashMap<>();
    }


    /**
     * Attaches gui element to screen in specific position
     * @param obj
     * @param xCoord
     * @param yCoord
     */
    public void attachGuiObject(GuiObject obj,
                                int xCoord,
                                int yCoord) {

        PositionData positionData = new PositionData(xCoord, yCoord);
        elementsToDraw.put(obj, positionData);
    }

    private class PositionData {

        int x;
        int y;

        private PositionData(int x, int y) {
            this.x = x;
            this.y = y;

            if (log.isWarnEnabled()) {
                if (checkElementPositionInWindow(this)) {
                    log.debug("Gui Element goes out of the window boundaries");
                }
            }
        }
    }

    private boolean checkElementPositionInWindow(PositionData positionData) {
        if (positionData.x > window.getWindowSize().x || positionData.x < 0) {
            return false;
        }
        if (positionData.y > window.getWindowSize().y || positionData.y < 0) {
            return false;
        }

        return true;
    }

    public void render() {
        elementsToDraw.forEach((sceneObjects, positionData) -> {
            sceneObjects.draw();
        });
    }
}

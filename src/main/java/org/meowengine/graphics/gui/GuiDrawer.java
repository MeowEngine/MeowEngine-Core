package org.meowengine.graphics.gui;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class GuiDrawer {

    private Map<GuiObject, PositionData> elementsToDraw;


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

            }
        }
    }
}

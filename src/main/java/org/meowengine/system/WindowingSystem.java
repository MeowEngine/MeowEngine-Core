package org.meowengine.system;

import org.meowengine.ApplicationSettings;
import org.meowengine.content.Disposable;

public abstract class WindowingSystem {

    protected static WindowingSystem singleton;

    protected WindowingSystem() {
        singleton = null;
    }


    public static WindowingSystem getInstance() {
        return singleton;
    }

    /**
     * Creates window with basic parameters
     * @return window obj
     */
    public abstract Window createWindow();

    /**
     * Creates window with given settings
     * @see ApplicationSettings
     * @return window obj
     */
    public abstract Window createWindow(ApplicationSettings settings);

    /**
     * Make window invisible
     * @param window window to make invisible
     */
    public abstract void hideWindow(Window window);

    /**
     * Make window visible
     * @param window to make visible
     */
    public abstract void showWindow(Window window);

    /**
     * Changes window visibility
     * @param window to change visibility
     * @return new state of window visibility
     */
    public abstract boolean toggleWindowVisibility(Window window);

    /**
     * Makes window current for windowingSystem context
     */
    public abstract void makeWindowCurrent(Window window);

    /**
     * Checks if there was a request to close the window
     * @return true if it should be closed
     */
    public abstract boolean isWindowShouldClosed(Window window);

    /**
     * Sends close signal to window or application, depends on your logic
     * @param r true if u want to send close signal
     */
    public abstract void setWindowCloseRequest(Window window, boolean r);

    /**
     * Shutdowns windowing system and releases it native resources
     */
    public abstract void terminate();
}

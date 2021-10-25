package org.meowengine.tests;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.meowengine.Application;
import org.meowengine.graphics.buffers.VertexArray;
import org.meowengine.graphics.gui.Rectangle;
import org.meowengine.graphics.shaders.Shader;
import org.meowengine.graphics.shaders.ShaderType;
import org.slf4j.LoggerFactory;

public class SimpleRun extends Application {

    private ListAppender<ILoggingEvent> logWatcher;

    @BeforeAll
    void setup() {
        this.logWatcher = new ListAppender<>();
        this.logWatcher.start();
        ((Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).addAppender(logWatcher);
    }

    @Test
    public void simpleRun() {
        new SimpleRun().Run();
    }

    @Override
    public void SimpleInit() {

    }

    @Override
    public void SimpleDraw(long spendTime) {

    }

    @Override
    public void SimpleUpdate(long spendTime) {
//        sendCloseSignal();
    }

    @Override
    protected void OnWindowResize(int new_width, int new_height) {

    }

    @Override
    protected boolean OnWindowCloseSignal() {
        return true;
    }
}

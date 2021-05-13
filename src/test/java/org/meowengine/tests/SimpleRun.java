package org.meowengine.tests;

import org.junit.Test;
import org.meowengine.Application;

public class SimpleRun extends Application {

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

    }

    @Override
    protected void OnWindowResize(int new_width, int new_height) {

    }

    @Override
    protected void OnFramebufferResize(int new_width, int new_height) {

    }

    @Override
    protected boolean OnWindowCloseSignal() {
        return true;
    }
}

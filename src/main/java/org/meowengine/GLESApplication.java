package org.meowengine;

@Renderer
@ApplicationType(renderer = RendererType.GLES)
public abstract class GLESApplication extends LegacyApplication {
    protected GLESApplication() {
        super();
    }

    @Override
    public void Run() {

    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void loop() {
        super.loop();
    }

    @Override
    protected void draw(long spendTime) {
        super.draw(spendTime);
    }

    @Override
    protected void update(long spendTime) {
        super.update(spendTime);
    }

    @Override
    protected void CallbackWindowResize(long window, int width, int height) {

    }

    @Override
    protected void CallbackWindowCloseSignal(long window) {

    }
}

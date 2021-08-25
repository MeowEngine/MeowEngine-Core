package org.meowengine.content;

import org.meowengine.Disposer;
import org.meowengine.exceptions.DisposeException;

public abstract class Disposable {

    protected Disposable() {
        Disposer.addDisposable(this);
    }

    public abstract void dispose() throws DisposeException;
}

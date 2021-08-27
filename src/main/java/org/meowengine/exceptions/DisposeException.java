package org.meowengine.exceptions;

import org.meowengine.content.Disposable;

public class DisposeException extends Exception {

    public DisposeException(Disposable disposable, String message) {
        super(String.format("Dispose occurred with %s of %s. %s", disposable.toString(),
                disposable.getClass().getName(),
                message));
    }
}

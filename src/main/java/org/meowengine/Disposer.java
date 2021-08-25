package org.meowengine;

import org.lwjgl.opengl.GL33;
import org.meowengine.content.Disposable;
import org.meowengine.content.Resource;
import org.meowengine.exceptions.DisposeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Disposer {

    private Disposer() {}

    private static List<Disposable> createdItems = new ArrayList<>();

    public static void addDisposable(Disposable disposable) {
        createdItems.add(disposable);
    }

    static void dispose() {
        createdItems.forEach(resource -> {
            try {
                resource.dispose();
            } catch (DisposeException exception) {
                exception.printStackTrace();
            }
        });

    }


}

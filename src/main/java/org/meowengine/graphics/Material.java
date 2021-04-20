package org.meowengine.graphics;

import org.meowengine.content.Asset;
import org.meowengine.content.ContentManager;
import org.meowengine.graphics.shaders.ShaderProgram;

public class Material extends Asset {

    private final Texture texture;
    private final ShaderProgram shaderProgram;

    public Material(ContentManager contentManager, String location) {
        super(contentManager, location);
        texture = null;
        shaderProgram = null;
    }
}

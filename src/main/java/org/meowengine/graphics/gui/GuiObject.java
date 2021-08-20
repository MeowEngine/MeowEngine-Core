package org.meowengine.graphics.gui;

import org.joml.Vector4i;
import org.meowengine.graphics.SceneObject;

public abstract class GuiObject extends SceneObject {

    public Vector4i margin = new Vector4i(0);
    public Vector4i padding = new Vector4i(0);
    public int border = 0;

//    public Material borderMaterial;



}

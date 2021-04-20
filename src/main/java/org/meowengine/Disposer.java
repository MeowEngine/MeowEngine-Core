package org.meowengine;

import org.lwjgl.opengl.GL33;

import java.util.ArrayList;
import java.util.List;

public class Disposer {
    private static List<Integer> shaderList = new ArrayList<>();
    private static List<Integer> bufferList = new ArrayList<>();
    private static List<Integer> textureList = new ArrayList<>();

    public synchronized static void dispose()
    {
        shaderList.forEach(GL33::glDeleteShader);
        shaderList.clear();
        bufferList.forEach(GL33::glDeleteBuffers);
        bufferList.clear();
        textureList.forEach(GL33::glDeleteTextures);
        textureList.clear();
    }

    public static void addShader(Integer integer) { shaderList.add(integer); }
    public static void addBuffer(Integer integer) { bufferList.add(integer); }
    public static void addTexture(Integer integer) { textureList.add(integer); }

}

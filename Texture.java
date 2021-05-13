package org.meowengine.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joml.Vector4i;
import org.meowengine.content.Resource;

@Slf4j
//FIXME
public class Texture extends Resource {

    //private final int openglId;

    //private final int frameCount;
    //private final Vector2i textureSize;
    //private final List<Frame> frameList;

    @Data
    @AllArgsConstructor
    public static class Frame {
        private final Vector4i frameSize;
        private final boolean rotated;
        private final boolean trimmed;
        private final int duration;
    }


    /*
    public Texture(ContentManager contentManager, String location) {
        super(contentManager, location);

        var jsonDeclaration = contentManager.getResourceDeclaration(location).getAsJsonObject();

        var jsonFrames = jsonDeclaration.getAsJsonArray("frames");
        frameCount = jsonFrames.size();
        textureSize = new Vector2i(
                jsonDeclaration.getAsJsonObject("size").getAsJsonPrimitive("w").getAsInt(),
                jsonDeclaration.getAsJsonObject("size").getAsJsonPrimitive("h").getAsInt()
        );

        frameList = new ArrayList<>(frameCount);
        jsonFrames.forEach(jsonElement -> {
            JsonObject currentFrameSizes = jsonElement.getAsJsonObject().getAsJsonObject("frame");
            frameList.add(
                    new Frame(
                            new Vector4i(
                                    currentFrameSizes.getAsJsonPrimitive("x").getAsInt(),
                                    currentFrameSizes.getAsJsonPrimitive("y").getAsInt(),
                                    currentFrameSizes.getAsJsonPrimitive("w").getAsInt(),
                                    currentFrameSizes.getAsJsonPrimitive("h").getAsInt()
                            ),
                            jsonElement.getAsJsonObject().getAsJsonPrimitive("rotated").getAsBoolean(),
                            jsonElement.getAsJsonObject().getAsJsonPrimitive("trimmed").getAsBoolean(),
                            jsonElement.getAsJsonObject().getAsJsonPrimitive("duration").getAsInt()
                    )
            );
        });


        openglId = glGenTextures();
         /*
        Disposer.addTexture(openglId);
        try {
            PNGDecoder decoder = new PNGDecoder(contentManager.getResourceAsStream(location).get());
            int bytePerPixel = 3;
            if (decoder.hasAlpha()) bytePerPixel++;

            ByteBuffer buffer = MemoryUtil.memCalloc(decoder.getHeight() * decoder.getWidth() * bytePerPixel);
            if (decoder.hasAlpha()) {
                decoder.decode(buffer, decoder.getWidth()*bytePerPixel, PNGDecoder.Format.RGBA);
            }
            else {
                decoder.decode(buffer, decoder.getWidth()*bytePerPixel, PNGDecoder.Format.RGB);
            }

            buffer.flip();

            glBindTexture(GL_TEXTURE_2D, openglId);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            if (decoder.hasAlpha()) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            } else {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, decoder.getWidth(), decoder.getHeight(), 0, GL_RGB, GL_UNSIGNED_BYTE, buffer);
            }

        }
        catch (IOException exception) {
            log.error("Failed to load texture");
            log.error(exception.getMessage());
            throw new RuntimeException("Failed to load Texture");
        }


    }
    */


    //public int getId() { return openglId; }


}

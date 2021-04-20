package org.meowengine;

import lombok.Getter;
import org.joml.Matrix4f;

public class Camera {

    private Matrix4f view;
    private Matrix4f projection;

    @Getter
    private boolean isPerspective;

    private Camera() {
        view = new Matrix4f().identity();
    }

    protected Matrix4f getViewMatrix() {
        return view;
    }

    protected Matrix4f getProjectionMatrix() {
        return projection;
    }


    public static Camera getPerspectiveCameraInstance(float fov, float aspectRatio, float near, float far) {
        Camera camera = new Camera();
        camera.isPerspective = true;
        camera.projection = new Matrix4f().perspective(fov, aspectRatio, near, far);

        return camera;
    }

    public static Camera getOrthographicCameraInstance(float left, float right,
                                                       float bottom, float top,
                                                       float near, float far) {
        Camera camera = new Camera();
        camera.isPerspective = false;
        camera.projection = new Matrix4f().ortho(left, right, bottom, top, near, far);

        return camera;
    }

}

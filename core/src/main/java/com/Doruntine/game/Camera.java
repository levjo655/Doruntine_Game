package com.Doruntine.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;

public class Camera {
    private OrthographicCamera camera;
    private float smoothSpeed = 5f; // higher = faster
    private float worldWidth = 1600; // size of world
    private float worldHeight = 1200;

    public Camera(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.setToOrtho(false);
        camera.update();
    }

    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        // Target = player center
        Vector3 target = new Vector3(playerX + playerWidth / 2f, playerY + playerHeight / 2f, 0);

        // Smoothly move camera
        camera.position.lerp(target, smoothSpeed * Gdx.graphics.getDeltaTime());

        // Clamp to world bounds
        float halfW = camera.viewportWidth / 2f;
        float halfH = camera.viewportHeight / 2f;
        camera.position.x = Math.max(halfW, Math.min(camera.position.x, worldWidth - halfW));
        camera.position.y = Math.max(halfH, Math.min(camera.position.y, worldHeight - halfH));

        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setWorldSize(float width, float height) {
        worldWidth = width;
        worldHeight = height;
    }

    public void setSmoothSpeed(float speed) {
        smoothSpeed = speed;
    }
}

package com.Doruntine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class My2DGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800, 600);
        camera.update();

        shapeRenderer = new ShapeRenderer();

    }
    @Override
    public void render() {

        Gdx.gl.glClearColor(0.2f, 0.6f, 0.2f, 1); // Green grass
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);

        // Vertical grid lines
        for (int x = 0; x <= 800; x += 32) {
            shapeRenderer.line(x, 0, x, 600);
        }

        // Horizontal grid lines
        for (int y = 0; y <= 600; y += 32) {
            shapeRenderer.line(0, y, 800, y);
        }

        shapeRenderer.end();
    }


    @Override
    public void dispose() {
        shapeRenderer.dispose();

    }
}

package com.Doruntine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class My2DGame extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Player player;
    private Camera gameCamera;
    private Texture mapTexture;

    @Override
    public void create() {
        gameCamera = new Camera(800, 600); // your smooth following camera
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        mapTexture = new Texture("demo_map.png");
        player = new Player(400, 300, 96, 96); // spawn player at center
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        handleInput(delta);

        // Update camera to follow player
        gameCamera.update(player.x, player.y, player.width, player.height);

        // Set projection matrices
        shapeRenderer.setProjectionMatrix(gameCamera.getCamera().combined);
        batch.setProjectionMatrix(gameCamera.getCamera().combined);

        // Clear screen
        Gdx.gl.glClearColor(0.2f, 0.6f, 0.2f, 1); // green grass
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw grid
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        for (int x = 0; x <= 1600; x += 32) shapeRenderer.line(x, 0, x, 1200); // world size 1600x1200
        for (int y = 0; y <= 1200; y += 32) shapeRenderer.line(0, y, 1600, y);
        shapeRenderer.end();

        // Draw player
        batch.begin();
        batch.draw(mapTexture,0,0);
        player.render(batch);
        batch.end();
    }

    private void handleInput(float delta) {
        float dx = 0, dy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) dx = 1;

        player.move(dx, dy, delta);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        player.dispose();
    }
}

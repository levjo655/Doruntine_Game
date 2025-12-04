package com.Doruntine.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

    public float x, y;
    public float width, height;
    public float speed = 200;

    private Texture sheet;
    private Animation<TextureRegion> walkAnimation;
    private TextureRegion idleFrame;

    private float elapsedTime = 0;
    private boolean isMoving = false;

    public Player(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        sheet = new Texture("Characters/MC/undead_soldier.png");

        // Extract 4 frames horizontally
        TextureRegion[] frames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            frames[i] = new TextureRegion(sheet, i * 32, 0, 32, 32);
        }

        walkAnimation = new Animation<>(0.25f, frames);
        idleFrame = frames[3]; // front frame
    }

    public void render(SpriteBatch batch) {
        if (isMoving) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            batch.draw(walkAnimation.getKeyFrame(elapsedTime, true), x, y, width, height);
        } else {
            batch.draw(idleFrame, x, y, width, height);
        }
    }

    public void move(float dx, float dy, float delta) {
        isMoving = (dx != 0 || dy != 0);

        x += dx * speed * delta;
        y += dy * speed * delta;
    }

    public void dispose() {
        sheet.dispose();
    }
}

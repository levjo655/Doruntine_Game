package com.Doruntine.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    public float x,y;   // Position
    public float width, height; // Size
    public float speed = 200; // Pixel per second
    private Texture texture;


    public Player (float x, float y, float width, float height) {
        this.x = x;
        this.y= y;
        this.width= width;
        this.height= height;

        // loading the sprite / player img

        texture = new Texture("demo_player.png");

    }

    public void render (SpriteBatch batch){

       batch.draw(texture, x,y,width, height);
    }
    void move(float dx, float dy, float delta){
        x += dx * speed * delta;
        y += dy * speed * delta;
    }
    public void dispose (){
        texture.dispose();
    }
}

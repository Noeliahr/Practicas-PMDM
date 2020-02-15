/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cescristorey;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import static com.cescristorey.Moneda.loadTexture;

/**
 *
 * @author Noelia
 */
public class Gato extends Image{
    TextureRegion stand, jump;
    Animation walk;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    TiledMapTileLayer layer;

    final float GRAVITY = -2.5f;
    final float MAX_VELOCITY = 0.5f;
    final float DAMPING = 0f;

    public Gato() {
        final float width = 20;
        final float height = 30;
        this.setSize(1, height / width);

        Texture gatitos = new Texture("gatos1.png");
        TextureRegion[][] grid = TextureRegion.split(gatitos, (int) width, (int) height);

        walk = new Animation(0.15f, grid[0][0], grid[0][1]);
        walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }
    public void act(float delta) {
        time = time + delta;
        
        yVelocity = yVelocity + MAX_VELOCITY;
        xVelocity = MAX_VELOCITY;

        yVelocity = yVelocity + GRAVITY;

        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;
        
        if (canMoveTo(x + xChange, y, false) == false) {
            xVelocity = xChange = 0;
        }

        if (canMoveTo(x, y + yChange, yVelocity > 0) == false) {
            canJump = yVelocity < 0;
            yVelocity = yChange = 0;
        }
        
        
        if(isFacingRight==true){
            if (x + xChange < 7){
               xChange = xVelocity * delta;
               this.setPosition(x + xChange, y + yChange); 
            }else {
                isFacingRight = false;
            }
        }else{
            if(x+xChange > 1){
               xChange = -1*xVelocity * delta;
               this.setPosition(x +xChange, y + yChange);
            }else {
                isFacingRight = true;
            }
        }
    
    }
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        frame = (TextureRegion) walk.getKeyFrame(time);     
        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }
    }

    private boolean canMoveTo(float startX, float startY, boolean shouldDestroy) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();
        int x = (int) startX;
        shouldDestroy=false;
        while (x < endX) {

            int y = (int) startY;
            while (y < endY) {
                if (layer.getCell(x, y) != null) {
                    if (shouldDestroy) {
                        layer.setCell(x, y, null);
                    }
                    return false;
                }
                y = y + 1;
            }
            x = x + 1;
        }
        return true;
    }
    
}

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

/**
 *
 * @author Noelia
 */
public class Estrella extends Image{
    public static Animation coinAnim;
    Texture gatitos;

    float time = 0;


    public Estrella() {
        final float width = 16;
        final float height = 16;
        this.setSize(1, height / width);

        gatitos = new Texture("mushroom.png");  
    }
    
    public static Texture loadTexture (String file) {
            return new Texture(Gdx.files.internal(file));
    }
    public void act(float delta) {
        time = time + delta;
    }

    public void draw(Batch batch, float parentAlpha) {
//        TextureRegion frame = (TextureRegion) coinAnim.getKeyFrame(time);
        batch.draw(gatitos , this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
    }
    
    
}

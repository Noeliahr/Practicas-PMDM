package com.cescristorey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Aventurera extends Game {
        public SpriteBatch batch;
	public BitmapFont font;
    
	public void create() {
                 batch = new SpriteBatch();
                //Use LibGDX's default Arial font.
                //font = new BitmapFont();
                font = new BitmapFont(Gdx.files.internal("fuente.fnt"));
            try {
                this.setScreen(new Pantalla_Inicio(this));
            } catch (IOException ex) {
                Logger.getLogger(Aventurera.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}

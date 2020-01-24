/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cescristorey;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import org.xml.sax.SAXException;

/**
 *
 * @author Noelia
 */
public class MainMenuScreen implements Screen {

	final Flappy game;

	OrthographicCamera camera;
        public int puntosMaximos;
        Texture fondo;
        TextureRegion background;
        SpriteBatch batch;

	public MainMenuScreen(final Flappy game) throws IOException, SAXException {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
                
               
                File ficheroScore = new File("Highscore.dat");
                ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(ficheroScore));
                try {
                    while (true) {
                        this.puntosMaximos =  dataIS.readInt();
                    }
                } catch (EOFException eo) {
                } catch (StreamCorruptedException x) {
                }
                dataIS.close();
                batch=new SpriteBatch();
                fondo = new Texture(Gdx.files.internal("fondo.jpg"));
                background =new TextureRegion(fondo,0,0,800,800);

	}
        @Override
        public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.begin();
                batch.draw(background,0,0);
                batch.end();

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Bienvenido al Flappy, tus puntos Maximos son "+ puntosMaximos, 100, 150);
		game.font.draw(game.batch, "Toca para jugar", 100, 100);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game, puntosMaximos));
			dispose();
		}
	}

    @Override
    public void show() {
        
    }

    @Override
    public void resize(int i, int i1) {
       
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        
    }

}

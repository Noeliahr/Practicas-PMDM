/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cescristorey;

import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;


/**
 *
 * @author Noelia
 */
public class GameScreen implements Screen {
	final Flappy game;
        Texture fondo;
        TextureRegion background;
        SpriteBatch batch;
	Texture columna;
        Texture columna2;
	Texture muneca;
	Sound fallo;
        Sound punto;
	Music Music;
	OrthographicCamera camera;
	Rectangle personaje;
	Array<Rectangle> columnas;
        Array<Rectangle> columnas2;
	long lastDropTime;
	int dropsGathered=0;
        int vidas;
        double velocidad;
        double facil;
        float yVelocity = 10; 
        final float MAX_VELOCITY = 10;
        final float GRAVITY =-2.5f;
        int ESPACIO=550;
        int puntosMaximos;
        
       
	public GameScreen(final Flappy game, int puntosMaximos) {
		this.game = game;		
	
		muneca = new Texture(Gdx.files.internal("salto.png"));
                batch=new SpriteBatch();
                fondo = new Texture(Gdx.files.internal("fondo.png"));
                background =new TextureRegion(fondo,0,0,800,800);
                
                columna = new Texture(Gdx.files.internal("columna2.png"));
                columna2 = new Texture(Gdx.files.internal("columna.png"));

		// load the drop sound effect and the rain background "music"
		fallo = Gdx.audio.newSound(Gdx.files.internal("die.wav"));
                punto = Gdx.audio.newSound(Gdx.files.internal("point.wav"));
		Music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		Music.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);

		// create a Rectangle to logically represent the bucket
		personaje = new Rectangle();
		personaje.x = 64; // center the bucket horizontally
		personaje.y = 150; // bottom left corner of the bucket is 20 pixels above
		// the bottom screen edge
		personaje.width = 64;
		personaje.height = 64;

		// create the raindrops array and spawn the first raindrop
		columnas = new Array<Rectangle>();
                columnas2 = new Array<Rectangle>();
		spawnRaindrop();
                
                vidas=5;
                velocidad=500;
                this.puntosMaximos=puntosMaximos;
	}

	private void spawnRaindrop() {
		Rectangle col = new Rectangle();
		col.y = MathUtils.random(-200,0);
		col.x = 800;
		col.width = 64;
		col.height = 320;
		columnas.add(col);
                Rectangle col2 = new Rectangle();
                col2.y = col.y + ESPACIO;
                col2.x = 800;
                col2.width = 64;
                col2.height = 550;
                columnas2.add(col2);
                
               
		lastDropTime = (long) (TimeUtils.nanoTime());
	}

	@Override
	public void render(float delta) {
                // clear the screen with a dark blue color. The
                // arguments to glClearColor are the red, green
                // blue and alpha component in the range [0,1]
                // of the color to be used to clear the screen.
                Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.begin();
                batch.draw(background,0,0);
                batch.end();
                
                // tell the camera to update its matrices.
                camera.update();
                
                // tell the SpriteBatch to render in the
                // coordinate system specified by the camera.
                game.batch.setProjectionMatrix(camera.combined);
                
                // begin a new batch and draw the bucket and
                // all drops
                game.batch.begin();
                game.font.draw(game.batch, "Puntuacion: " + dropsGathered, 0, 800);
                game.font.draw(game.batch, "Puntuancion Maxima: " + puntosMaximos, 200, 800);
                game.batch.draw(muneca, personaje.x, personaje.y, personaje.width, personaje.height);
                
                
                for (Rectangle raindrop : columnas) {
                    game.batch.draw(columna, raindrop.x, raindrop.y);
                   
                }
                for (Rectangle raindrop : columnas2) {
                    game.batch.draw(columna2, raindrop.x, raindrop.y);
                }
                game.batch.end();
                
                // process user input
                
                if (Gdx.input.isKeyPressed(Keys.UP)){
                    personaje.y = personaje.y + MAX_VELOCITY;
                    
                }
                personaje.y = personaje.y + GRAVITY;
                
                // make sure the bucket stays within the screen bounds
                if (personaje.y < 0){
                    fallo.play();
                    Music.pause();
                    try {
                        game.setScreen(new HasPerdido(game, dropsGathered, puntosMaximos));
                    } catch (IOException ex) {
                        Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (personaje.y > 800 - 64)
                    personaje.y = 800 - 64;
                
                // check if we need to create a new raindrop
                if (TimeUtils.nanoTime() - lastDropTime > 1e+9)
                    spawnRaindrop();
                
                // move the raindrops, remove any that are beneath the bottom edge of
                // the screen or that hit the bucket. In the later case we increase the
                // value our drops counter and add a sound effect.
                Iterator<Rectangle> iter = columnas.iterator();
                
                while (iter.hasNext()) {
                    Rectangle raindrop1 = iter.next();
                    raindrop1.x -= velocidad * Gdx.graphics.getDeltaTime();
                    if (raindrop1.x + 64 < 0){
                        iter.remove();
                        dropsGathered=dropsGathered+1;
                        punto.play();
                    }
                    if (raindrop1.overlaps(personaje)) {
                        fallo.play();
                        dispose();
                        try {  
                            game.setScreen(new HasPerdido(game, dropsGathered, puntosMaximos));
                        } catch (IOException ex) {
                            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                Iterator<Rectangle> iter2 = columnas2.iterator();
                while (iter2.hasNext()) {
                    Rectangle raindrop = iter2.next();
                    raindrop.x -= velocidad * Gdx.graphics.getDeltaTime();
                    
                    if (raindrop.overlaps(personaje)) {
                        fallo.play();
                        dispose();
                        try {
                            game.setScreen(new HasPerdido(game, dropsGathered, puntosMaximos));
                        } catch (IOException ex) {
                            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                }
            
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		Music.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		columna.dispose();
                columna2.dispose();
		muneca.dispose();
		fallo.dispose();
                punto.dispose();
		Music.dispose();
	}

}

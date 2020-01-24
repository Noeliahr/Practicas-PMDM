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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Noelia
 */
public class HasPerdido implements Screen {

	final Flappy game;
        int total_de_puntos;
        int puntosMaximos;
	OrthographicCamera camera;
        Texture fondo;
        TextureRegion background;
        SpriteBatch batch;

	public HasPerdido(final Flappy game, int total, int puntosMaximos) throws FileNotFoundException, IOException {
		this.game = game;
                this.total_de_puntos=total;
                if(total_de_puntos>puntosMaximos){
                    this.puntosMaximos=total_de_puntos;
                }else {
                    this.puntosMaximos=puntosMaximos;
                }
                System.out.println(puntosMaximos);
                
                File ficheroScore = new File("Highscore.dat");
                FileOutputStream fileout = new FileOutputStream(ficheroScore);  //crea el flujo de salida
                //conecta el flujo de bytes al flujo de datos
                ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
                dataOS.writeInt(this.puntosMaximos);
                dataOS.close();  //cerrar stream de salida
                
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
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
		game.font.draw(game.batch, "Game over, tus puntos son: " +total_de_puntos, 100, 150);
                game.font.draw(game.batch, "Maxima Puntuacion: " +puntosMaximos, 400, 150);
		game.font.draw(game.batch, "Toca para volver a jugar", 100, 100);
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

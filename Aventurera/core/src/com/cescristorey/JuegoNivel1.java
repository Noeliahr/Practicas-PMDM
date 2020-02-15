/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cescristorey;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

/**
 *
 * @author Noelia
 */
public class JuegoNivel1 implements Screen{
    final Aventurera game;
    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Muneca muneca;
    Gato gato;
    Perro perro;
    ArrayList <Moneda> monedas;
    ArrayList <Estrella> estrellitas;
    Estrella estrella;
    int puntos=0;

    public JuegoNivel1(Aventurera game) {
        this.game = game;
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("mapa.tmx");
        final float pixelsPerTile = 16;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);

        muneca = new Muneca();
        muneca.layer = (TiledMapTileLayer) map.getLayers().get("plataformas");
        muneca.setPosition(2,10);
        stage.addActor(muneca);
        
        gato = new Gato();
        gato.layer = (TiledMapTileLayer) map.getLayers().get("plataformas");
        gato.setPosition(5,10);
        stage.addActor(gato);
        
        perro=new Perro();
        perro.layer=(TiledMapTileLayer) map.getLayers().get("plataformas");
        perro.setPosition(20, 10);
        stage.addActor(perro);
        
        monedas= new ArrayList<>();
        this.loadMoneda(0,0);
        
        estrellitas= new ArrayList<>();
        this.loadEstrellas(0, 0);
//        estrella= new Estrella();
//        estrella.layer=(TiledMapTileLayer) map.getLayers().get("plataformas");
//        estrella.setPosition(3, 13);
//        stage.addActor(estrella);
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        if(muneca.getX()> 13 && muneca.getX()<85){
            camera.position.x=muneca.getX();
        }
        if(muneca.getX()<0){
            muneca.setPosition(0, muneca.getY());
        }
        if (muneca.getX()>95){
            //muneca.setPosition(90, muneca.getY());
            game.setScreen(new JuegoNivel2(game));
            dispose();
            
        }
        if (muneca.getY()<0){
            game.setScreen(new HasPerdido(game));
            dispose();
        }
        camera.update();
        

        renderer.setView(camera);
        renderer.render();

        stage.act(delta);
        stage.draw();

        this.overlapsMonedas();
        this.overlapsGato();
        this.overlapsPerro();
        this.overlapsEstrella();
        
        this.game.batch.begin();        
        this.game.font.draw(game.batch, "Puntuacion: " + puntos, 0, 420);
        this.game.batch.end();
        
        
    }

    @Override
    public void resize(int width, int height) {
         camera.setToOrtho(false, 20 * width / height, 20);
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
    
    public void loadMoneda(float startX, float startY){
        TiledMapTileLayer monedas=(TiledMapTileLayer) map.getLayers().get("monedas");
        float endX=startX + monedas.getWidth();
        float endY=startY + monedas.getHeight();
        
        int x = (int) startX;
        while (x < endX) {

            int y = (int) startY;
            while (y < endY) {
                if (monedas.getCell(x, y) != null) {
                    if (monedas.getProperties().get("visible", Boolean.class)==true) {
                        monedas.setCell(x, y, null);
                        this.spawMoneda(x,y);
                    }
                }
                y = y + 1;
            }
            x = x + 1;
        }      
    }
    
    public void spawMoneda(float x, float y){
        Moneda moneda= new Moneda();
        moneda.setPosition(x, y);
        stage.addActor(moneda);
        monedas.add(moneda);
    }
    
    public void loadEstrellas(float startX, float startY){
        TiledMapTileLayer estrella=(TiledMapTileLayer) map.getLayers().get("estrellas");
        float endX=startX + estrella.getWidth();
        float endY=startY + estrella.getHeight();
        
        int x = (int) startX;
        while (x < endX) {
            int y = (int) startY;
            while (y < endY) {
                if (estrella.getCell(x, y) != null) {
                    if (estrella.getProperties().get("visible", Boolean.class)==true) {
                        estrella.setCell(x, y, null);
                        this.spawEstrella(x,y);
                    }
                }
                y = y + 1;
            }
            x = x + 1;
        }      
    }
    
    public void spawEstrella(float x, float y){
        Estrella estrella= new Estrella();
        estrella.setPosition(x, y);
        stage.addActor(estrella);
        this.estrellitas.add(estrella);
    }
    
    //overlaps
    public void overlapsMonedas(){
        for (Moneda moneda:monedas){
            if (moneda.getX()+1.5f > muneca.getX() && moneda.getY()+1.5f > muneca.getY() && moneda.getX()-1.5f < muneca.getX() && moneda.getY()-1.5f < muneca.getY()){
               puntos+=10;
               moneda.setY(500);
               moneda.remove();
            }    
        }
    }
    
    public void overlapsEstrella(){
        for (Estrella estrella:estrellitas){
            if (estrella.getX()+1.5f > muneca.getX() && estrella.getY()+1.5f > muneca.getY() && estrella.getX()-1.5f < muneca.getX() && estrella.getY()-1.5f < muneca.getY()){
               puntos+=1;
               estrella.setY(500);
               estrella.remove();
            }    
        }
    }
    
    public void overlapsGato(){
        if (gato.getX()+1f > muneca.getX() && gato.getY()+1f > muneca.getY() && gato.getX()-1f < muneca.getX() && gato.getY()-1f < muneca.getY()){
            
            if(muneca.getX()<gato.getX()){
                muneca.setX(muneca.getX()-5);
                gato.setX(muneca.getY()+5);
            }else {
                muneca.setX(muneca.getX()+5);
                gato.setX(muneca.getY()-5);
            }
            if (puntos> 0){
                puntos-=5;
            }else {
               game.setScreen(new HasPerdido(game));
               dispose(); 
            }
            
        }   
    }
    public void overlapsPerro(){
        if (perro.getX()+1f > muneca.getX() && perro.getY()+1f > muneca.getY() && perro.getX()-1f < muneca.getX() && perro.getY()-1f < muneca.getY()){ 
            muneca.setPosition(muneca.getX(), 0);
            game.setScreen(new HasPerdido(game));
            dispose();            
        }   
    }
    
    
    //muerte
    
}

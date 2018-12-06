package com.marth.projectcyber.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.marth.projectcyber.projectCyber;

/**
 * Created by arthur on 02/10/16.
 */

public class MainMenuScreen implements Screen, InputProcessor{

    final projectCyber game;
    private Stage stage;
    private Stage SplashScreen;
    private Stage IFSP;

    private Sprite fundoBranco;

    private Skin skin;
    private Table table;
    private Table table2;
    private Table table3;
    private Sprite sprite;
    private Texture texture;
    private Texture texture3;
    private Texture texture4;

    private TextButton btnJogar;
    private TextButton btnSair;
    private int contador = 0;
    private Music bgm;

    OrthographicCamera camera;


    public MainMenuScreen(final projectCyber gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        skin = new Skin(Gdx.files.internal("Scene2D/clean-crispy-ui.json"));
        fundoBranco = new Sprite(new Texture(Gdx.files.internal("sprites/fundoCinza.png")));
        table = new Table();
        table2 = new Table();
        table3 = new Table();
        stage = new Stage(new ScreenViewport(),game.batch);
        SplashScreen = new Stage(new ScreenViewport(),game.batch);
        IFSP = new Stage(new ScreenViewport(),game.batch);
        btnJogar = new TextButton("Jogar",skin);
        btnSair = new TextButton("Sair",skin);

        sprite = new Sprite(new Texture(Gdx.files.internal("Matrix.png")));
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        texture = new Texture(Gdx.files.internal("Logo.png"));
        TextureRegion region = new TextureRegion(texture);

        Image actor = new Image(region);
        stage.addActor(actor);


        fundoBranco.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //MENU
        table.setWidth(Gdx.graphics.getWidth());
        table.align(Align.center|Align.top);

        table.setPosition(0,Gdx.graphics.getHeight());

        table.row();
        table.add(actor).align(Align.center).size(150).padTop((Gdx.graphics.getHeight()/2) /2 - 50);
        table.row();
        table.add(btnJogar).padBottom(20).width(btnJogar.getWidth() * 2).padTop(50);
        table.row();
        table.add(btnSair).width(btnSair.getWidth() * 2);

        stage.addActor(table);

        //SPLASHSCREEN
        texture = new Texture(Gdx.files.internal("Logo.png"));
        TextureRegion region2 = new TextureRegion(texture);
        Image actor2 = new Image(region2);
        SplashScreen.addActor(actor2);

        table2.setWidth(Gdx.graphics.getWidth());
        table2.align(Align.center|Align.top);

        table2.setPosition(0,Gdx.graphics.getHeight());
        table2.padTop(250);
        table2.add(actor2).align(Align.center).size(150);

        SplashScreen.addActor(table2);

        //IFSP E LIB GDX ICONES
        texture3 = new Texture(Gdx.files.internal("LibGdx.png"));
        TextureRegion region3 = new TextureRegion(texture3);
        Image actor3 = new Image(region3);

        IFSP.addActor(actor3);

        texture4 = new Texture(Gdx.files.internal("IFSP.png"));
        TextureRegion region4 = new TextureRegion(texture4);
        Image actor4 = new Image(region4);
        IFSP.addActor(actor4);

        table3.setWidth(Gdx.graphics.getWidth());
        table3.align(Align.center|Align.top);

        table3.setPosition(0,Gdx.graphics.getHeight());
        table3.row();
        table3.padTop(250);
        table3.add(actor3).align(Align.center).width(250).height(75);

        table3.add(actor4).align(Align.center).width(250).height(75).padLeft(25);
        IFSP.addActor(table3);

        btnJogar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });

        btnSair.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });

        InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                contador++;
            }
        },3);

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                IFSP.draw();
                contador++;
            }
        },5);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("music/trilhahotlineFUZZ.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(0.5f);
        bgm.play();


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        fundoBranco.draw(game.batch);
        game.batch.end();

        if(contador == 0)
          SplashScreen.draw();

        if(contador==1)
            IFSP.draw();

        if(contador==2)
            stage.draw();
    }

    @Override
    public void dispose() {
        bgm.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide(){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

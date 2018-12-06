package com.marth.projectcyber.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.marth.projectcyber.Sprites.Enemy;
import com.marth.projectcyber.Sprites.Player;
import com.marth.projectcyber.Tools.B2WorldCreator;
import com.marth.projectcyber.Tools.WorldContactListerner;
import com.marth.projectcyber.TouchPad.TouchPad;
import com.marth.projectcyber.World1.Rooms;
import com.marth.projectcyber.World1.Tiles;
import com.marth.projectcyber.projectCyber;

import java.util.ArrayList;


/**
 * Created by Matheus on 02/10/2016.
 */
public class PlayScreen implements Screen{

    private projectCyber game;
    private TouchPad touchPad;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private Rooms RoomsW1;
    private Tiles tiles;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;
    private Enemy enemy;

    private Music bgm;

    private Rooms[][] rooms;
    private Vector2 pos;
    public Vector2 posicao;
    private int [][] mapa = new int[][]{
            {2,6,6,0,0,6,6,5},
            {8,0,0,0,0,0,0,9},
            {8,0,0,0,0,0,0,9},
            {8,0,0,0,0,0,0,9},
            {8,0,0,0,0,0,0,9},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {8,0,0,0,0,0,1,9},
            {8,0,0,0,0,0,1,9},
            {8,0,0,0,0,0,1,9},
            {8,0,0,0,0,0,0,9},
            {3,10,10,0,0,10,10,4}
    };




    public PlayScreen(projectCyber game){
        this.game = game;

        bgm = Gdx.audio.newMusic(Gdx.files.internal("music/GWOSHH.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(0.3f);
        bgm.play();

        rooms = new Rooms[16][16];

        //target = new Vector2((float)3.84,(float)2.56);

        camera = new OrthographicCamera();
        gamePort = new FitViewport(projectCyber.V_WIDTH / projectCyber.PPM ,projectCyber.V_HEIGHT/projectCyber.PPM,camera);

        pos = new Vector2(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2);
        posicao = new Vector2(0,0);

        tiles = new Tiles(game,camera);
        RoomsW1 = new Rooms();
        renderer = new OrthogonalTiledMapRenderer(RoomsW1.initMap(), 1 / projectCyber.PPM);
        camera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world);
        enemy = new Enemy(world, .32f, .32f);

        world.setContactListener(new WorldContactListerner());
        new B2WorldCreator(world, RoomsW1.initMap());

    }

    @Override
    public void show() {

    }



    public void checkTransitions() {

        //DIREITA
        if(player.getX() > pos.x + 1.91) {
            posicao.x+= 3.84;
            mapa = tiles.gerarFase();
            tiles.desenhar(posicao,mapa);


            moveCamera("direita");
        }
        //ESQUERDA
        if(player.getX() < pos.x - 1.92) {
            posicao.x-= 3.84;
            mapa = tiles.gerarFase();
            tiles.desenhar(posicao,mapa);

            moveCamera("esquerda");
        }
        //CIMA
        if(player.getY() > pos.y + 1.28){
            posicao.y+= 2.56;
            mapa = tiles.gerarFase();
            tiles.desenhar(posicao,mapa);

            moveCamera("cima");
        }
        //BAIXO
        if(player.getY() < pos.y - 1.28) {
            posicao.y-= 2.56;
            mapa = tiles.gerarFase();
            tiles.desenhar(posicao,mapa);

            moveCamera("baixo");
        }
    }

    public void moveCamera(String lado){

            if(lado.equals("direita")) {
                pos.x += 3.84;
                camera.position.set(pos.x, pos.y, 0);
                renderer.setView(camera);
                renderer.render();
            }
            if(lado.equals("esquerda")) {
                pos.x -= 3.84;
                camera.position.set(pos.x, pos.y, 0);
                renderer.setView(camera);
                renderer.render();

            }
            if(lado.equals("cima")) {
                pos.y += 2.56;
                camera.position.set(pos.x, pos.y, 0);
                renderer.setView(camera);
                renderer.render();
            }

            if(lado.equals("baixo")) {
                pos.y -= 2.56;
                camera.position.set(pos.x, pos.y, 0);
                renderer.setView(camera);
                renderer.render();
            }
    }

    public void handleInput(float dt){
        player.b2body.setLinearDamping(15);
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            player.b2body.applyForce(new Vector2(0,Player.PLAYER_SPEED), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            player.b2body.applyForce(new Vector2(-Player.PLAYER_SPEED,0), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            player.b2body.applyForce(new Vector2(0,-Player.PLAYER_SPEED), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {

            player.b2body.applyForce(new Vector2(Player.PLAYER_SPEED,0), player.b2body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
           System.err.print("PosicaoX =="+posicao.x +"\n"
                            +"PosicaoY =="+posicao.y+"\n");

            /*"X = " + player.getX() + "\n" + "Y = "+ player.getY() + "\n"
                            + "Target X = " + target.x + "\n"
                            + "Target Y = " + target.y + "\n"
                            + "POS X = " + pos.x + " POS = " + pos.y + " \n"*/

        }
    }


    public void update(float dt){
        handleInput(dt);


        world.step(1/60f, 6, 2);

        checkTransitions();
  
        //moveEnemy();


        player.update(dt);
        enemy.update(dt);




        camera.update();
       // renderer.setView(camera);
    }

    public void moveEnemy()
    {
        float targetX = player.b2body.getPosition().x; //Player's X
        float targetY = player.b2body.getPosition().y; //Player's Y
        float spriteX = enemy.b2body.getPosition().x;  //Enemy's X
        float spriteY = enemy.b2body.getPosition().y;  //Enemy's Y
        float x2 = 0;
        float y2 = 0;
        float angle; // We use a triangle to calculate the new trajectory

        angle = (float) Math
                .atan2(targetY - spriteY, targetX - spriteX);

        x2 += (float) Math.cos(angle) * 180
                * 0.0050f;

        y2 += (float) Math.sin(angle) * 180
                * 0.0050f;

        enemy.b2body.setLinearDamping(1);
        enemy.b2body.applyForce(new Vector2( x2*2, y2*2), enemy.b2body.getWorldCenter(), true);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


       //renderer.render();

        b2dr.render(world, camera.combined);

       /* camera.position.set(player.getX(), player.getY(), 0);
        camera.update();*/

/*        if(Gdx.app.getType() == Application.ApplicationType.Android){
            player.setX(player.getX() + touchPad.()*blockSpeed);
            player.setY(player.getY() + touchPad.getKnobPercentY()*blockSpeed);
        }*/

        tiles.desenhar(posicao,mapa);
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        player.draw(game.batch);
        enemy.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        RoomsW1.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
    }
}

package com.marth.projectcyber.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.marth.projectcyber.Sprites.Player;
import com.marth.projectcyber.Tools.B2WorldCreator;
import com.marth.projectcyber.projectCyber;

/**
 * Created by Matheus on 02/10/2016.
 */
public class PlayScreen implements Screen{

    private projectCyber game;
    Texture texture;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;

    private Music bgm;

    public PlayScreen(projectCyber game){
        this.game = game;
        bgm = Gdx.audio.newMusic(Gdx.files.internal("music/GWOSHH.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(0.5f);
        bgm.play();
        texture = new Texture("badlogic.jpg");
        camera = new OrthographicCamera();
        gamePort = new FitViewport(projectCyber.V_WIDTH / projectCyber.PPM ,projectCyber.V_HEIGHT/projectCyber.PPM,camera);
        maploader = new TmxMapLoader();
        map = maploader.load("tmx/lvlsmall.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / projectCyber.PPM);
        camera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world);

        new B2WorldCreator(world, map);


    }

    @Override
    public void show() {

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
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6, 2);

        player.update(dt);

        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //b2dr.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
    }
}

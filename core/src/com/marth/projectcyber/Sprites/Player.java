package com.marth.projectcyber.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.marth.projectcyber.projectCyber;

/**
 * Created by Matheus on 02/10/2016.
 */
public class Player extends Sprite{
    public static final float PLAYER_SPEED = 20f;

    public World world;
    public Body b2body;

    public enum State {STANDING, RUNNING, SHOOTING, RUN_SHOOTING}
    public State currentState;
    public State previousState;

    private Texture txeonStanding = new Texture("sprites/xeonStandingNew.png");
    private Texture txeonRunning = new Texture("sprites/xeonRunningNew.png");
    private Texture txeonStandingShooting = new Texture("sprites/xeonStandingShot.png");
    private Texture txeonRunningShooting = new Texture("sprites/xeonRunningShot.png");
    private TextureRegion xeonStanding = new TextureRegion(txeonStanding);
    private TextureRegion xeonStandingShooting = new TextureRegion(txeonStandingShooting);
    private Animation xeonRun;
    private Animation xeonRunShoot;
    private Animation xeonRunShootBackwards;

    private boolean runningRight;
    private float statetimer;


    public Player(World world){
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        runningRight = true;
        statetimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 10; i++){
            frames.add(new TextureRegion(txeonRunning,i * 45,0,45,55));
        }
        xeonRun = new Animation(0.08f, frames);
        frames.clear();

        for(int i = 0; i < 10; i++){
            frames.add(new TextureRegion(txeonRunningShooting,i * 45,0,45,55));
        }
        xeonRunShoot = new Animation(0.08f, frames);
        frames.clear();

        for(int i = 10; i > 0; i--){
            frames.add(new TextureRegion(txeonRunningShooting,(i * 45)-45,0,45,55));
        }
        xeonRunShootBackwards = new Animation(0.08f, frames);
        frames.clear();

        definePlayer();

        setBounds(0,0,35/projectCyber.PPM,50 / projectCyber.PPM);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2 , b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame (float dt){
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case RUNNING:
                region = xeonRun.getKeyFrame(statetimer,true);
                break;
            case SHOOTING:
                    region = xeonStandingShooting;
                break;
            case RUN_SHOOTING:
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x > 0)
                    region = xeonRunShootBackwards.getKeyFrame(statetimer,true);
                else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x < 0)
                    region = xeonRunShootBackwards.getKeyFrame(statetimer,true);
                else
                    region = xeonRunShoot.getKeyFrame(statetimer,true);
                break;
            default:
                region = xeonStanding;
                break;

        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !region.isFlipX() )
            region.flip(true,false);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && region.isFlipX() )
            region.flip(true,false);

        statetimer = currentState == previousState ? statetimer + dt : 0;
        previousState = currentState;
        return region;
    }


    public State getState(){
//        if(b2body.getLinearVelocity().y != 0 || b2body.getLinearVelocity().x != 0)
//            return State.RUNNING;
//        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.LEFT) )
//            return State.SHOOTING;
//        else
//            return State.STANDING;
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                return State.RUN_SHOOTING;
            }
            return State.RUNNING;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            return State.SHOOTING;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            return State.SHOOTING;
        else
            return State.STANDING;

    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(50/ projectCyber.PPM,50/projectCyber.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10 / projectCyber.PPM, 20 / projectCyber.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("corpoPlayer");
    }

    public void onPlayerHit(){

    }
}

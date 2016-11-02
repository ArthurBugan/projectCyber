package com.marth.projectcyber.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.marth.projectcyber.projectCyber;

/**
 * Created by Matheus on 02/10/2016.
 */
public class Player extends Sprite{
    public static final float PLAYER_SPEED = 25f;

    public World world;
    public Body b2body;

    public enum State {STANDING, RUNNING, SHOOTING};
    public State currentState;
    public State previousState;

    private Texture txeonStanding = new Texture("sprites/xeonstandinga.png");
    private Texture txeonRunning = new Texture("sprites/xeonwalking.png");
    private Animation xeonRun;
    private Animation xeonStand;

    private boolean runningRight;
    private float statetimer;




    public Player(World world){
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        runningRight = true;
        statetimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 3; i++){
            frames.add(new TextureRegion(txeonStanding,i * 45,0,45,65));
        }
        xeonStand = new Animation(0.15f,frames);
        frames.clear();
        for(int i = 0; i < 4; i++){
            frames.add(new TextureRegion(txeonRunning,i * 45,0,45,65));
        }
        xeonRun = new Animation(0.15f, frames);
        frames.clear();

        definePlayer();


        setBounds(0,0,30/projectCyber.PPM,50 / projectCyber.PPM);

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
            case STANDING:
            default:
                region = xeonStand.getKeyFrame(statetimer,true);
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
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D))
            return State.RUNNING;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.LEFT) )
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
        b2body.createFixture(fdef);
    }
}

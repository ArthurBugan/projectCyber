package com.marth.projectcyber.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.marth.projectcyber.Screens.PlayScreen;
import com.marth.projectcyber.projectCyber;

/**
 * Created by Arthur on 12/8/2016.
 */

public  class Enemy extends Sprite{
   public Body b2body;
   public World world;

    private float statetime;
    private Texture enemywalking = new Texture("sprites/enemy.png");
    private Animation enmwalk;
    protected Fixture fixture;

    public Enemy(World world, float x, float y){
        this.world = world;

       setPosition(x,y);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++){
            frames.add(new TextureRegion(enemywalking,i * 16,0,16,16));
        }
        statetime = 0;
        enmwalk = new Animation(0.4f, frames);
        frames.clear();

        defineEnemy();

        setBounds(0,0,16/ projectCyber.PPM,16 / projectCyber.PPM);

    }


    private void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(16/ projectCyber.PPM,16/projectCyber.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10 / projectCyber.PPM, 20 / projectCyber.PPM);

        fdef.shape = shape;

        fixture = b2body.createFixture(fdef);
    }


    public void update(float dt){
        statetime += dt;
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight() /2);
        setRegion(enmwalk.getKeyFrame(statetime,true));
    }
}

package com.marth.projectcyber.Tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.marth.projectcyber.Sprites.Player;

/**
 * Created by Arthur on 12/8/2016.
 */


public class WorldContactListerner implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contato");
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("FimContato");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

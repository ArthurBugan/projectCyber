package com.marth.projectcyber.World1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Matheus on 02/11/2016.
 */
public class Rooms extends Map {
    private TiledMap map;

    public TiledMap generateRoom() {
        map = initMap();
        //Gerar room nova
        TiledMapTileSet tileset = map.getTileSets().getTileSet("ground");
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        TiledMapTileLayer.Cell cellesquerda = new TiledMapTileLayer.Cell();
        cellesquerda.setTile(tileset.getTile(12));

        SpriteBatch batch = new SpriteBatch();
        Texture texture = new Texture("sprites/xeonstanding.png");

        batch.begin();
        batch.draw(texture,(float)-1.92,(float)1.28);
        batch.dispose();



        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;



        return map;
    }

    public void dispose(){
        map.dispose();
    }
}

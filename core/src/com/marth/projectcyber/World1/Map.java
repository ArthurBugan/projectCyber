package com.marth.projectcyber.World1;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by Matheus on 02/11/2016.
 */
public class Map {
    public static final int MAX_ROOMS = 12;
    public static final int MIN_ROOMS = 8;

    public static final String MAP_ROOT = "tmx/w1centro.tmx";

    public TiledMap initMap() {
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load(MAP_ROOT);
        return map;
    }
}

package com.marth.projectcyber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.marth.projectcyber.Screens.MainMenuScreen;
import com.marth.projectcyber.Screens.PlayScreen;
import com.marth.projectcyber.TouchPad.TouchPad;


public class projectCyber extends Game {
	public static  final int V_WIDTH = 384;
	public static  final int V_HEIGHT = 256;
	public static final float PPM = 100;
	private TouchPad touchPad;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();



	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

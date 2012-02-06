package com.simpleglengine.entity.scene.background;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.Entity;
import com.simpleglengine.entity.sprite.SpriteBatch;

public class TextureBackground extends ColorBackground implements Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private boolean mRepeatX, mRepeatY;
	private float mXMin, mXMax, mYMin, mYMax;
	private SpriteBatch mSpriteBatch;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TextureBackground(Texture texture, float mXMin, float mXMax, float mYMin, float mYMax, boolean mRepeatX, boolean mRepeatY) {
		super(0.0f, 0.0f, 0.0f, 1.0f);

		int width = (int) ((mXMax-mXMin)/texture.getWidth());//(int) (mXMax/mXMin);
		int height = (int) ((mYMax-mYMin)/texture.getHeight());
		float [] x = new float[width*height], y = new float[width*height];

		int k = 0;
		for(int i = 0; i < width*height;i++) {
			x[k] = mXMin + texture.getWidth()*(i%width);
			y[k] = mYMin + texture.getWidth()*(i/width);
			k++;
		}

		this.mSpriteBatch = new SpriteBatch(texture, x, y);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public void setScale(float scale) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onDraw(GL10 gl) {
		super.onDraw(gl);

		this.mSpriteBatch.onDraw(gl);
	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);

		this.mSpriteBatch.onUpdate(alpha);
	}
	
	public void onLoadSurface(GL10 gl) {
		super.onLoadSurface(gl);
		
		this.mSpriteBatch.onLoadSurface(gl);
	}

	// ===========================================================
	// Methods
	// ===========================================================
}

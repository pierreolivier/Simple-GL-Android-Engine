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
		float [] x  = {mXMin, mXMax}, y = {mYMin, mYMax};
		
		Log.e("s", ""+width+" "+height);
		
		for(int i = 0; i < width;i++) {
			for(int j = 0; j < height;j++) {
				x[i] = mXMin + texture.getWidth()*(i%width);
				y[i] = mYMin + texture.getWidth()*(i/width);
			}
		}
		/*
		
		if(width > height) {
			x = new float[width];
			y = new float[width];			
			for(int i = 0; i < width;i++) {
				x[i] = mXMin + texture.getWidth()*(i%width);
				y[i] = mYMin + texture.getWidth()*(i/width);
			}
		} else {
			x = new float[height];
			y = new float[height];			
			for(int i = 0; i < height;i++) {
				x[i] = mXMin + texture.getHeight()*(i%height);
				y[i] = mYMin + texture.getHeight()*(i/height);
			}
		}
		*/
		
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

	// ===========================================================
	// Methods
	// ===========================================================
}

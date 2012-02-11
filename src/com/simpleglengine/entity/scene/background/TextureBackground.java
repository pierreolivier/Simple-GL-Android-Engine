package com.simpleglengine.entity.scene.background;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.sprite.SpriteBatch;

public class TextureBackground extends ColorBackground implements IEntity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	//private boolean mRepeatX, mRepeatY;
	//private float mXMin, mXMax, mYMin, mYMax;
	private SpriteBatch mSpriteBatch = null;
	
	private Sprite mSprite = null;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TextureBackground(Texture texture, int x, int y) {
		super(0.0f, 0.0f, 0.0f, 1.0f);
		
		this.mSprite = new Sprite(texture, x, y);
	}
	public TextureBackground(Texture texture, float mXMin, float mXMax, float mYMin, float mYMax) {
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

		gl.glDisable(GL10.GL_BLEND);
		
		if(mSpriteBatch != null)
			this.mSpriteBatch.onDraw(gl);
		else if (mSprite != null)
			this.mSprite.onDraw(gl);
			
		
		gl.glEnable(GL10.GL_BLEND);
	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);

		if(mSpriteBatch != null)
			this.mSpriteBatch.onUpdate(alpha);
		else if (mSprite != null)
			this.mSprite.onUpdate(alpha);
		
	}
	
	public void onLoadSurface(GL10 gl) {
		super.onLoadSurface(gl);
		
		if(mSpriteBatch != null)
			this.mSpriteBatch.onLoadSurface(gl);
		else if (mSprite != null)
			this.mSprite.onLoadSurface(gl);
	}

	// ===========================================================
	// Methods
	// ===========================================================
}

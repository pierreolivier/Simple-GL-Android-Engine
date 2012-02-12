package com.simpleglengine.entity.sprite;

import com.simpleglengine.engine.opengl.Texture;

public class AnimatedSprite extends Sprite {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected Texture [] mTextures;
	protected int mCurrentTexture;
	protected double mDuration, mLastChange;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public AnimatedSprite(Texture [] textures, int x, int y, double duration) {
		super(textures[0], x, y);
		
		this.mTextures = textures;
		this.mCurrentTexture = 0;
		this.mDuration = duration;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);
		
		if(System.currentTimeMillis() - mLastChange >= mDuration) {			
			mTexture = mTextures[mCurrentTexture];
			
			mLastChange = System.currentTimeMillis();
			
			mCurrentTexture++;
			if(mCurrentTexture >= mTextures.length) mCurrentTexture = 0;
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================
	
	
	
}

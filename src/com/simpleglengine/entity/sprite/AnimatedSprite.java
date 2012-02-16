package com.simpleglengine.entity.sprite;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;

public class AnimatedSprite extends Sprite {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected TextureRegion [] mTexturesRegions;
	protected int mCurrentTexture;
	protected double mDuration, mLastChange;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AnimatedSprite(TextureRegion [] texturesRegions, int x, int y, double duration) {
		super(texturesRegions[0], x, y);

		this.mTexturesRegions = texturesRegions;
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

		if(!mPause) {
			if(System.currentTimeMillis() - mLastChange >= mDuration) {			
				mTextureRegion = mTexturesRegions[mCurrentTexture];

				mLastChange = System.currentTimeMillis();

				mCurrentTexture++;
				if(mCurrentTexture >= mTexturesRegions.length) mCurrentTexture = 0;
			}
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================



}

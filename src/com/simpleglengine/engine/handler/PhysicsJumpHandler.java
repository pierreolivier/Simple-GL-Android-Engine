package com.simpleglengine.engine.handler;

import android.util.Log;

import com.simpleglengine.entity.Shape;
import com.simpleglengine.tools.ScreenTools;

public class PhysicsJumpHandler extends PhysicsHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mJumpVelocityX, mJumpVelocityY, mJumpAccelerationX, mJumpAccelerationY;
	protected float mYMax;

	protected int mMaxJumpsInARow, mJumpsInARow;
	protected boolean mFalling;
	
	protected IPhysicsJumpListener mIPhysicsJumpHandler = null;

	// ===========================================================
	// Constructors
	// ===========================================================
	public PhysicsJumpHandler(Shape shape) {
		super(shape);

		this.mEnabled = false;

		this.mYMax = ScreenTools.getHeight()-shape.getHeight();
		this.mMaxJumpsInARow = -1;
		this.mJumpsInARow = 0;
	}
	
	public PhysicsJumpHandler(Shape shape, IPhysicsJumpListener iPhysicsJumpHandler) {
		this(shape);
		
		this.mIPhysicsJumpHandler = iPhysicsJumpHandler;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public boolean isFalling() {
		return mFalling;
	}
	public boolean isJumping() {
		return mEnabled;
	}
	public float getYMax() {
		return mYMax;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void onUpdate(float pSecondsElapsed) {
		super.onUpdate(pSecondsElapsed);
		
		/*
		if(mEntity.getY() >= mYMax && !mFalling) {
			stop();
			mEntity.setY(mYMax);
		}*/
		if(mEntity.getY() >= mYMax && mEnabled) {
			stop();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	//Set angular rotation

	public void settingJump(float velocityX, float velocityY, float accelerationX, float accelerationY) {
		this.mJumpVelocityX = velocityX;
		this.mJumpVelocityY = velocityY;
		this.mJumpAccelerationX = accelerationX;
		this.mJumpAccelerationY = accelerationY;
	}
	public void settingWorld(float yMax, int maxJumpsInARow) {
		this.mYMax = yMax;
		this.mMaxJumpsInARow = maxJumpsInARow;
	}

	public void startJump() {
		if(mMaxJumpsInARow != -1 && mJumpsInARow < mMaxJumpsInARow) {
			this.mVelocityX = mJumpVelocityX;
			this.mVelocityY = mJumpVelocityY;
			this.mAccelerationX = mJumpAccelerationX;
			this.mAccelerationY = mJumpAccelerationY;

			this.mEnabled = true;

			this.mJumpsInARow++;
			
			if(mIPhysicsJumpHandler != null)
				mIPhysicsJumpHandler.onJumpStarted();
		} else if (mMaxJumpsInARow == -1){
			this.mVelocityX = mJumpVelocityX;
			this.mVelocityY = mJumpVelocityY;
			this.mAccelerationX = mJumpAccelerationX;
			this.mAccelerationY = mJumpAccelerationY;

			this.mEnabled = true;
			
			if(mIPhysicsJumpHandler != null)
				mIPhysicsJumpHandler.onJumpStarted();
		}
	}
	public void startFall() {
		if(mJumpsInARow < mMaxJumpsInARow) {
			this.mVelocityX = 0;
			this.mVelocityY = 0;
			this.mAccelerationX = 0;
			this.mAccelerationY = mJumpAccelerationY;

			this.mJumpsInARow = mMaxJumpsInARow;

			this.mFalling = true;

			this.mEnabled = true;
			
			if(mIPhysicsJumpHandler != null)
				mIPhysicsJumpHandler.onFallingStarted();
		}
	}

	//Call this to rejump !!!!
	public void stop() {
		this.mEnabled = false;

		this.mFalling = false;
		this.mJumpsInARow = 0;

		mEntity.setY(mYMax);
		
		if(mIPhysicsJumpHandler != null)
			mIPhysicsJumpHandler.onJumpFinished();
	}

	public void resetXVelocity() {
		this.mVelocityX = 0;
		this.mAccelerationX = 0;
	}
	public void resetYVelocity() {
		this.mVelocityY = 0;
	}



}

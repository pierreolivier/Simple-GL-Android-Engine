package com.simpleglengine.engine.handler;

import android.util.Log;

import com.simpleglengine.entity.Shape;

public class PhysicsHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected boolean mEnabled = true;

	protected float mAccelerationX = 0;
	protected float mAccelerationY = 0;

	protected float mVelocityX = 0;
	protected float mVelocityY = 0;

	protected float mAngularVelocity = 0;

	protected Shape mEntity;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PhysicsHandler(Shape shape) {
		super();

		this.mEntity = shape;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isEnabled() {
		return this.mEnabled;
	}

	public void setEnabled(final boolean pEnabled) {
		this.mEnabled = pEnabled;
	}

	public float getVelocityX() {
		return this.mVelocityX;
	}

	public float getVelocityY() {
		return this.mVelocityY;
	}

	public void setVelocityX(final float pVelocityX) {
		this.mVelocityX = pVelocityX;
	}

	public void setVelocityY(final float pVelocityY) {
		this.mVelocityY = pVelocityY;
	}

	public void setVelocity(final float pVelocity) {
		this.mVelocityX = pVelocity;
		this.mVelocityY = pVelocity;
	}

	public void setVelocity(final float pVelocityX, final float pVelocityY) {
		this.mVelocityX = pVelocityX;
		this.mVelocityY = pVelocityY;
	}

	public float getAccelerationX() {
		return this.mAccelerationX;
	}

	public float getAccelerationY() {
		return this.mAccelerationY;
	}

	public void setAccelerationX(final float pAccelerationX) {
		this.mAccelerationX = pAccelerationX;
	}

	public void setAccelerationY(final float pAccelerationY) {
		this.mAccelerationY = pAccelerationY;
	}

	public void setAcceleration(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX = pAccelerationX;
		this.mAccelerationY = pAccelerationY;
	}

	public void setAcceleration(final float pAcceleration) {
		this.mAccelerationX = pAcceleration;
		this.mAccelerationY = pAcceleration;
	}

	public void accelerate(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX += pAccelerationX;
		this.mAccelerationY += pAccelerationY;
	}

	public float getAngularVelocity() {
		return this.mAngularVelocity;
	}

	public void setAngularVelocity(final float pAngularVelocity) {
		this.mAngularVelocity = pAngularVelocity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void onUpdate(float pSecondsElapsed) {
		if(this.mEnabled) {
			/* Apply linear acceleration. */
			final float accelerationX = this.mAccelerationX;
			final float accelerationY = this.mAccelerationY;
			if(accelerationX != 0 || accelerationY != 0) {
				this.mVelocityX += accelerationX * pSecondsElapsed;
				this.mVelocityY += accelerationY * pSecondsElapsed;
			}

			/* Apply angular velocity. */
			final float angularVelocity = this.mAngularVelocity;
			if(angularVelocity != 0) {
				mEntity.setRotation((int) (mEntity.getRotation() + angularVelocity * pSecondsElapsed));
			}

			/* Apply linear velocity. */
			final float velocityX = this.mVelocityX;
			final float velocityY = this.mVelocityY;
			if(velocityX != 0 || velocityY != 0) {
				mEntity.setPosition(mEntity.getX() + velocityX * pSecondsElapsed, mEntity.getY() + velocityY * pSecondsElapsed);
			}
		}
	}

	public void reset() {
		this.mAccelerationX = 0;
		this.mAccelerationY = 0;
		this.mVelocityX = 0;
		this.mVelocityY = 0;
		this.mAngularVelocity = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
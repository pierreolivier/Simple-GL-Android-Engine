package com.simpleglengine.engine.handler.modifier;

import android.util.Log;

import com.simpleglengine.engine.handler.modifier.ease.IEaseFunction;
import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;

public class DoubleValueEntityModifier implements IEntityModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mSecondsElapsed, mDuration;
	protected float mInitialX, mInitialY, mFinalX, mFinalY;
	protected IEaseFunction mEaseFunction;

	protected float mVelocityX, mVelocityY;

	protected boolean mFinished;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DoubleValueEntityModifier(float duration, float initialX, float initialY, float finalX, float finalY, IEaseFunction easeFunction) {
		super();

		this.mSecondsElapsed = 0;
		this.mDuration = duration;

		this.mInitialX = initialX;
		this.mInitialY = initialY;
		this.mFinalX = finalX;
		this.mFinalY = finalY;

		this.mEaseFunction = easeFunction;

		this.mVelocityX = (finalX - initialX) / duration;
		this.mVelocityY = (finalY - initialY) / duration;

		this.mFinished = false;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public boolean isFinished() {
		return mFinished;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onModifierStarted(Shape shape) {
		shape.setPosition(mInitialX, mInitialY);
	}
	@Override
	public void onModifierFinished(Shape shape) {
		shape.setPosition(mFinalX, mFinalY);

		this.mFinished = true;
	}
	@Override
	public void onUpdate(Shape shape, float alpha) {
		float percentage;

		if(mSecondsElapsed == 0)
			onModifierStarted(shape);

		this.mSecondsElapsed += alpha;

		percentage = mEaseFunction.getPercentage(mSecondsElapsed, mDuration);

		shape.setX(mInitialX + mVelocityX*percentage*mDuration);
		shape.setY(mInitialY + mVelocityY*percentage*mDuration);

		if(mSecondsElapsed >= mDuration)
			onModifierFinished(shape);
	}



	// ===========================================================
	// Methods
	// ===========================================================

}

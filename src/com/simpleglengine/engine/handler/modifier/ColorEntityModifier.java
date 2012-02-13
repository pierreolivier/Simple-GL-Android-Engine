package com.simpleglengine.engine.handler.modifier;

import android.util.Log;

import com.simpleglengine.engine.handler.modifier.ease.IEaseFunction;
import com.simpleglengine.entity.Shape;

public class ColorEntityModifier  implements IEntityModifier{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mDuration, mSecondsElapsed;
	protected float mInitialR, mInitialG, mInitialB, mInitialA;
	protected float mFinalR, mFinalG, mFinalB, mFinalA;
	protected IEaseFunction mEaseFunction;

	protected float mVelocityR, mVelocityG, mVelocityB, mVelocityA;

	protected boolean mFinished;

	protected IEntityModifierListener mEntityModifierListener;
	// ===========================================================
	// Constructors
	// ===========================================================
	public ColorEntityModifier(float duration, float r, float g, float b, float a, float finalR, float finalG, float finalB, float finalA, IEaseFunction easeFunction) {
		super();

		this.mSecondsElapsed = 0;
		this.mDuration = duration;
		
		this.mInitialR = r;
		this.mInitialG = g;
		this.mInitialB = b;
		this.mInitialA = a;
		
		this.mFinalR = finalR;
		this.mFinalG = finalG;
		this.mFinalB = finalB;
		this.mFinalA = finalA;

		this.mEaseFunction = easeFunction;
		
		this.mVelocityR = (finalR - r) / duration;
		this.mVelocityG = (finalG - g) / duration;
		this.mVelocityB = (finalB - b) / duration;
		this.mVelocityA = (finalA - a) / duration;
		/*if(finalR > r) this.mVelocityR = (finalR - r) / duration;
		else this.mVelocityR = (r - finalR) / duration;
		if (finalG > g) this.mVelocityG = (finalG - g) / duration;
		else this.mVelocityG = (g - finalG) / duration;
		if(finalB > b) this.mVelocityB = (finalB - b) / duration;
		else this.mVelocityB = (b - finalB) / duration;
		if(finalA > a) this.mVelocityA = (finalA - a) / duration;
		else this.mVelocityA = (a - finalA) / duration;*/

		this.mFinished = false;
		
		this.mEntityModifierListener = null;
	}
	public ColorEntityModifier(float duration, float r, float g, float b, float a, float finalR, float finalG, float finalB, float finalA, IEaseFunction easeFunction, IEntityModifierListener entityModifierListener) {
		this(duration, r, g, b, a, finalR, finalG, finalB, finalA, easeFunction);
		
		this.mEntityModifierListener = entityModifierListener;
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
		//shape.setPosition(mInitialX, mInitialY);
		shape.setColor(mInitialR, mInitialG, mInitialB, mInitialA);
		
		if(mEntityModifierListener != null)
			mEntityModifierListener.onModifierStarted(shape);
	}
	@Override
	public void onModifierFinished(Shape shape) {		
		//shape.setPosition(mFinalX, mFinalY);
		shape.setColor(mFinalR, mFinalG, mFinalB, mFinalA);

		if(mEntityModifierListener != null && !mFinished)
			mEntityModifierListener.onModifierFinished(shape);
		
		this.mFinished = true;
	}
	@Override
	public void onUpdate(Shape shape, float alpha) {
		float percentage;

		if(mSecondsElapsed == 0)
			onModifierStarted(shape);

		this.mSecondsElapsed += alpha;

		percentage = mEaseFunction.getPercentage(mSecondsElapsed, mDuration);

		shape.setColor(mInitialR + mVelocityR*percentage*mDuration, mInitialG + mVelocityG*percentage*mDuration, mInitialB + mVelocityB*percentage*mDuration, mInitialA + mVelocityA*percentage*mDuration);
		
		
		if(mSecondsElapsed >= mDuration)
			onModifierFinished(shape);
	}



	// ===========================================================
	// Methods
	// ===========================================================

}

package com.simpleglengine.entity.scene.background;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

import com.simpleglengine.entity.Entity;

public class ColorBackground implements Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private float mR, mG, mB, mA;
	private boolean mExecuted;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public ColorBackground(float r, float g, float b, float a) {
		this.mR = r;
		this.mG = g;
		this.mB = b;
		this.mA = a;
		
		this.mExecuted = false;
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public void setColor(float r, float g, float b, float a) {
		this.mR = r;
		this.mG = g;
		this.mB = b;
		this.mA = a;
		
		this.mExecuted = false;
	}
	
	@Override
	public void setScale(float scale) {
		
	}

	@Override
	public float getScale() {
		return 1;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onLoadSurface(GL10 gl) {
		gl.glClearColor(mR, mG, mB, mA);
		this.mExecuted = true;
	}
	
	@Override
	public void onDraw(GL10 gl) {
		if(!this.mExecuted) {
			gl.glClearColor(mR, mG, mB, mA);
			this.mExecuted = true;
		}
	}

	@Override
	public void onUpdate(float alpha) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean onTouch(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

}

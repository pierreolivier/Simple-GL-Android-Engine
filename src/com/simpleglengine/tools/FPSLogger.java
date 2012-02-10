package com.simpleglengine.tools;

import android.util.Log;

public class FPSLogger {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mFramesNumber;
	private double mLastUpdate;
	private int mFps;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public FPSLogger() {
		this.mFramesNumber = 0;
		this.mLastUpdate = -1;
		this.mFps = 60;
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getFPS() {
		return mFps;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void log() {
		if(mLastUpdate == -1)
			mLastUpdate = System.currentTimeMillis();
		
		mFramesNumber++;
		if(mLastUpdate + 1000 <= System.currentTimeMillis()) {
			Log.e(FPSLogger.class.toString(), "FPS: "+mFramesNumber);
			
			mFps = mFramesNumber;
			mFramesNumber = 0;
			mLastUpdate = System.currentTimeMillis();
		}
	}
}

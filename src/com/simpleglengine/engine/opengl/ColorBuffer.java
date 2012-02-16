package com.simpleglengine.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.util.Log;

public class ColorBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private float mRed1, mGreen1, mBlue1, mAlpha1;
	private float mRed2, mGreen2, mBlue2, mAlpha2;
	private float mRed3, mGreen3, mBlue3, mAlpha3;
	private float mRed4, mGreen4, mBlue4, mAlpha4;
	private FloatBuffer mColorBuffer;

	// ===========================================================
	// Constructors
	// ===========================================================
	public ColorBuffer() {
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public FloatBuffer getColorBuffer() {
		return mColorBuffer;
	}
	public void setColor1(float r, float g, float b, float a) {
		this.mRed1 = r;
		this.mGreen1 = g;
		this.mBlue1 = b;
		this.mAlpha1 = a;
	}
	public void setColor2(float r, float g, float b, float a) {
		this.mRed2 = r;
		this.mGreen2 = g;
		this.mBlue2 = b;
		this.mAlpha2 = a;
	}
	public void setColor3(float r, float g, float b, float a) {
		this.mRed3 = r;
		this.mGreen3 = g;
		this.mBlue3 = b;
		this.mAlpha3 = a;
	}
	public void setColor4(float r, float g, float b, float a) {
		this.mRed4 = r;
		this.mGreen4 = g;
		this.mBlue4 = b;
		this.mAlpha4 = a;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods 
	// ===========================================================
	public void generate() {
		float colorBuffer[] = {
				mRed1, mGreen1, mBlue1, mAlpha1,
				mRed2, mGreen2, mBlue2, mAlpha2,
				mRed3, mGreen3, mBlue3, mAlpha3,
				mRed4, mGreen4, mBlue4, mAlpha4
		};
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(colorBuffer.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mColorBuffer = byteBuffer.asFloatBuffer();
		this.mColorBuffer.put(colorBuffer);
		this.mColorBuffer.position(0);
	}
}

package com.simpleglengine.shapes;

import java.nio.FloatBuffer;

public abstract class Shape {
	protected int mX, mY;
	protected int mRotation;
	protected int mXRotationCenter, mYRotationCenter;

	protected FloatBuffer mVertexBuffer;
	protected Texture mTexture = null;

	protected Shape(int x, int y) {
		super();
		
		this.mRotation = 0;
		this.mXRotationCenter = 0;
		this.mYRotationCenter = 0;
		this.mX = x;
		this.mY = y;		
	}
	
	protected void translate(int dX, int dY) {
		this.mX += dX;
		this.mY += dY;
	}
	protected void translateX(int dX) {
		this.mX += dX;
	}
	protected void translateY(int dY) {
		this.mY += dY;
	}
	protected void rotate(int angle) {
		this.mRotation = angle;
	}
	protected void setRotationCenter(int x, int y) {
		this.mXRotationCenter = x;
		this.mYRotationCenter = y;
	}
}

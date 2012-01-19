package com.simpleglengine.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class Shape {
	protected int mX, mY;
	protected int mRotation;
	protected int mXRotationCenter, mYRotationCenter;

	protected FloatBuffer mVertexBuffer;
	protected Texture mTexture = null;
	protected float [] mVertex = null, mNoScaledVertex = null;

	protected Shape(int x, int y) {
		super();
		
		this.mRotation = 0;
		this.mXRotationCenter = 0;
		this.mYRotationCenter = 0;
		this.mX = x;
		this.mY = y;		
	}
	
	public void translate(int dX, int dY) {
		this.mX += dX;
		this.mY += dY;
	}
	public void translateX(int dX) {
		this.mX += dX;
	}
	public void translateY(int dY) {
		this.mY += dY;
	}
	public void rotate(int angle) {
		this.mRotation = angle;
	}
	public void setRotationCenter(int x, int y) {
		this.mXRotationCenter = x;
		this.mYRotationCenter = y;
	}
	protected void loadVertexBuffer(float [] vertex) {
		this.mVertex = vertex;
		this.mNoScaledVertex = this.mVertex.clone();
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertex.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mVertexBuffer = byteBuffer.asFloatBuffer();
		this.mVertexBuffer.put(vertex);
		this.mVertexBuffer.position(0);
	}
	
}

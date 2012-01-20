package com.simpleglengine.entity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.engine.opengl.Texture;

public abstract class Shape implements Entity {
	protected float mX, mY;
	protected int mRotation;
	protected int mXRotationCenter, mYRotationCenter;
	
	protected FloatBuffer mVertexBuffer;
	protected Texture mTexture = null;
	protected float [] mVertex = null, mNoScaledVertex = null;
	protected float mScale;

	protected Shape(int x, int y) {
		super();
		
		this.mRotation = 0;
		this.mXRotationCenter = 0;
		this.mYRotationCenter = 0;
		this.mX = x;
		this.mY = y;		
		
		this.mScale = 1;
	}
	
	
	
	public float getX() {
		return mX;
	}
	public float getY() {
		return mY;
	}
	public int getRotation() {
		return mRotation;
	}

	public void setPosition(float x, float y) {
		this.mX = x;
		this.mY = y;
	}

	public void translate(float dX, float dY) {
		this.mX += dX;
		this.mY += dY;
	}
	public void translateX(float dX) {
		this.mX += dX;
	}
	public void translateY(float dY) {
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

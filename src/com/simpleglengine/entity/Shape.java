package com.simpleglengine.entity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.GLBuffer;
import com.simpleglengine.engine.opengl.Texture;

public abstract class Shape implements Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mX, mY;
	protected int mRotation;
	protected int mXRotationCenter, mYRotationCenter;
	
	protected FloatBuffer mVertexBuffer;
	protected float [] mVertex = null, mNoScaledVertex = null;
	protected Texture mTexture = null;
	protected GLBuffer mBuffer = null;
	
	protected float mScale;
	protected boolean mPostRescale;

	protected PhysicsHandler mPhysicsHandler = null;
	// ===========================================================
	// Constructors
	// ===========================================================
	protected Shape(int x, int y) {
		super();
		
		this.mRotation = 0;
		this.mXRotationCenter = 0;
		this.mYRotationCenter = 0;
		this.mX = x;
		this.mY = y;		
		
		this.mScale = 1;
		this.mPostRescale = false;
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public PhysicsHandler getPhysicsHandler() {
		return mPhysicsHandler;
	}
	public void setPhysicsHandler(PhysicsHandler mPhysicsHandler) {
		this.mPhysicsHandler = mPhysicsHandler;
	}
	
	public float getX() {
		return mX;
	}
	public void setX(float x) {
		this.mX = x;
	}
	public float getY() {
		return mY;
	}
	public void setY(float y) {
		this.mY = y;
	}
	public int getRotation() {
		return mRotation;
	}
	public void setRotation(int angle) {
		this.mRotation = angle;
	}
	public void setRotationCenter(int x, int y) {
		this.mXRotationCenter = x;
		this.mYRotationCenter = y;
	}
	public void setPosition(float x, float y) {
		this.mX = x;
		this.mY = y;
	}
	

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void onUpdate(float alpha) {
		if(this.mPhysicsHandler != null)
			this.mPhysicsHandler.onUpdate(alpha);
	}
	// ===========================================================
	// Methods
	// ===========================================================
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

package com.simpleglengine.entity.primitive;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.simpleglengine.engine.opengl.GLBuffer;
import com.simpleglengine.entity.Shape;

public class Rectangle extends Shape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	//private float mRed, mGreen, mBlue, mAlpha;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Rectangle(int x, int y, int width, int height, float r, float g, float b, float a) {
		super(x, y, width, height);


		float rectangle[] = {
				width, 	0f, 		0,
				width, 	height, 	0,				
				0f, 	0f, 		0,			
				0f, 	height, 	0				
		};
		this.mBuffer = new GLBuffer(rectangle);

		this.mA = a;
		this.mR = r;
		this.mG = g;
		this.mB = b;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	@Override
	public void setScale(float scale) {		

		super.mScale = scale;

		float sprite[] = {
				mWidth*scale, 	0f, 				0,
				mWidth*scale, 	mHeight*scale, 	0,				
				0f, 			0f, 				0,			
				0f, 			mHeight*scale, 	0				
		};


		mBuffer.setVertex(sprite);

		this.mPostRescale = true;
	}

	@Override
	public float getScale() {
		return super.mScale;
	}

	/*
	public void setColor(float r, float g, float b, float a) {
		this.mA = a;
		this.mR = r;
		this.mG = g;
		this.mB = b;
	}*/

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onLoadSurface(GL10 gl) {
		mBuffer.loadBuffer((GL11) gl);
	}

	@Override
	public void onDraw(GL10 gl10) {
		GL11 gl = (GL11) gl10;

		gl.glLoadIdentity();

		if(mPostRescale) {
			mBuffer.loadBuffer(gl);
			mPostRescale = false;
		}


		gl.glTranslatef(super.mX, super.mY, 0);
		gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(-super.mXRotationCenter*mScale, -super.mYRotationCenter*mScale, 0);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, mBuffer.getBufferId());
		gl.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

		gl.glColor4f(mR, mG, mB, mA);

		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
	}

	// ===========================================================
	// Methods 
	// ===========================================================

}

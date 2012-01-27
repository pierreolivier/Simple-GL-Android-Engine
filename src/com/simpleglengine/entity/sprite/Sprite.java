package com.simpleglengine.entity.sprite;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.GLBuffer;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.Entity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.tools.ScreenTools;

public class Sprite extends Shape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected Texture mTexture;
	protected PhysicsHandler mPhysicsHandler = null;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Sprite(Texture texture, int x, int y) {
		super(x, y);
		this.mTexture = texture;

		float width = mTexture.getWidth(), height = mTexture.getHeight();
		float sprite[] = {
				ScreenTools.x(width), 	ScreenTools.y(0f), 		0,
				ScreenTools.x(width), 	ScreenTools.y(height), 	0,				
				ScreenTools.x(0f), 		ScreenTools.y(0f), 		0,			
				ScreenTools.x(0f), 		ScreenTools.y(height), 	0				
		};

		this.mBuffer = new GLBuffer(sprite);

		loadVertexBuffer(sprite);


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
	public void setScale(float scale) {		
		float width = mTexture.getWidth(), height = mTexture.getHeight();

		super.mScale = scale;

		float sprite[] = {
				ScreenTools.x(width*scale), 	ScreenTools.y(0f), 				0,
				ScreenTools.x(width*scale), 	ScreenTools.y(height*scale), 	0,				
				ScreenTools.x(0f), 				ScreenTools.y(0f), 				0,			
				ScreenTools.x(0f), 				ScreenTools.y(height*scale), 	0				
		};

		
		mBuffer.setVertex(sprite);
		
		this.mPostRescale = true;
		
		/*
		//mBuffer.loadBuffer((GL11) gl);
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(sprite.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mVertexBuffer = byteBuffer.asFloatBuffer();
		this.mVertexBuffer.put(sprite);
		this.mVertexBuffer.position(0);*/
	}
	@Override
	public float getScale() {
		return super.mScale;
	}

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

		//float xCenter = ScreenTools.getWidth()/2, yCenter = ScreenTools.getHeight()/2;
		float xCenter = 0, yCenter = 0;

		gl.glLoadIdentity();

		if(mPostRescale) {
			mBuffer.loadBuffer(gl);
			mPostRescale = false;
		}
		/*
<<<<<<< HEAD

=======
		if(mPostRescale) {

		}

		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture.getTextureId());

>>>>>>> a6a51402ff1cbba58d95b432d96c632b26363b58*/
		//gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture.getTextureId());

		gl.glTranslatef(ScreenTools.dX(-xCenter+super.mXRotationCenter+mX), ScreenTools.dY(-yCenter+super.mYRotationCenter+mY), 0); //Offset
		gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f); //Rotation en degre ?
		gl.glTranslatef(ScreenTools.dX(xCenter-super.mXRotationCenter), ScreenTools.dY(yCenter-super.mXRotationCenter), 0); //Millieu + centre de rotation

		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture.getTextureId());
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.mTexture.getTextureBuffer());

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, mBuffer.getBufferId());
		gl.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
		//gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer);


		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);


	}

	@Override
	public void onUpdate(float alpha) {

		if(this.mPhysicsHandler != null)
			this.mPhysicsHandler.onUpdate(alpha);

	}

	// ===========================================================
	// Methods
	// ===========================================================

}

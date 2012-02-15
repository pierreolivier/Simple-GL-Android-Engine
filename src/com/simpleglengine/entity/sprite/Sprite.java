package com.simpleglengine.entity.sprite;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.GLBuffer;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.tools.GLGraphics;
import com.simpleglengine.tools.ScreenTools;

public class Sprite extends Shape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	//protected Texture mTexture;
	protected TextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Sprite(TextureRegion textureRegion, int x, int y) {
		super(x, y, textureRegion.getWidth(), textureRegion.getHeight());
		this.mTextureRegion = textureRegion;

		float width = mTextureRegion.getWidth(), height = mTextureRegion.getHeight();
		float sprite[] = {
				width, 	0f, 		0,
				width, 	height, 	0,				
				0f, 	0f, 		0,			
				0f, 	height, 	0				
		};

		this.mBuffer = new GLBuffer(sprite);

		//loadVertexBuffer(sprite);


	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setScale(float scale) {		
		float width = mTextureRegion.getWidth(), height = mTextureRegion.getHeight();

		super.mScale = scale;

		float sprite[] = {
				width*scale, 	0f, 				0,
				width*scale, 	height*scale, 	0,				
				0f, 			0f, 				0,			
				0f, 			height*scale, 	0				
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
	
	/*
	public float getWidth() {
		return mTexture.getWidth();
	}
	
	
	public float getHeight() {
		return mTexture.getHeight();
	}
	
	public float getScaledWidth() {
		return getWidth()*mScale;
	}
	public float getScaledHeight() {
		return getHeight()*mScale;
	}
	 */
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
		
		float xCenter = 0, yCenter = 0;

		gl.glLoadIdentity();

		if(mPostRescale) {
			mBuffer.loadBuffer(gl);
			mPostRescale = false;
		}
		

		
		gl.glTranslatef(super.mX, super.mY, 0);
		gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(-super.mXRotationCenter*mScale, -super.mYRotationCenter*mScale, 0);
		
		//gl.glTranslatef(super.mXRotationCenter, super.mXRotationCenter, 0);
		//gl.glTranslatef(super.mXRotationCenter+mX, super.mYRotationCenter+mY, 0); //Offset
		//gl.glTranslatef(super.mXRotationCenter, super.mYRotationCenter, 0); //Offset
		//gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f); //Rotation en degre ?
		//gl.glTranslatef(-super.mXRotationCenter, -super.mXRotationCenter, 0); //Milieu + centre de rotation
		if(GLGraphics.currentTextureId != mTextureRegion.getTexture().getTextureId())
			gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureRegion.getTexture().getTextureId());
		GLGraphics.currentTextureId = mTextureRegion.getTexture().getTextureId();
		
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.mTextureRegion.getTextureBuffer());

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, mBuffer.getBufferId());
		gl.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
			//gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer);

		gl.glColor4f(mR, mG, mB, mA);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		
		//gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);


	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);

	}

	// ===========================================================
	// Methods
	// ===========================================================

}

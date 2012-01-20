package com.simpleglengine.entity.sprite;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.Entity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.tools.ScreenTools;

public class Sprite extends Shape {
	private Texture mTexture;
	private PhysicsHandler mPhysicsHandler = null;

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
		loadVertexBuffer(sprite);


	}

	public PhysicsHandler getPhysicsHandler() {
		return mPhysicsHandler;
	}
	public void setPhysicsHandler(PhysicsHandler mPhysicsHandler) {
		this.mPhysicsHandler = mPhysicsHandler;
	}

	@Override
	public void onDraw(GL10 gl) {

		float xCenter = ScreenTools.getWidth()/2, yCenter = ScreenTools.getHeight()/2;

		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture.getTextureId());


		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.mTexture.getTextureBuffer());

		gl.glTranslatef(ScreenTools.dX(-xCenter+super.mXRotationCenter+mX), ScreenTools.dY(-yCenter+super.mYRotationCenter+mY), 0); //Offset
		gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f); //Rotation en degre ?
		gl.glTranslatef(ScreenTools.dX(xCenter-super.mXRotationCenter), ScreenTools.dY(yCenter-super.mXRotationCenter), 0); //Millieu + centre de rotation



		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

	}

	@Override
	public void onUpdate(float alpha) {

		if(this.mPhysicsHandler != null)
			this.mPhysicsHandler.onUpdate(alpha);

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

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(sprite.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mVertexBuffer = byteBuffer.asFloatBuffer();
		this.mVertexBuffer.put(sprite);
		this.mVertexBuffer.position(0);
	}
	public void resetScale() {
		this.mVertex = mNoScaledVertex.clone();

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(this.mVertex.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mVertexBuffer = byteBuffer.asFloatBuffer();
		this.mVertexBuffer.put(this.mVertex);
		this.mVertexBuffer.position(0);

		super.mScale = 1;
	}
	@Override
	public float getScale() {
		return super.mScale;
	}

}

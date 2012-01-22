package com.simpleglengine.entity.sprite;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.tools.ScreenTools;

public class SpriteBatch extends Sprite {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private boolean mPostBind, mPostUnbind;
	private float [] mXs, mYs;

	// ===========================================================
	// Constructors
	// ===========================================================
	public SpriteBatch(Texture texture, float [] x, float [] y) {
		super(texture, (int) x[0],  (int) y[0]);

		this.mPostBind = false;
		this.mPostUnbind = false;

		this.mXs = x;
		this.mYs = y;
	}



	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void onDraw(GL10 gl) {

		float xCenter = ScreenTools.getWidth()/2, yCenter = ScreenTools.getHeight()/2;

		gl.glLoadIdentity();

		//if(mPostBind) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture.getTextureId());
		this.mPostBind = false;
		//}



		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.mTexture.getTextureBuffer());

		for(float x : mXs) {
			for(float y : mYs) {
				gl.glPushMatrix();

				gl.glTranslatef(ScreenTools.dX(-xCenter+super.mXRotationCenter+x), ScreenTools.dY(-yCenter+super.mYRotationCenter+y), 0); //Offset
				gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f); //Rotation en degre ?
				gl.glTranslatef(ScreenTools.dX(xCenter-super.mXRotationCenter), ScreenTools.dY(yCenter-super.mXRotationCenter), 0); //Millieu + centre de rotation

				gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);	

				gl.glPopMatrix();
			}
		}



		//if(mPostUnbind) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		this.mPostUnbind = false;
		//}



	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void bind() {
		this.mPostBind = true;
	}


	public void unbind() {
		this.mPostUnbind = true;
	}
}

package com.simpleglengine.entity.sprite;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.tools.ScreenTools;

public class SpriteBatch extends Sprite {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	//private boolean mPostBind, mPostUnbind;
	protected float [] mXs, mYs;

	// ===========================================================
	// Constructors
	// ===========================================================
	public SpriteBatch(Texture texture, float [] x, float [] y) {
		super(texture, (int) x[0],  (int) y[0]);

		//this.mPostBind = false; 
		//this.mPostUnbind = false;

		this.mXs = x;
		this.mYs = y;
	}



	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void onDraw(GL10 gl10) {
		GL11 gl = (GL11) gl10;
		gl.glLoadIdentity();

		//if(mPostBind) {
		gl.glBindTexture(GL11.GL_TEXTURE_2D, mTexture.getTextureId());
		gl.glTexCoordPointer(2, GL11.GL_FLOAT, 0, this.mTexture.getTextureBuffer());
		
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, mBuffer.getBufferId());
		gl.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
		
		gl.glColor4f(mR, mG, mB, mA);
		//gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer);
		//this.mPostBind = false;
		//}
		
		
		
		
		
		float lastX = -1, lastY = -1;
		
		for(int i = 0; i < mXs.length;i++) {
			float x = mXs[i], y = mYs[i];

			gl.glTranslatef(ScreenTools.dX(x-lastX), ScreenTools.dY(y-lastY), 0); //Offset
			
			gl.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);	
			
			lastX = x;
			lastY = y;
		}



		//if(mPostUnbind) {
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
		//this.mPostUnbind = false;
		//}



	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	/*
	public void bind() {
		this.mPostBind = true;
	}


	public void unbind() {
		this.mPostUnbind = true;
	}*/
}

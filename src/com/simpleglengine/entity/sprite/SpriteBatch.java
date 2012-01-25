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

		gl.glLoadIdentity();

		//if(mPostBind) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture.getTextureId());
		this.mPostBind = false;
		//}

		

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.mTexture.getTextureBuffer());
		
		float lastX = -1, lastY = -1;
		
		for(int i = 0; i < mXs.length;i++) {
			float x = mXs[i], y = mYs[i];

			gl.glTranslatef(ScreenTools.dX(x-lastX), ScreenTools.dY(y-lastY), 0); //Offset
			
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);	
			
			lastX = x;
			lastY = y;
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

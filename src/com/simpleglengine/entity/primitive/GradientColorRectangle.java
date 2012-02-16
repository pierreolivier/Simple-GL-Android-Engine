package com.simpleglengine.entity.primitive;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.simpleglengine.engine.opengl.ColorBuffer;

public class GradientColorRectangle extends Rectangle {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private ColorBuffer mColorBuffer;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GradientColorRectangle(int x, int y, int width, int height, ColorBuffer colorBuffer) {
		super(x, y, width, height, 0.5f, 1.0f, 1.0f, 1.0f);
		
		this.mColorBuffer = colorBuffer;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onDraw(GL10 gl10) {		
		GL11 gl = (GL11) gl10;
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		gl.glLoadIdentity();

		if(mPostRescale) {
			mBuffer.loadBuffer(gl);
			mPostRescale = false;
		}


		gl.glTranslatef(super.mX, super.mY, 0);
		gl.glRotatef(this.mRotation, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(-super.mXRotationCenter*mScale, -super.mYRotationCenter*mScale, 0);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		
		gl.glColorPointer(4, GL11.GL_FLOAT, 0, mColorBuffer.getColorBuffer());
		
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, mBuffer.getBufferId());
		gl.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
		

		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
		
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	// ===========================================================
	// Methods 
	// ===========================================================

}

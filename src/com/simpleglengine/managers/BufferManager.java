package com.simpleglengine.managers;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.simpleglengine.engine.opengl.GLBuffer;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.GLGraphics;

public class BufferManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mBufferNumber;
	private int[] mBuffers;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BufferManager(Context context) {
		super();

		this.mBufferNumber = 0;
		this.mBuffers = new int[0];
		
		BitmapTools.setContext(context);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public GLBuffer loadBuffer(float [] vertex) {
		GL11 gl = (GL11) GLGraphics.currentGLContext;
		GLBuffer buffer;
		int [] textures = mBuffers.clone();

		mBuffers = new int[mBufferNumber+1];
		for(int i = 0; i<mBufferNumber;i++)
			mBuffers[i] = textures[i];
		
		gl.glGenBuffers(mBufferNumber+1, mBuffers, 0);	

		gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, mBuffers[mBufferNumber]);
		
		buffer = new GLBuffer(vertex);		
		gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, vertex.length, buffer.getBuffer(), GL11.GL_DYNAMIC_DRAW);
		
		this.mBufferNumber++;
		
		return buffer;
	}

	
	
}

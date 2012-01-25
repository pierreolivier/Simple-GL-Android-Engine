package com.simpleglengine.managers;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.simpleglengine.engine.opengl.GLBuffer;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.tools.BitmapTools;

public class BufferManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private GL11 gl;

	private int mBufferNumber;
	private int[] mBuffers;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BufferManager(Context context, GL10 gl) {
		super();

		this.mBufferNumber = 0;
		this.mBuffers = new int[0];
		
		this.gl = (GL11) gl;
		
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

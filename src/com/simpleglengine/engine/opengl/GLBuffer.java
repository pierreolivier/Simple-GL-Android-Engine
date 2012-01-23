package com.simpleglengine.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class GLBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mBufferId;
	private FloatBuffer mBuffer;
	private float [] mVertex;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public GLBuffer(float [] vertex) {
		super();
		
		this.mVertex = vertex;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getBufferId() {
		return mBufferId;
	}
	public FloatBuffer getBuffer() {
		return mBuffer;
	}
	public float [] getVertex() {
		return mVertex;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void loadBuffer(GL11 gl) {
		
		
		int [] buffers = new int[1];
		
		gl.glGenBuffers(1, buffers, 0);
		gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, buffers[0]);
		
		this.mBufferId = buffers[0];
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(mVertex.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mBuffer = byteBuffer.asFloatBuffer();
		this.mBuffer.put(mVertex);
		this.mBuffer.position(0);
		
		gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, mVertex.length, mBuffer, GL11.GL_DYNAMIC_DRAW);
	}
	
	
}
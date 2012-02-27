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
		
		this.mBufferId = -1;
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
	public void setVertex(float [] vertex) {
		this.mVertex = vertex;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void loadBuffer(GL11 gl) {		
		int [] buffers = new int[1];
		
		if(mBufferId != -1) {
			int [] tmp = { mBufferId };
			gl.glDeleteBuffers(1, tmp, 0);
		}
		
		gl.glGenBuffers(1, buffers, 0);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, buffers[0]);
		
		this.mBufferId = buffers[0];
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(mVertex.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mBuffer = byteBuffer.asFloatBuffer();
		this.mBuffer.put(mVertex);
		this.mBuffer.position(0);
		
		gl.glBufferData(GL11.GL_ARRAY_BUFFER, mVertex.length * 4, mBuffer, GL11.GL_DYNAMIC_DRAW);
		
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
	}
	
	public void unloadBuffer(GL11 gl) {
		if(mBufferId != -1) {
			int [] tmp = { mBufferId };
			gl.glDeleteBuffers(1, tmp, 0);
			mBufferId = -1;
		}
	}
	
	
}
package com.simpleglengine.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Texture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mTextureId;
	
	private int mWidth, mHeight;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public Texture(int textureId, int width, int height) {
		// Set texture id
		this.mTextureId = textureId;
		
		// Set width and height
		this.mWidth = width;
		this.mHeight = height;
		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================	
	public int getTextureId() {
		return mTextureId;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	
	
}

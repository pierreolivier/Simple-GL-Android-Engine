package com.simpleglengine.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Texture {
	private int mTextureId;
	private FloatBuffer mTextureBuffer;
	
	private int mWidth, mHeight;
	
	public Texture(int textureId, int width, int height) {
		// Set texture id
		this.mTextureId = textureId;
		
		// Set width and height
		this.mWidth = width;
		this.mHeight = height;
		
		// Set coordinates on shape
		float textureCoordinates[] = {
				0.0f, 0.0f,
				0.0f, 1.0f,
				1.0f, 0.0f,
				1.0f, 1.0f 
		};
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mTextureBuffer = byteBuffer.asFloatBuffer();
		this.mTextureBuffer.put(textureCoordinates);
		this.mTextureBuffer.position(0);
	}

	public int getTextureId() {
		return mTextureId;
	}
	public FloatBuffer getTextureBuffer() {
		return mTextureBuffer;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}
	
	
	
}

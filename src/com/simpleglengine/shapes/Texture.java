package com.simpleglengine.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Texture {
	private int mTextureId;
	private FloatBuffer mTextureBuffer;
	
	public Texture(int textureId) {
		// Set texture id
		this.mTextureId = textureId;
		
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
}

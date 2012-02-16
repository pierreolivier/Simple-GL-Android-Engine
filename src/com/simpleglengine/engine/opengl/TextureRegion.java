package com.simpleglengine.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextureRegion {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Texture mTexture;
	private FloatBuffer mTextureBuffer;
	private float mX, mY, mWidth, mHeight;
	
	private float u1, v1, u2, v2;
	

	// ===========================================================
	// Constructors
	// ===========================================================
	public TextureRegion(Texture texture, float x, float y, float width, float height) {
		this.mTexture = texture;
		
		this.mX = x;
		this.mY = y;
		this.mWidth = width;
		this.mHeight = height;
		
		this.u1 = x / texture.getWidth();
		this.v1 = y / texture.getHeight();
		this.u2 = this.u1 + width / texture.getWidth();
		this.v2 = this.v1 + height / texture.getHeight();
		
		float textureCoordinates[] = {
				u2, v1,
				u2, v2,
				u1, v1,
				u1, v2 
		};
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.mTextureBuffer = byteBuffer.asFloatBuffer();
		this.mTextureBuffer.put(textureCoordinates);
		this.mTextureBuffer.position(0);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public FloatBuffer getTextureBuffer() {
		return mTextureBuffer;
	}

	public Texture getTexture() {
		return mTexture;
	}

	public float getWidth() {
		return mWidth;
	}

	public float getHeight() {
		return mHeight;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}

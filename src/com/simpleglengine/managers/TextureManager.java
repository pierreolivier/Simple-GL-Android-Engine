package com.simpleglengine.managers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.simpleglengine.shapes.Texture;

public class TextureManager {
	private Context mContext;
	private GL10 gl;

	private int mTextureNumber;
	private int[] mTextures;

	public TextureManager(Context context, GL10 gl) {
		super();

		this.mTextureNumber = 0;
		mTextures = new int[0];
	}

	public Texture loadTextureFromBitmap(Bitmap bitmap) {
		int [] textures = mTextures.clone();

		gl.glDeleteTextures(mTextureNumber, mTextures, 0);

		mTextures = new int[mTextureNumber+1];
		gl.glGenTextures(mTextureNumber+1, mTextures, 0);

		for(int i = 0; i<mTextureNumber;i++)
			mTextures[i] = textures[i];

		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextures[mTextureNumber]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		this.mTextureNumber++;
		
		return new Texture(mTextures[mTextureNumber]);
	}
	public Texture loadTextureRegionFromBitmap(Bitmap bitmap, int xOffset, int yOffset, int width, int height) {
		int [] textures = mTextures.clone();

		gl.glDeleteTextures(mTextureNumber, mTextures, 0);

		mTextures = new int[mTextureNumber+1];
		gl.glGenTextures(mTextureNumber+1, mTextures, 0);

		for(int i = 0; i<mTextureNumber;i++)
			mTextures[i] = textures[i];

		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextures[mTextureNumber]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bitmap.getWidth() * bitmap.getHeight() * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		bitmap.copyPixelsToBuffer(byteBuffer);
		byteBuffer.position(0);		
		gl.glTexSubImage2D(GL10.GL_TEXTURE_2D, 0, xOffset, yOffset, width, height, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, byteBuffer);

		this.mTextureNumber++;
		
		return new Texture(mTextures[mTextureNumber]);
	}
}

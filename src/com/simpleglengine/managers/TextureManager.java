package com.simpleglengine.managers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.simpleglengine.OpenGLES10Renderer;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;

public class TextureManager {
	private GL10 gl;

	private int mTextureNumber;
	private int[] mTextures;

	public TextureManager(Context context, GL10 gl) {
		super();

		this.mTextureNumber = 0;
		mTextures = new int[0];
		
		this.gl = gl;
		
		BitmapTools.setContext(context);
	}

	public Texture loadTextureFromBitmap(Bitmap bitmap) {
		int [] textures = mTextures.clone();

		mTextures = new int[mTextureNumber+1];
		for(int i = 0; i<mTextureNumber;i++)
			mTextures[i] = textures[i];
		gl.glGenTextures(mTextureNumber+1, mTextures, 0);

		

		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextures[mTextureNumber]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		this.mTextureNumber++;
		
		return new Texture(mTextures[mTextureNumber-1], bitmap.getWidth(), bitmap.getHeight());
	}
	public Texture loadTextureRegionFromBitmap(Bitmap bitmap, int xOffset, int yOffset, int width, int height) {
		Bitmap bmp = BitmapTools.subBitmap(bitmap, bitmap.getWidth() - (width  + xOffset), yOffset, width, height);
		return loadTextureFromBitmap(bmp);
	}

	
	
}

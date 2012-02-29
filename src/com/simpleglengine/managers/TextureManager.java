package com.simpleglengine.managers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.opengl.GLUtils;

import com.simpleglengine.OpenGLES10Renderer;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.GLGraphics;

public class TextureManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int mTextureNumber;
	private int[] mTextures;
	
	private ArrayList<TextureRegion> mTextureRegions;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TextureManager(Context context) {
		super();

		this.mTextureNumber = 0;
		this.mTextures = new int[0];

		this.mTextureRegions = new ArrayList<TextureRegion>();
		
		BitmapTools.setContext(context);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public ArrayList<TextureRegion> getTextureRegions() {
		return mTextureRegions;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public Texture loadTextureFromBitmap(Bitmap bitmap) {	
		GL10 gl = GLGraphics.currentGLContext;
		
		Matrix aMatrix = new Matrix();
		aMatrix.preScale(-1.0f, 1.0f);
		//bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), aMatrix, false);
		
		int [] textures = mTextures.clone();

		
		mTextures = new int[mTextureNumber+1];
		for(int i = 0; i<mTextureNumber;i++)
			mTextures[i] = textures[i];
		gl.glGenTextures(mTextureNumber+1, mTextures, 0);

		

		gl.glBindTexture(GL11.GL_TEXTURE_2D, mTextures[mTextureNumber]);
		gl.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		gl.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		
		GLUtils.texImage2D(GL11.GL_TEXTURE_2D, 0, bitmap, 0);
		

		this.mTextureNumber++;
		
		return new Texture(mTextures[mTextureNumber-1], bitmap.getWidth(), bitmap.getHeight());
	}

	public Texture loadTextureRegionFromBitmap(Bitmap bitmap, int xOffset, int yOffset, int width, int height) {
		//Bitmap bmp = BitmapTools.subBitmap(bitmap, bitmap.getWidth() - (width  + xOffset), yOffset, width, height);
		Bitmap bmp = BitmapTools.subBitmap(bitmap, xOffset, yOffset, width, height);
		return loadTextureFromBitmap(bmp);
	}
	
	public TextureRegion createTextureRegion(Texture texture, float x, float y, float width, float height) {
		TextureRegion textureRegion = new TextureRegion(texture, x, y, width, height);
		
		mTextureRegions.add(textureRegion);
		
		return textureRegion;
	}

	
	
}

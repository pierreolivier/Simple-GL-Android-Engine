package com.simpleglengine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.managers.TextureManager;
import com.simpleglengine.shapes.Texture;
import com.simpleglengine.shapes.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

public class OpenGLES10Renderer implements Renderer {


	private Context context;


	public static float ratio = 1.77916667f;
	public static int width = 854;
	public static int height = 480;
	
	private TextureManager mTextureManager;
	private Texture mTexture;
	private Sprite mSprite;
	
	public OpenGLES10Renderer(Context context) {
		this.context = context;
		
	}

	public void init(GL10 gl) {
		this.mTextureManager = new TextureManager(context, gl);
		
		Bitmap bmp = BitmapTools.loadBitmapFromRessource(context, R.drawable.heros, Color.rgb(255, 0, 255));
		this.mTexture = this.mTextureManager.loadTextureFromBitmap(bmp);
		this.mSprite = new Sprite(this.mTexture, 0, 0);
		mSprite.setScale(2f);
		mSprite.rotate(90);
		mSprite.setRotationCenter(32, 32);
		mSprite.translate(600, 200);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

	}	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		this.mSprite.onDraw(gl);
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		this.width = width;
		this.height = height;
		ratio = (float) width / height;
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glFrustumf(ratio, -ratio, 1, -1, 3, 7);
		GLU.gluLookAt(gl, 0, 0, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		init(gl);
	}

	
	

}

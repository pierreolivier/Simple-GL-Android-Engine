package com.simpleglengine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.managers.AudioManager;
import com.simpleglengine.managers.BufferManager;
import com.simpleglengine.managers.FontManager;
import com.simpleglengine.managers.TextureManager;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.FPSLogger;
import com.simpleglengine.tools.GLGraphics;

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
import android.view.MotionEvent;

public class OpenGLES10Renderer implements Renderer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private SimpleGLEngineActivity context;

	public static float ratio = 1.77916667f;
	public static int width = 854;
	public static int height = 480;

	// GL context
	// GL10 mGL;
	private boolean mReady;

	// Managers
	private TextureManager mTextureManager;
	private FontManager mFontManager;
	private AudioManager mAudioManager;

	// Scene
	private Scene mScene;

	// runOnUpdateThread
	private List <Runnable> mRunOnUpdateThread;

	// Alpha&FPS calculation
	private double mLastUpdate = 0;
	private FPSLogger mFpsLogger;

	// Pause
	private boolean mPause;

	// ===========================================================
	// Constructors
	// ===========================================================	
	public OpenGLES10Renderer(SimpleGLEngineActivity context) {
		this.context = context;

		this.mScene = null;
		this.mReady = false;
	}	

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public Scene getScene() {
		return mScene;
	}
	public void setScene(Scene mScene) {
		this.mScene = mScene;
	}
	public TextureManager getTextureManager() {
		return mTextureManager;
	}
	public FontManager getFontManager() {
		return mFontManager;
	}
	public AudioManager getAudioManager() {
		return mAudioManager;
	}

	public int getFPS() {
		return mFpsLogger.getFPS();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		gl.glEnable(GL10.GL_TEXTURE_2D);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glDisable(GL10.GL_DEPTH_TEST);

		gl.glHint(GL10.GL_TEXTURE_2D, GL10.GL_FASTEST);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}	
	@Override
	public void onDrawFrame(GL10 gl) {		
		double alpha = System.currentTimeMillis() - mLastUpdate;

		// Clear screen
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();


		// Draw phase
		mScene.onDraw(gl);

		// Update phase
		if(mScene.getMenu() != null && mScene.getMenu().isShow() && mScene.getMenu().isAutoPause())
			mPause = true;
		if(mScene.getMenu() != null && !mScene.getMenu().isShow() && mScene.getMenu().isAutoPause())
			mPause = false;
		if(!mPause)
			mScene.onUpdate((float) (alpha/1000.0f));


		// RunOnUpdateThread phase
		if(mRunOnUpdateThread.size() > 0) {
			for(Runnable runnable : mRunOnUpdateThread) {
				runnable.run();
			}
			mRunOnUpdateThread.clear();
		}

		// Log fps
		mFpsLogger.log();

		mLastUpdate = System.currentTimeMillis();
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Set the new viewport, thanks to resolution
		gl.glViewport(0, 0, width, height);
		this.width = width;
		this.height = height;
		ratio = (float) width / height;

		// Set Camera
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		GLU.gluOrtho2D(gl, 0 , width, height, 0);

		// Go to Model View Mode
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		// Init engine
		if(!mReady && width > height)
			init(gl);
		else if(mReady && width > height)
			reinit(gl);

	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void init(GL10 gl) {
		Scene scene;

		Log.e("init", "init");

		// Init vars
		GLGraphics.currentGLContext = gl;
		this.mTextureManager = new TextureManager(context);
		this.mFontManager = new FontManager(context);
		this.mAudioManager = new AudioManager(context);

		this.mRunOnUpdateThread = new ArrayList<Runnable>();

		this.mPause = false;		

		// Loading phase
		context.onLoadRessources();
		mScene = context.onLoadScene();        
		context.onLoadComplete();

		mScene.onLoadSurface(gl);


		// Init update timer
		mLastUpdate = System.currentTimeMillis();
		mFpsLogger = new FPSLogger();

		mReady = true;
	}
	public void reinit(GL10 gl) {
		// Vars
		ArrayList<TextureRegion> textureRegionsBack = mTextureManager.getTextureRegions();
		ArrayList<TextureRegion> textureRegionsNew = null;
		
		// Init vars
		GLGraphics.currentGLContext = gl;
		this.mTextureManager = new TextureManager(context);
		this.mFontManager = new FontManager(context);
		this.mAudioManager = new AudioManager(context);

		// Loading phase
		context.onLoadRessources();
		textureRegionsNew = mTextureManager.getTextureRegions();
		mScene.onLoadSurface(gl);
		mScene.onReloadTextureRegion(textureRegionsBack, textureRegionsNew);
		
		

		// Init update timer
		mLastUpdate = System.currentTimeMillis();
	}

	public void runOnUpdateThread(Runnable runnable) {
		this.mRunOnUpdateThread.add(runnable);
	}

	public boolean onTouch(MotionEvent event) {
		if(mScene != null)
			return mScene.onTouch(event);
		else
			return false;
	}

	public void pause() {
		mPause = true;
	}

	public void unPause() {
		mPause = false;
		mLastUpdate = System.currentTimeMillis();
	}



}

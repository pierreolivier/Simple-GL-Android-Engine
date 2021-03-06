package com.simpleglengine;

import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.managers.AudioManager;
import com.simpleglengine.managers.FontManager;
import com.simpleglengine.managers.TextureManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public abstract class SimpleGLEngineActivity extends Activity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private OpenGLES10SurfaceView mGLView;
	private OpenGLES10Renderer mRenderer;

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   

		// Full screen mode
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Create Renderer
		mGLView = new OpenGLES10SurfaceView(this);
		setContentView(mGLView);        
		mRenderer = mGLView.getRenderer();
		
		// Loading app
		//View init fonction in OpenGLES10Renderer

	}    

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		

	}	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}
	@Override
	public void onConfigurationChanged(final Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public abstract void onLoadRessources();
	public abstract Scene onLoadScene();    
	public abstract void onLoadComplete();

	// ===========================================================
	// Methods from engine
	// ===========================================================
	public TextureManager getTextureManager() {
		return mRenderer.getTextureManager();
	}
	public FontManager getFontManager() {
		return mRenderer.getFontManager();
	}
	public int getFPS() {
		return mRenderer.getFPS();
	}	
	public void runOnUpdateThread(Runnable runnable) {
		mRenderer.runOnUpdateThread(runnable);
	}
	public AudioManager getAudioManager() {
		return mRenderer.getAudioManager();
	}
	
	public void pause() {
		mRenderer.pause();
	}
	
	public void unPause() {
		mRenderer.unPause();
	}
}
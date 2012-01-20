package com.simpleglengine;

import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.managers.TextureManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public abstract class SimpleGLEngineActivity extends Activity {
    
	private OpenGLES10SurfaceView mGLView;
	private OpenGLES10Renderer mRenderer;
	
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
    
    public TextureManager getTextureManager() {
    	return mRenderer.getTextureManager();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
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
    
    public abstract void onLoadRessources();
    public abstract Scene onLoadScene();    
    public abstract void onLoadComplete();
}
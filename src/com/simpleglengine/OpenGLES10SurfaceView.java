package com.simpleglengine;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class OpenGLES10SurfaceView extends GLSurfaceView {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private OpenGLES10Renderer mRenderer;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public OpenGLES10SurfaceView(SimpleGLEngineActivity context){
        super(context);
        
        mRenderer = new OpenGLES10Renderer(context);
        setRenderer(mRenderer);
    }
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public OpenGLES10Renderer getRenderer() {
		return mRenderer;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
}

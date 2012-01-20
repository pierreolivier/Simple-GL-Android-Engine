package com.simpleglengine;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class OpenGLES10SurfaceView extends GLSurfaceView {
	private OpenGLES10Renderer mRenderer;
	public OpenGLES10SurfaceView(SimpleGLEngineActivity context){
        super(context);
        
        mRenderer = new OpenGLES10Renderer(context);
        setRenderer(mRenderer);
    }
	
	public OpenGLES10Renderer getRenderer() {
		return mRenderer;
	}
	
}

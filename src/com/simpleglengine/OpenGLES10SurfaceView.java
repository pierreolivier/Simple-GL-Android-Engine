package com.simpleglengine;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class OpenGLES10SurfaceView extends GLSurfaceView {
	public OpenGLES10SurfaceView(Context context){
        super(context);
        
        // Create an OpenGL ES 2.0 context.
        //setEGLContextClientVersion(2);
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new OpenGLES10Renderer(context));
    }
}

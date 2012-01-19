package com.simpleglengine;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class OpenGLES10SurfaceView extends GLSurfaceView {
	public OpenGLES10SurfaceView(Context context){
        super(context);
        
        //setRenderer(new OpenGLES10Renderer(context));
        setRenderer(new OpenGLES10Renderer(context));
    }
}

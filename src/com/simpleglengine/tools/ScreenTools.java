package com.simpleglengine.tools;

import com.simpleglengine.OpenGLES10Renderer;

public class ScreenTools {
	public static int getWidth() {
		return OpenGLES10Renderer.width;
	}
	public static int getHeight() {
		return OpenGLES10Renderer.height;
	}
	public static float x(float x) {
		float a = 2.0f * OpenGLES10Renderer.ratio / OpenGLES10Renderer.width;
		return x;
		//return a * x - OpenGLES10Renderer.ratio;
	}
	public static float y(float y) {
		float a =  2.0f / OpenGLES10Renderer.height;
		return y;
		//return a * y - 1.0f;
	}
	public static float dX(float x) {
		return x(x);
		//return x(x)+OpenGLES10Renderer.ratio;
	}
	public static float dY(float y) {
		return y(y);
		//return y(y)+1;
	}
}

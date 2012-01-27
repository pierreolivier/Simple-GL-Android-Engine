package com.gl.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class HelloOpenGLES10Renderer implements Renderer {

	private int id;
	private FPSLogger fps = new FPSLogger();
	public void onSurfaceCreated(GL10 gl10, EGLConfig config) {
		GL11 gl = (GL11) gl10;
		// Set the background frame color
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);


		gl.glEnable(GL11.GL_TEXTURE_2D);
		gl.glEnable(GL11.GL_BLEND);
		gl.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

		initShapes(gl);
	}

	public void onDrawFrame(GL10 gl10) {
		GL11 gl = (GL11) gl10;
		// Redraw background color
		gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		gl.glColor4f(0.23671875f, 0.76953125f, 0.22265625f, 1.0f);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, id);
		gl.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

		for(int i=0;i<300;i++) {
			gl.glTranslatef(0.04f, 0.0f, 0f);
			gl.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
		}
		fps.log();
		
		

		//gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
		//gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
	}

	public void onSurfaceChanged(GL10 gl10, int width, int height) {
		GL11 gl = (GL11) gl10;
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width / height;
		gl.glMatrixMode(GL11.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7); 
		//GLU.gluOrtho2D(gl, width , 0, height, 0);

		gl.glMatrixMode(GL11.GL_MODELVIEW);
		gl.glLoadIdentity();

		GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);


	}

	private void initShapes(GL11 gl){
		FloatBuffer triangleVB;
		
		float triangleCoords[] = {
				// X, Y, Z
				-0.5f, -0.25f, 0,
				0.5f, -0.25f, 0,
				0.0f,  0.559016994f, 0
		}; 

		// initialize vertex Buffer for triangle  
		ByteBuffer vbb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 4 bytes per float)
				triangleCoords.length * 4); 
		vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
		triangleVB = vbb.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
		triangleVB.put(triangleCoords);    // add the coordinates to the FloatBuffer
		triangleVB.position(0);            // set the buffer to read the first coordinate

		int [] ids = new int[1];
		gl.glGenBuffers(1, ids,0);
		id = ids[0];
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, id);
		gl.glBufferData(GL11.GL_ARRAY_BUFFER, triangleCoords.length*4 , triangleVB, GL11.GL_DYNAMIC_DRAW);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);



	}
}

package com.simpleglengine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

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

	private FloatBuffer triangleVB;
	private FloatBuffer squareVB;

	private float ratio = 1.77916667f;
	private int width = 854;
	private int height = 480;
	/*
	float color[] = {
			0.63671875f, 0.76953125f, 0.22265625f, 0.0f	
	};*/

	private Bitmap bitmap;
	private int[] textures = new int[2];

	private FloatBuffer mTextureBuffer;



	public OpenGLES10Renderer(Context context) {
		this.context = context;
	}

	private void initShapes(GL10 gl){

		float triangleCoords[] = {
				// X, Y, Z
				-0.5f, -0.25f, 0,
				0.5f, -0.25f, 0,
				0.0f,  0.559016994f, 0
		}; 

		ByteBuffer vbb = ByteBuffer.allocateDirect(triangleCoords.length * 4); 
		vbb.order(ByteOrder.nativeOrder());
		triangleVB = vbb.asFloatBuffer();
		triangleVB.put(triangleCoords);
		triangleVB.position(0);
		
		
		float square[] = {
				x(64f), y(0f), 0,
				x(64f), y(64f), 0,				
				x(0f), y(0f), 0,			
				x(0f), y(64f), 0				
				
		};
		
		/*
		float square[] = {
				dX(32f), -dY(32f), 0,
				dX(32f), dY(32f), 0,				
				-dX(32f), -dY(32f), 0,			
				-dX(32f), dY(32f), 0				
				
		};
		*/
		ByteBuffer sbb = ByteBuffer.allocateDirect(square.length * 4);
		sbb.order(ByteOrder.nativeOrder());
		squareVB = sbb.asFloatBuffer();
		squareVB.put(square);
		squareVB.position(0);

		Bitmap bitmap = BitmapTools.createTransparentBitmapFromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.heros), Color.rgb(255, 0, 255));
		Bitmap bitmap2 = BitmapTools.createTransparentBitmapFromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.heros2), Color.rgb(255, 0, 255));
		float textureCoordinates[] = {
				0.0f, 0.0f,
				0.0f, 1.0f,
				1.0f, 0.0f,
				1.0f, 1.0f 
		};
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		mTextureBuffer = byteBuf.asFloatBuffer();
		mTextureBuffer.put(textureCoordinates);
		mTextureBuffer.position(0);

		gl.glGenTextures(2, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[1]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap2, 0);
		
		
		bitmap.recycle();
		bitmap2.recycle();
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

		long time = SystemClock.uptimeMillis() % 4000L;
		
		gl.glColor4f(1f, 1f, 1f, 1f);
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[1]);
		float angle = 0.090f * ((int) time);
		
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, squareVB);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
		gl.glTranslatef(dX(-854/2+32+100), dY(-480/2+32+200), 0); //Offset
		gl.glRotatef(2*angle, 0.0f, 0.0f, 1.0f); //Rotation en degre ?
		gl.glTranslatef(dX(854/2-32), dY(480/2-32), 0); //Millieu + centre de rotation
		
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		/*
		gl.glLoadIdentity();
		float angle = 0.090f * ((int) time);
		gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
		gl.glColor4f(0.23671875f, 0.76953125f, 0.52265625f, 1.0f);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleVB);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);*/

	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		this.width = width;
		this.height = height;
		ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
		gl.glLoadIdentity();                        // reset the matrix to its default state
		//gl.glFrustumf(ratio, -ratio, 1, -1, 3, 7);  // apply the projection matrix
		gl.glFrustumf(ratio, -ratio, 1, -1, 3, 7);  // apply the projection matrix

		initShapes(gl);


		GLU.gluLookAt(gl, 0, 0, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

	}

	public float x(float x) {
		float a = 2.0f * ratio / width;
		return a * x - ratio;
	}
	public float y(float y) {
		float a =  2.0f / height;
		return a * y - 1.0f;
	}
	public float dX(float x) {
		return x(x)+ratio;
	}
	public float dY(float y) {
		return y(y)+1;
	}
	

}

package com.simpleglengine.entity.text;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.entity.Entity;
import com.simpleglengine.entity.Shape;

public class Text extends Shape implements Entity{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Font mFont;
	private String mText;
	
	protected PhysicsHandler mPhysicsHandler = null;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public Text(Font font, String text, int x, int y) {
		super(x,y);
		
		this.mFont = font;
		this.mText = text;
		
		
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public PhysicsHandler getPhysicsHandler() {
		return mPhysicsHandler;
	}
	public void setPhysicsHandler(PhysicsHandler mPhysicsHandler) {
		this.mPhysicsHandler = mPhysicsHandler;
	}
	@Override
	public void setScale(float scale) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onLoadSurface(GL10 gl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDraw(GL10 gl) {
		gl.glLoadIdentity();
		
		mFont.draw(gl, mText, (int) mX, (int) mY);
	}

	@Override
	public void onUpdate(float alpha) {
		if(this.mPhysicsHandler != null)
			this.mPhysicsHandler.onUpdate(alpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

}

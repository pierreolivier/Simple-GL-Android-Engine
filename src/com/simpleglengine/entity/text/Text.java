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
	public String getText() {
		return mText;
	}

	public void setText(String mText) {
		this.mText = mText;
	}
	
	public int getWidth() {
		return mFont.getWidth(mText);
	}
	public int getHeight() {
		return mFont.getHeight();
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
		
		mFont.draw(gl, mText, (int) mX, 480-(int) mY-getHeight());
	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

}

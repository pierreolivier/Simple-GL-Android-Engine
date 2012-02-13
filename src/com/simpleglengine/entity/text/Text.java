package com.simpleglengine.entity.text;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;

public class Text extends Shape implements IEntity{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected Font mFont;
	protected String mText;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public Text(Font font, String text, int x, int y) {
		super(x,y,font.getWidth(text),font.getHeight());
		
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
	
	
	public float getWidth() {
		return mFont.getWidth(mText);
	}
	public float getHeight() {
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
	public void setColor(float r, float g, float b, float a) {
		super.setColor(r, g, b, a);
		
		this.mFont.setColor(r, g, b);
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
		
		mFont.draw(gl, mText, (int) mX, 480-(int) mY-(int) getHeight() );
	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

}

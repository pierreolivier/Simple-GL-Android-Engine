package com.simpleglengine.engine.opengl;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.simpleglengine.tools.TextureFontTools;

public class Font {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private TextureFontTools mTextureFontTools;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public Font(TextureFontTools textureFontTools) {
		super();
		
		this.mTextureFontTools = textureFontTools;
		this.setColor(0.0f, 0.0f, 0.0f);
		
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public void setSize(float size) {
		this.mTextureFontTools.SetScale(size);
	}
	public void setColor(float r, float g, float b) {
		this.mTextureFontTools.SetPolyColor(r, g, b);
	}
	public int getWidth(String text) {
		return mTextureFontTools.GetTextLength(text);
	}
	public int getHeight() {
		return mTextureFontTools.GetTextHeight();
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void draw(GL10 gl,String text, int x, int y) {
		this.mTextureFontTools.PrintAt(gl, text, x, y);
	}

}

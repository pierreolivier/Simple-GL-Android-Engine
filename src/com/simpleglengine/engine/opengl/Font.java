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
	
	public Font(GL10 gl, TextureFontTools textureFontTools) {
		super();
		
		this.mTextureFontTools = textureFontTools;
		
		
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

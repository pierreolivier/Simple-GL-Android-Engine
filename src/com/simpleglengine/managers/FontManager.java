package com.simpleglengine.managers;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.GLGraphics;
import com.simpleglengine.tools.TextureFontTools;

public class FontManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Context mContext;

	// ===========================================================
	// Constructors
	// ===========================================================
	public FontManager(Context context) {
		super();

		this.mContext = context;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public Font createFont(String assetsBff) throws IOException {
		TextureFontTools textureFontTools = new TextureFontTools(mContext, GLGraphics.currentGLContext);
		textureFontTools.LoadFont(assetsBff, GLGraphics.currentGLContext);
		
		return new Font(textureFontTools);
	}
}

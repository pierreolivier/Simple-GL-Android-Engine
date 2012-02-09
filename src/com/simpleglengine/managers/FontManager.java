package com.simpleglengine.managers;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.TextureFontTools;

public class FontManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Context mContext;
	private GL10 mGL;

	// ===========================================================
	// Constructors
	// ===========================================================
	public FontManager(Context context, GL10 gl) {
		super();

		this.mContext = context;
		this.mGL = mGL;
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
		TextureFontTools textureFontTools = new TextureFontTools(mContext, mGL);
		textureFontTools.LoadFont(assetsBff, mGL);
		
		return new Font(mGL, textureFontTools);
	}
}

package com.simpleglengine.entity.scene.menu;

import java.util.ArrayList;
import java.util.List;

import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.text.Text;

public class Menu extends Scene {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private List <Text> mItems;
	private boolean mAutoPause;
	private boolean mShow;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public Menu() {
		super();
		
		this.mAutoPause = false;
		this.mShow = false;
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public boolean isAutoPause() {
		return mAutoPause;
	}
	public void setAutoPause(boolean mAutoPause) {
		this.mAutoPause = mAutoPause;
	}
	public boolean isShow() {
		return mShow;
	}	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================



	// ===========================================================
	// Methods
	// ===========================================================
	public void show() {
		this.mShow = true;
	}	
	public void hide() {
		this.mShow = false;
	}
}

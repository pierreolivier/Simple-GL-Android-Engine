package com.simpleglengine.entity;

import javax.microedition.khronos.opengles.GL10;

public interface Entity {
	public void setScale(float scale);
	public float getScale();
	
	public void onLoadSurface(GL10 gl);
	
	public void onDraw(GL10 gl);
	public void onUpdate(float alpha);
}

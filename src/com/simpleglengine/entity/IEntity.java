package com.simpleglengine.entity;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

public interface IEntity {
	public void setScale(float scale);
	public float getScale();
	public void setPause(boolean pause);
	public boolean isPaused();
	
	public void onLoadSurface(GL10 gl);
	
	public void onDraw(GL10 gl);
	public void onUpdate(float alpha);
	public abstract void onManagedUpdate(float alpha); 
	
	public boolean onTouch(final MotionEvent event);
}

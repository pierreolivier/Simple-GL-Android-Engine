package com.simpleglengine.shapes;

import javax.microedition.khronos.opengles.GL10;

public interface Entity {
	public void onDraw(GL10 gl);
	public void onUpdate(float alpha);
}

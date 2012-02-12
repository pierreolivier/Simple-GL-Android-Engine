package com.simpleglengine.engine.handler.modifier;

import com.simpleglengine.entity.Shape;

public interface IEntityModifierListener {
	public void onModifierStarted(Shape shape);
	public void onModifierFinished(Shape shape);
}

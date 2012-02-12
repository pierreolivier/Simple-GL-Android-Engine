package com.simpleglengine.engine.handler.modifier;

import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;

public interface IEntityModifier {
	public boolean isFinished();
	
	public void onModifierStarted(Shape shape);
	public void onModifierFinished(Shape shape);
	
	public void onUpdate(Shape shape, float alpha);
}

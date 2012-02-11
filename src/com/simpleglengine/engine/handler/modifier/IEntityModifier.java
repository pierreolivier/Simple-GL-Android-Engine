package com.simpleglengine.engine.handler.modifier;

public interface IEntityModifier {
	public void onModifierStarted();
	public void onModifierFinished();
	
	public void onUpdate(float alpha);
}

package com.simpleglengine.engine.handler.modifier.ease;

public interface IEaseFunction {
	public float getPercentage(float secondsElapsed, float duration);
}

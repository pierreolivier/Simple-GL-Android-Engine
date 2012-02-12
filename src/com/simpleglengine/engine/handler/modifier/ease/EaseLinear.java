package com.simpleglengine.engine.handler.modifier.ease;

public class EaseLinear implements IEaseFunction {

	@Override
	public float getPercentage(float secondsElapsed, float duration) {
		return function(secondsElapsed/duration);
	}
	
	public float function(float x) {
		return x;
	}
}

package com.simpleglengine.engine.handler.modifier.ease;

import com.simpleglengine.engine.handler.modifier.IEntityModifier;
import com.simpleglengine.entity.Shape;

public class EaseBounceOut implements IEaseFunction {

	@Override
	public float getPercentage(float secondsElapsed, float duration) {
		return function(secondsElapsed/duration);
	}

	public float function(float x) {
		if(x < (1f / 2.75f)) {
			return 7.5625f * x * x;
		} else if(x < (2f / 2.75f)) {
			float t = x - (1.5f / 2.75f);
			return 7.5625f * t * t + 0.75f;
		} else if(x < (2.5f / 2.75f)) {
			float t = x - (2.25f / 2.75f);
			return 7.5625f * t * t + 0.9375f;
		} else {
			float t = x - (2.625f / 2.75f);
			return 7.5625f * t * t + 0.984375f;
		}
	}



}

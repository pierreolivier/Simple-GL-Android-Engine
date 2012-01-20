package com.simpleglengine.entity.scene;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.entity.Entity;

public class Scene implements Entity {
	private List <Entity> mChildren;
	private float mScale;


	public Scene() {
		super();

		this.mChildren = new ArrayList<Entity>();
		
		this.mScale = 1;
	}

	@Override
	public void onDraw(GL10 gl) {
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.onDraw(gl);
			}
		}
	}

	@Override
	public void onUpdate(float alpha) {
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.onUpdate(alpha);
			}
		}
	}

	public void attachChild(Entity pEntity) {
		synchronized (mChildren) {
			this.mChildren.add(pEntity);
		}
	}
	public void detachChild(Entity pEntity) {
		synchronized (mChildren) {
			this.mChildren.remove(pEntity);
		}
	}

	@Override
	public void setScale(float scale) {
		this.mScale = scale;
		
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.setScale(scale);
			}
		}
	}

	@Override
	public void resetScale() {
		this.mScale = 1;
		
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.resetScale();
			}
		}
	}

	@Override
	public float getScale() {
		return mScale;
	}

}

package com.simpleglengine.entity.scene;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.entity.Entity;

public class Scene implements Entity {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private List <Entity> mChildren;
	private Entity mBackground;
	
	private float mScale;

	// ===========================================================
	// Constructors
	// ===========================================================s
	public Scene() {
		super();

		this.mChildren = new ArrayList<Entity>();
		this.mBackground = null;
		
		this.mScale = 1;
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public void setScale(float scale) {
		this.mScale = scale;
		
		if(this.mBackground != null)
			this.mBackground.setScale(scale);
		
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.setScale(scale);
			}
		}
	}


	@Override
	public float getScale() {
		return mScale;
	}
	
	public void setBackground(Entity pEntity) {
		this.mBackground = pEntity;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onLoadSurface(GL10 gl) {
		if(this.mBackground != null)
			this.mBackground.onLoadSurface(gl);
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.onLoadSurface(gl);
			}
		}
	}
	
	@Override
	public void onDraw(GL10 gl) {
		if(this.mBackground != null)
			this.mBackground.onDraw(gl);
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.onDraw(gl);
			}
		}
	}

	@Override
	public void onUpdate(float alpha) {
		if(this.mBackground != null)
			this.mBackground.onUpdate(alpha);
		
		synchronized (mChildren) {
			for(Entity pEntity : this.mChildren) {
				pEntity.onUpdate(alpha);
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
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

	

	

}

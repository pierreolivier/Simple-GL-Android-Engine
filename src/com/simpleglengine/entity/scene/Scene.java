package com.simpleglengine.entity.scene;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;
import android.view.MotionEvent;

import com.simpleglengine.entity.Entity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.text.Text;

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

		for(Entity pEntity : this.mChildren) {
			pEntity.onLoadSurface(gl);
		}

	}

	@Override
	public void onDraw(GL10 gl) {
		if(this.mBackground != null)
			this.mBackground.onDraw(gl);

		for(Entity pEntity : this.mChildren) {
			pEntity.onDraw(gl);
		}

	}

	@Override
	public void onUpdate(float alpha) {
		if(this.mBackground != null)
			this.mBackground.onUpdate(alpha);


		for(Entity pEntity : this.mChildren) {
			pEntity.onUpdate(alpha);
		}

	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		
		for(Entity pEntity : this.mChildren) {
			if(pEntity instanceof Sprite) {
				Sprite sprite = (Sprite) pEntity;
				float xTouch = event.getX(), yTouch = event.getY();
				
				if(xTouch >= sprite.getX() && xTouch <= sprite.getX()+sprite.getScaledWidth() &&
						yTouch >= sprite.getY() && yTouch <= sprite.getY()+sprite.getScaledHeight() ) {
					return sprite.onTouch(event);
				}
			} else if(pEntity instanceof Text) {				
				Text text = (Text) pEntity;
				float xTouch = event.getX(), yTouch = event.getY();
				
				if(xTouch >= text.getX() && xTouch <= text.getX()+text.getWidth() &&
						yTouch >= text.getY() && yTouch <= text.getY()+text.getHeight() ) {
					return text.onTouch(event);
				}
			}
		}
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void attachChild(Entity pEntity) {

		this.mChildren.add(pEntity);

	}
	public void detachChild(Entity pEntity) {
		this.mChildren.remove(pEntity);
	}







}

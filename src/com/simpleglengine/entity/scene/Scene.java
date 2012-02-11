package com.simpleglengine.entity.scene;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;
import android.view.MotionEvent;

import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.text.Text;

public class Scene implements IEntity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private List <IEntity> mChildren;
	private IEntity mBackground;

	private float mScale;

	// ===========================================================
	// Constructors
	// ===========================================================s
	public Scene() {
		super();

		this.mChildren = new ArrayList<IEntity>();
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
			for(IEntity pEntity : this.mChildren) {
				pEntity.setScale(scale);
			}
		}
	}


	@Override
	public float getScale() {
		return mScale;
	}

	public void setBackground(IEntity pEntity) {
		this.mBackground = pEntity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onLoadSurface(GL10 gl) {
		if(this.mBackground != null)
			this.mBackground.onLoadSurface(gl);

		for(IEntity pEntity : this.mChildren) {
			pEntity.onLoadSurface(gl);
		}

	}

	@Override
	public void onDraw(GL10 gl) {
		if(this.mBackground != null)
			this.mBackground.onDraw(gl);

		for(IEntity pEntity : this.mChildren) {
			pEntity.onDraw(gl);
		}

	}

	@Override
	public void onUpdate(float alpha) {
		if(this.mBackground != null)
			this.mBackground.onUpdate(alpha);


		for(IEntity pEntity : this.mChildren) {
			pEntity.onUpdate(alpha);
		}

	}

	@Override
	public boolean onTouch(MotionEvent event) {

		for(IEntity pEntity : this.mChildren) {
			if(pEntity instanceof Shape) {
				Shape shape = (Shape) pEntity;
				float xTouch = event.getX(), yTouch = event.getY();

				if(xTouch >= shape.getX() && xTouch <= shape.getX()+shape.getScaledWidth() &&
						yTouch >= shape.getY() && yTouch <= shape.getY()+shape.getScaledHeight() ) {
					return shape.onTouch(event);
				}
			}
		}
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void attachChild(IEntity pEntity) {

		this.mChildren.add(pEntity);

	}
	public void detachChild(IEntity pEntity) {
		this.mChildren.remove(pEntity);
	}







}

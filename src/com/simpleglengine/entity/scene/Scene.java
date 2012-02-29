package com.simpleglengine.entity.scene;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.util.Log;
import android.view.MotionEvent;

import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.entity.scene.menu.Menu;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.text.Text;
import com.simpleglengine.tools.GLGraphics;
import com.simpleglengine.tools.ScreenTools;

public class Scene implements IEntity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected List <IEntity> mChildren;
	protected IEntity mBackground;

	protected float mScale;

	protected Menu mMenu;

	protected boolean mPause;

	// ===========================================================
	// Constructors
	// ===========================================================s
	public Scene() {
		super();

		this.mChildren = new ArrayList<IEntity>();
		this.mBackground = null;

		this.mScale = 1;

		this.mMenu = null;

		this.mPause = false;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public void setScale(float scale) {
		this.mScale = scale;

		if(this.mBackground != null)
			this.mBackground.setScale(scale);


		for(IEntity pEntity : this.mChildren) {
			pEntity.setScale(scale);
		}

	}
	@Override
	public float getScale() {
		return mScale;
	}


	public void setBackground(IEntity pEntity) {
		this.mBackground = pEntity;
	}


	public Menu getMenu() {
		return mMenu;
	}

	public void setMenu(Menu mMenu) {
		this.mMenu = mMenu;
	}

	@Override
	public void setPause(boolean pause) {
		this.mPause = pause;
	}

	@Override
	public boolean isPaused() {
		return mPause;
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

		if(this.mMenu != null)
			this.mMenu.onLoadSurface(gl);

	}

	@Override
	public void onDraw(GL10 gl) {
		if(this.mBackground != null)
			this.mBackground.onDraw(gl);

		for(IEntity pEntity : this.mChildren) {
			pEntity.onDraw(gl);
		}

		if(this.mMenu != null && this.mMenu.isShow())
			this.mMenu.onDraw(gl);

	}

	@Override
	public void onUpdate(float alpha) {
		if(!mPause) {
			if(this.mBackground != null)
				this.mBackground.onUpdate(alpha);

			this.onManagedUpdate(alpha);

			for(IEntity pEntity : this.mChildren) {
				pEntity.onUpdate(alpha);
			}

			if(this.mMenu != null && this.mMenu.isShow())
				this.mMenu.onUpdate(alpha);			

		}
	}

	@Override
	public void onManagedUpdate(float alpha) {

	}

	@Override
	public boolean onTouch(MotionEvent event) {

		if(this.mMenu != null && this.mMenu.isShow())
			return this.mMenu.onTouch(event);
		else {
			boolean reponse = false;
			for(IEntity pEntity : this.mChildren) {
				if(pEntity instanceof Shape) {
					Shape shape = (Shape) pEntity;
					float xTouch = event.getX(), yTouch = event.getY();

					if(xTouch >= shape.getX() && xTouch <= shape.getX()+shape.getScaledWidth() &&
							yTouch >= shape.getY() && yTouch <= shape.getY()+shape.getScaledHeight() ) {
						reponse = shape.onTouch(event);
						//return shape.onTouch(event);
					}
					if(reponse)
						return true;
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
		pEntity.setScale(mScale);

	}
	public void detachChild(IEntity pEntity) {
		this.mChildren.remove(pEntity);
		if(pEntity instanceof Shape)
			((Shape) pEntity).getBuffer().unloadBuffer((GL11) GLGraphics.currentGLContext);
	}

	public void onReloadTextureRegion(ArrayList<TextureRegion> textureRegionsBack,	ArrayList<TextureRegion> textureRegionsNew) {

		for(IEntity pEntity : this.mChildren) {
			if(pEntity instanceof Sprite) {
				Sprite sprite = (Sprite) pEntity;
				int index = getIndex(textureRegionsBack, sprite.getTextureRegion());
				if(index != -1)
					sprite.setTextureRegion(textureRegionsNew.get(index));
			}

		}
	}

	private int getIndex(ArrayList<TextureRegion> textureRegions, TextureRegion textureRegion) {
		for(int i = 0; i < textureRegions.size(); i++) {
			if(textureRegions.get(i) == textureRegion)
				return i;
		}
		return -1;
	}











}

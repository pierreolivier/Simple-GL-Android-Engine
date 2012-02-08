package com.simpleglengine.entity.scene.background;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.Entity;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.sprite.SpriteBatch;
import com.simpleglengine.tools.ScreenTools;

public class AutoParallaxBackground extends ColorBackground implements Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Texture mTexture;
	private int mYAutoParallaxBackground;
	private float mVelocityX;
	
	private Sprite mSprite = null, mSpriteNext;
	
	private List <Sprite> mSprites = null, mSpritesNexts = null;
	private Sprite mLastSprite = null, mLastSpriteNext = null;
	
	private float mScale = 1.0f;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AutoParallaxBackground(Texture texture, int y, float velocityX) {
		super(0.0f, 0.0f, 0.0f, 1.0f);
		
		this.mTexture = texture;
		this.mYAutoParallaxBackground = y;
		this.mVelocityX = velocityX;
		
		load(texture, y, velocityX);
		/*
		if(texture.getWidth() <= ScreenTools.getWidth()) {
			int xNext = ScreenTools.getWidth();

			mSprites = new ArrayList<Sprite>();
			mSpritesNexts = new ArrayList<Sprite>();

			for(int i = 0; i <= ScreenTools.getWidth();i+=texture.getWidth()) {
				Sprite spriteTemp = new Sprite(texture, i, y);
				PhysicsHandler physicsHandler = new PhysicsHandler(spriteTemp);
				physicsHandler.setVelocityX(velocityX);
				spriteTemp.setPhysicsHandler(physicsHandler);				

				mSprites.add(spriteTemp);

				xNext = (int) (spriteTemp.getX()+texture.getWidth());
				mLastSprite = spriteTemp;
			}

			for(int i = xNext; i <= 2*ScreenTools.getWidth();i+=texture.getWidth()) {
				Sprite spriteTemp = new Sprite(texture, i, y);
				PhysicsHandler physicsHandler = new PhysicsHandler(spriteTemp);
				physicsHandler.setVelocityX(velocityX);
				spriteTemp.setPhysicsHandler(physicsHandler);				

				mSpritesNexts.add(spriteTemp);
				
				mLastSpriteNext = spriteTemp;
			}
		} else {
			mSprite = new Sprite(texture, 0, y);
			PhysicsHandler physicsHandler = new PhysicsHandler(mSprite);
			physicsHandler.setVelocityX(velocityX);
			mSprite.setPhysicsHandler(physicsHandler);

			mSpriteNext = new Sprite(texture, 0 + texture.getWidth(), y);
			PhysicsHandler physicsHandlerNext = new PhysicsHandler(mSpriteNext);
			physicsHandlerNext.setVelocityX(velocityX);
			mSpriteNext.setPhysicsHandler(physicsHandlerNext);
		}
		*/
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public void setScale(float scale) {
		mScale = scale;
		
		load(mTexture, mYAutoParallaxBackground, mVelocityX);
		
		if(mSprites != null && mSpritesNexts != null) {
			for(Entity pEntity : this.mSprites) {
				pEntity.setScale(mScale);
			}
			for(Entity pEntity : this.mSpritesNexts) {
				pEntity.setScale(mScale);
			}
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.setScale(mScale);
			mSpriteNext.setScale(mScale);
		}
		
		//load(mTexture, mYAutoParallaxBackground, mVelocityX);
	}

	@Override
	public float getScale() {
		// TODO Auto-generated method stub
		return mScale;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onLoadSurface(GL10 gl) {
		super.onLoadSurface(gl);
		
		if(mSprites != null && mSpritesNexts != null) {
			for(Entity pEntity : this.mSprites) {
				pEntity.onLoadSurface(gl);
			}
			for(Entity pEntity : this.mSpritesNexts) {
				pEntity.onLoadSurface(gl);
			}
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.onLoadSurface(gl);
			mSpriteNext.onLoadSurface(gl);
		}
	}

	@Override
	public void onDraw(GL10 gl) {
		super.onDraw(gl);
		
		//gl.glDisable(GL10.GL_BLEND);
		
		if(mSprites != null && mSpritesNexts != null) {
			for(Entity pEntity : this.mSprites) {
				pEntity.onDraw(gl);
			}
			for(Entity pEntity : this.mSpritesNexts) {
				pEntity.onDraw(gl);
			}
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.onDraw(gl);
			mSpriteNext.onDraw(gl);
		}
		
		//gl.glEnable(GL10.GL_BLEND);
	}


	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);
		
		if(mSprites != null && mSpritesNexts != null) {
			for(Entity pEntity : this.mSprites) {
				pEntity.onUpdate(alpha);
			}
			for(Entity pEntity : this.mSpritesNexts) {
				pEntity.onUpdate(alpha);
			}
			
			if(mLastSprite.getX()+mLastSprite.getScaledWidth() < 0) {
				float xTemp = mLastSpriteNext.getX()+mLastSpriteNext.getScaledWidth();
				for(Sprite sprite : mSprites) {
					sprite.setX(xTemp);
					xTemp += mLastSpriteNext.getScaledWidth();
				}
			} else if(mLastSpriteNext.getX()+mLastSpriteNext.getScaledWidth() < 0) {
				float xTemp = mLastSprite.getX()+mLastSprite.getScaledWidth();
				for(Sprite sprite : mSpritesNexts) {
					sprite.setX(xTemp);
					xTemp += mLastSprite.getScaledWidth();
				}
			} 
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.onUpdate(alpha);
			mSpriteNext.onUpdate(alpha);
			
			if(mSprite.getX()+mSprite.getScaledWidth() < 0) {
				mSprite.setX(mSpriteNext.getX()+mSpriteNext.getScaledWidth());
			} else if(mSpriteNext.getX()+mSpriteNext.getScaledWidth() < 0) {
				mSpriteNext.setX(mSprite.getX()+mSprite.getScaledWidth());
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void load(Texture texture, int y, float velocityX) {
		if(mScale*texture.getWidth() <= ScreenTools.getWidth()) {
			int xNext = ScreenTools.getWidth();

			mSprites = new ArrayList<Sprite>();
			mSpritesNexts = new ArrayList<Sprite>();

			for(int i = 0; i <= ScreenTools.getWidth();i+=mScale*texture.getWidth()) {
				Sprite spriteTemp = new Sprite(texture, i, y);
				PhysicsHandler physicsHandler = new PhysicsHandler(spriteTemp);
				physicsHandler.setVelocityX(velocityX);
				spriteTemp.setPhysicsHandler(physicsHandler);				

				mSprites.add(spriteTemp);

				xNext = (int) (spriteTemp.getX()+mScale*texture.getWidth());
				mLastSprite = spriteTemp;
			}

			for(int i = xNext; i <= 2*ScreenTools.getWidth();i+=mScale*texture.getWidth()) {
				Sprite spriteTemp = new Sprite(texture, i, y);
				PhysicsHandler physicsHandler = new PhysicsHandler(spriteTemp);
				physicsHandler.setVelocityX(velocityX);
				spriteTemp.setPhysicsHandler(physicsHandler);				

				mSpritesNexts.add(spriteTemp);
				
				mLastSpriteNext = spriteTemp;
			}
		} else {
			mSprite = new Sprite(texture, 0, y);
			PhysicsHandler physicsHandler = new PhysicsHandler(mSprite);
			physicsHandler.setVelocityX(velocityX);
			mSprite.setPhysicsHandler(physicsHandler);

			mSpriteNext = new Sprite(texture, (int) (0 + mScale*texture.getWidth()), y);
			PhysicsHandler physicsHandlerNext = new PhysicsHandler(mSpriteNext);
			physicsHandlerNext.setVelocityX(velocityX);
			mSpriteNext.setPhysicsHandler(physicsHandlerNext);
		}
	}
}

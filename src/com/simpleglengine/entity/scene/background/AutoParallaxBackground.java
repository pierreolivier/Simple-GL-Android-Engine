package com.simpleglengine.entity.scene.background;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.IEntity;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.sprite.SpriteBatch;
import com.simpleglengine.tools.ScreenTools;

public class AutoParallaxBackground extends ColorBackground implements IEntity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected TextureRegion mTextureRegion;
	protected int mYAutoParallaxBackground;
	protected float mVelocityX;

	protected IEntity mBackground = null;

	private Sprite mSprite = null, mSpriteNext = null;

	private List <Sprite> mSprites = null, mSpritesNexts = null;
	private Sprite mLastSprite = null, mLastSpriteNext = null;	

	protected List <Follower> mFollowers = null;

	protected float mScale = 1.0f;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AutoParallaxBackground(TextureRegion textureRegion, int y, float velocityX) {
		super(0.0f, 0.0f, 0.0f, 1.0f);

		this.mTextureRegion = textureRegion;
		this.mYAutoParallaxBackground = y;
		this.mVelocityX = velocityX;

		load(textureRegion, y, velocityX);

		this.mFollowers = new ArrayList<Follower>();
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public void setScale(float scale) {
		mScale = scale;

		load(mTextureRegion, mYAutoParallaxBackground, mVelocityX);

		if(mSprites != null && mSpritesNexts != null) {
			for(IEntity pEntity : this.mSprites) {
				pEntity.setScale(mScale);
			}
			for(IEntity pEntity : this.mSpritesNexts) {
				pEntity.setScale(mScale);
			}
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.setScale(mScale);
			mSpriteNext.setScale(mScale);

			for(Follower follower : this.mFollowers) {
				follower.getSprite().setScale(scale);
			}
		}

		//load(mTexture, mYAutoParallaxBackground, mVelocityX);
	}

	@Override
	public float getScale() {
		// TODO Auto-generated method stub
		return mScale;
	}

	public void setBackground(IEntity background) {
		this.mBackground = background;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onLoadSurface(GL10 gl) {
		super.onLoadSurface(gl);

		if(mBackground != null)
			mBackground.onLoadSurface(gl);

		if(mSprites != null && mSpritesNexts != null) {
			for(IEntity pEntity : this.mSprites) {
				pEntity.onLoadSurface(gl);
			}
			for(IEntity pEntity : this.mSpritesNexts) {
				pEntity.onLoadSurface(gl);
			}
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.onLoadSurface(gl);
			mSpriteNext.onLoadSurface(gl);

			for(Follower follower : this.mFollowers) {
				follower.getSprite().onLoadSurface(gl);
			}
		}
	}

	@Override
	public void onDraw(GL10 gl) {
		super.onDraw(gl);

		if(mBackground != null)
			mBackground.onDraw(gl);

		if(mSprites != null && mSpritesNexts != null) {
			for(IEntity pEntity : this.mSprites) {
				pEntity.onDraw(gl);
			}
			for(IEntity pEntity : this.mSpritesNexts) {
				pEntity.onDraw(gl);
			}
		} else if (mSprite != null && mSpriteNext != null) {
			mSprite.onDraw(gl);
			mSpriteNext.onDraw(gl);

			for(Follower follower : this.mFollowers) {
				follower.getSprite().onDraw(gl);
			}
		}
	}


	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);

		if(!mPause) {
			if(mBackground != null)
				mBackground.onUpdate(alpha);

			if(mSprites != null && mSpritesNexts != null) {
				for(IEntity pEntity : this.mSprites) {
					pEntity.onUpdate(alpha);
				}
				for(IEntity pEntity : this.mSpritesNexts) {
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

				for(Follower follower : this.mFollowers) {
					Sprite sprite = follower.getSprite();

					if(sprite.getX()+sprite.getScaledWidth() < 0) {
						if(mSprite.getX()+follower.getxOffset()+sprite.getScaledWidth() < 0) {
							sprite.setPosition(mSpriteNext.getX()+follower.getxOffset(),mSpriteNext.getY()+follower.getyOffset());
						} else {
							sprite.setPosition(mSprite.getX()+follower.getxOffset(),mSprite.getY()+follower.getyOffset());
						}
					}


					sprite.onUpdate(alpha);
				}

				if(mSprite.getX()+mSprite.getScaledWidth() < 0) {
					mSprite.setX(mSpriteNext.getX()+mSpriteNext.getScaledWidth());
				} else if(mSpriteNext.getX()+mSpriteNext.getScaledWidth() < 0) {
					mSpriteNext.setX(mSprite.getX()+mSprite.getScaledWidth());
				}
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void load(TextureRegion textureRegion, int y, float velocityX) {
		if(mScale*textureRegion.getWidth() <= ScreenTools.getWidth()) {
			int xNext = ScreenTools.getWidth();

			mSprites = new ArrayList<Sprite>();
			mSpritesNexts = new ArrayList<Sprite>();

			for(int i = 0; i <= ScreenTools.getWidth();i+=mScale*textureRegion.getWidth()) {
				Sprite spriteTemp = new Sprite(textureRegion, i, y);
				PhysicsHandler physicsHandler = new PhysicsHandler(spriteTemp);
				physicsHandler.setVelocityX(velocityX);
				spriteTemp.setPhysicsHandler(physicsHandler);				

				mSprites.add(spriteTemp);

				xNext = (int) (spriteTemp.getX()+mScale*textureRegion.getWidth());
				mLastSprite = spriteTemp;
			}

			for(int i = xNext; i <= 2*ScreenTools.getWidth();i+=mScale*textureRegion.getWidth()) {
				Sprite spriteTemp = new Sprite(textureRegion, i, y);
				PhysicsHandler physicsHandler = new PhysicsHandler(spriteTemp);
				physicsHandler.setVelocityX(velocityX);
				spriteTemp.setPhysicsHandler(physicsHandler);				

				mSpritesNexts.add(spriteTemp);

				mLastSpriteNext = spriteTemp;
			}
		} else {
			mSprite = new Sprite(textureRegion, 0, y);
			PhysicsHandler physicsHandler = new PhysicsHandler(mSprite);
			physicsHandler.setVelocityX(velocityX);
			mSprite.setPhysicsHandler(physicsHandler);

			mSpriteNext = new Sprite(textureRegion, (int) (0 + mScale*textureRegion.getWidth()), y);
			PhysicsHandler physicsHandlerNext = new PhysicsHandler(mSpriteNext);
			physicsHandlerNext.setVelocityX(velocityX);
			mSpriteNext.setPhysicsHandler(physicsHandlerNext);
		}
	}

	// ===========================================================
	// Class
	// ===========================================================
	public void addFollower(Sprite sprite, float xOffset, float yOffset) {
		PhysicsHandler physicsHandler;
		if(sprite.getPhysicsHandler() == null) {
			physicsHandler = new PhysicsHandler(sprite);
			sprite.setPhysicsHandler(physicsHandler);
		} else {
			physicsHandler = sprite.getPhysicsHandler();
		}
		physicsHandler.setVelocityX(mVelocityX);

		sprite.setPosition(mSprite.getX()+xOffset,mSprite.getY()+yOffset);



		mFollowers.add(new Follower(sprite, xOffset, yOffset));
	}


	private class Follower {
		private Sprite sprite;
		private float xOffset, yOffset;

		public Follower(Sprite sprite, float xOffset, float yOffset) {
			super();
			this.sprite = sprite;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}

		public Sprite getSprite() {
			return sprite;
		}
		public void setSprite(Sprite sprite) {
			this.sprite = sprite;
		}
		public float getxOffset() {
			return xOffset;
		}
		public void setxOffset(float xOffset) {
			this.xOffset = xOffset;
		}
		public float getyOffset() {
			return yOffset;
		}
		public void setyOffset(float yOffset) {
			this.yOffset = yOffset;
		}
	}


}

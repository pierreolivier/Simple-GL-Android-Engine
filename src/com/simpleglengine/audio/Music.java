package com.simpleglengine.audio;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private MediaPlayer mMediaPlayer;
	private boolean mStop;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Music(MediaPlayer mediaPlayer) {
		this.mMediaPlayer = mediaPlayer;
		
		this.mStop = false;
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getCurrentPosition() {
		return mMediaPlayer.getCurrentPosition();
	}
	public int getDuration() {
		return mMediaPlayer.getDuration();
	}
	public boolean isLooping() {
		return mMediaPlayer.isLooping();
	}
	public void setLooping(boolean looping) {
		mMediaPlayer.setLooping(looping);
	}
	public boolean isPlaying() {
		return mMediaPlayer.isPlaying();
	}	
	public void setVolume(float leftVolume, float rightVolume) {
		mMediaPlayer.setVolume(leftVolume, rightVolume);
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void play() {
		if(mStop) {
			try {
				mMediaPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mStop = false;
		}
		mMediaPlayer.start();
	}
	public void stop() {
		mMediaPlayer.stop();
		mStop = true;
	}
	public void pause() {
		mMediaPlayer.pause();

	}	
	public void seekTo(int msec) {
		mMediaPlayer.seekTo(msec);
	}
}

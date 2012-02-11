package com.simpleglengine.audio;

import java.io.IOException;

import android.media.MediaPlayer;

public class Sound {
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
	public Sound(MediaPlayer mediaPlayer) {
		this.mMediaPlayer = mediaPlayer;

		this.mStop = false;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getDuration() {
		return mMediaPlayer.getDuration();
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
}

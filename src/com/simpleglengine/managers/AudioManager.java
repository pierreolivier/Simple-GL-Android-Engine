package com.simpleglengine.managers;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

import com.simpleglengine.audio.Music;
import com.simpleglengine.audio.Sound;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Context mContext;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AudioManager(Context context) {
		super();

		this.mContext = context;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public Music loadMusic(int RRaw) throws IllegalStateException, IOException {
		MediaPlayer mediaPlayer = MediaPlayer.create(mContext, RRaw);		
		return new Music(mediaPlayer);
	}
	public Sound loadSound(int RRaw) throws IllegalStateException, IOException {
		MediaPlayer mediaPlayer = MediaPlayer.create(mContext, RRaw);		
		return new Sound(mediaPlayer);
	}
}

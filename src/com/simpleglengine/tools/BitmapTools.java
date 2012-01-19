package com.simpleglengine.tools;

import com.simpleglengine.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class BitmapTools {
	public static Bitmap loadBitmapFromRessource(Context context, int RDrawable) {
		return BitmapFactory.decodeResource(context.getResources(), RDrawable);
	}
	public static Bitmap loadBitmapFromRessource(Context context, int RDrawable, int transparentColor) {
		Bitmap bitmap =  BitmapFactory.decodeResource(context.getResources(), RDrawable);
		return createTransparentBitmapFromBitmap(bitmap, transparentColor);
	}
	public static Bitmap createTransparentBitmapFromBitmap(Bitmap bitmap, int replaceThisColor) {
		if (bitmap != null) {
			int picw = bitmap.getWidth();
			int pich = bitmap.getHeight();
			int[] pix = new int[picw * pich];
			bitmap.getPixels(pix, 0, picw, 0, 0, picw, pich);

			for (int y = 0; y < pich; y++) {
				for (int x = 0; x < picw; x++) {
					int index = y * picw + x;
					int r = (pix[index] >> 16) & 0xff;
					int g = (pix[index] >> 8) & 0xff;
					int b = pix[index] & 0xff;

					if (pix[index] == replaceThisColor) {
						pix[index] = Color.TRANSPARENT;
					}
				}
			}

			Bitmap bm = Bitmap.createBitmap(pix, picw, pich, Bitmap.Config.ARGB_4444);

			return bm;
		}
		return null;
	}
}

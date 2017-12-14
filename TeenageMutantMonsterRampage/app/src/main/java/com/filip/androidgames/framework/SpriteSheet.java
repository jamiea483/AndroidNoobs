package com.filip.androidgames.framework;

import android.graphics.Bitmap;

import com.filip.androidgames.framework.impl.AndroidPixmap;

/**
 * Created by darkf on 2017-12-14.
 */

public class SpriteSheet {
    Bitmap sprite;
    public int rows;
    public int cols;
    public int cellWidth;
    public int cellHeight;
    public Pixmap pixmap;

    public SpriteSheet(Bitmap spriteBitmap, int cols, int rows ) {

        this.sprite = spriteBitmap;
        this.cols = cols;
        this.rows = rows;
        cellWidth = this.sprite.getWidth()/cols;
        cellHeight = this.sprite.getHeight()/rows;
        this.pixmap = new AndroidPixmap(sprite, Graphics.PixmapFormat.ARGB8888);
    }

}

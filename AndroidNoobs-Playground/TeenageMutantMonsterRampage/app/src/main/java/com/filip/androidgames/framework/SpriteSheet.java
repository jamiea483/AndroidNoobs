package com.filip.androidgames.framework;

/**
 * Created by Jeric on 2017-12-15.
 */
import android.graphics.Bitmap;
import com.filip.androidgames.framework.impl.AndroidPixmap;


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

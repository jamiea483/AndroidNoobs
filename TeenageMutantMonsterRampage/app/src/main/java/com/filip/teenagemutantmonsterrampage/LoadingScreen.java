package com.filip.teenagemutantmonsterrampage;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Screen;

import android.util.Log;

/**
 * Created by jamie on 10/4/2017.
 */

public class LoadingScreen extends Screen {

    private static final String TAG = LoadingScreen.class.getSimpleName();
    public LoadingScreen(Game game) {super(game);}

    public void update(float deltaTime){
        Graphics g = game.getGraphics();
        Assets.background = game.getGraphics().newPixmap("Backgrond.png", Graphics.PixmapFormat.RGB565);
        Assets.mainMenu = game.getGraphics().newPixmap("buttons.png", Graphics.PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("Hit_Hurt.wav");
        Log.d(TAG, "Loading main menu");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

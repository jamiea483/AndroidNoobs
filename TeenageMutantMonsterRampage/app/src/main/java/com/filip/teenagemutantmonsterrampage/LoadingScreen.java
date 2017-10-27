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
        Assets.ready = game.getGraphics().newPixmap("Tap.png", Graphics.PixmapFormat.ARGB4444);
        Assets.resume = game.getGraphics().newPixmap("Resume.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pause = game.getGraphics().newPixmap("pause.png" , Graphics.PixmapFormat.ARGB4444);
        Assets.back = game.getGraphics().newPixmap("Menu.png" , Graphics.PixmapFormat.ARGB4444);
        Assets.settingsMenu = game.getGraphics().newPixmap("settings_buttons.png", Graphics.PixmapFormat.ARGB4444);
        Assets.backButton = game.getGraphics().newPixmap("back_button.png", Graphics.PixmapFormat.ARGB4444);
        Assets.Building = game.getGraphics().newPixmap("Building.png", Graphics.PixmapFormat.ARGB4444);
        Assets.BuildingBackground = game.getGraphics().newPixmap("BuildingBackground.png", Graphics.PixmapFormat.ARGB4444);
        Assets.boxBackground = game.getGraphics().newPixmap("boxBackground.png", Graphics.PixmapFormat.ARGB4444);
        Assets.box = game.getGraphics().newPixmap("box.png", Graphics.PixmapFormat.ARGB4444);
        Assets.fireBreath = game.getGraphics().newPixmap("flamethrower_0005.png", Graphics.PixmapFormat.ARGB4444);


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

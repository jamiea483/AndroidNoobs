package com.filip.teenagemutantmonsterrampage;

import android.app.Activity;
import android.util.Log;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Screen;
import com.filip.androidgames.framework.FileIO;

import java.util.List;
/*
 * Created by Jeric on 2017-10-04.
 */

public class HighScore extends Screen{
    public HighScore(Game game){ super(game);}

    public void update(float deltaTime){
        Graphics g = game.getGraphics();List<TouchEvent> touchEvents = game.getInput().getTouchEvents();


        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 100 && event.y < 98) {
                    game.setScreen(new MainMenuScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }




    public void present (float deltaTime){
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.pause, 0,0);
    }
    @Override
    public void pause() {Settings.save(game.getFileIO());}

    public void resume(){

    }

    public void dispose(){

    }
}
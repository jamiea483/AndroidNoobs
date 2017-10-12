package com.filip.teenagemutantmonsterrampage;

import android.app.Activity;
import android.util.Log;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input;
import com.filip.androidgames.framework.Screen;
import com.filip.androidgames.framework.FileIO;

import java.util.List;
/*
 * Created by Jeric on 2017-10-04.
 */

public class HighScore extends Screen{
    public HighScore(Game game){ super(game);}

    public void update(float deltaTime){
        Graphics g = game.getGraphics();
        }


    public void present (float deltaTime){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);

    }

    @Override
    public void pause() {Settings.save(game.getFileIO());}

    public void resume(){

    }

    public void dispose(){

    }
}
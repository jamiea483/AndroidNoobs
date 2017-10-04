package com.filip.teenagemutantmonsterrampage;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Screen;

import  java.util.List;

/**
 * Created by jamie on 10/4/2017.
 */

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game){ super(game);}

    public void update(float deltaTime){
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP){

                    //New Game
                if(inBounds(event, 250,  500, 300,47)){
                   game.setScreen(new GameScreen(game));
                    if(Settings.soundEnable)
                        Assets.click.play(1);
                    return;
                }
                //Options
                if(inBounds(event, 250, 500 + 150, 300,47)) {
                   // game.setScreen(new OptionScreen(game));
                   if(Settings.soundEnable)
                        Assets.click.play(1);
                    return;
                }
                    //Highscore
                if(inBounds(event, 250, 500 + 300, 300,47)){
                   // game.setScreen(new HighScoreScreen(game));
                   if(Settings.soundEnable)
                        Assets.click.play(1);
                    return;
                }

            }
        }
    }

    public void present (float deltaTime){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.mainMenu, 250, 500 , 0, 0, 300, 47);
        g.drawPixmap(Assets.mainMenu, 250, 500 + 150, 1, 49, 300,47);
        g.drawPixmap(Assets.mainMenu, 250, 500 + 300, 1, 96, 300,47);


    }

    @Override
    public void pause() {Settings.save(game.getFileIO());}

    public void resume(){

    }

    public void dispose(){

    }
}

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
                if(inBounds(event, 175, 500 , 450, 70)){
                   game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)

                        Assets.click.play(1);
                    return;
                }
                //Options
                if(inBounds(event, 175, 500  + 150, 450, 70)) {
                    game.setScreen(new SettingsScreen(game));
                    if(Settings.soundEnabled)

                        Assets.click.play(1);
                    return;
                }
                    //Highscore
                if(inBounds(event, 175, 500 + 300, 450, 72)){

                   game.setScreen(new HighScore(game));
                   if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }

            }
        }
    }

    public void present (float deltaTime){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.mainMenu, 175, 500 , 0, 0, 450, 70);
        g.drawPixmap(Assets.mainMenu, 175, 500 + 150, 0, 72, 450,70);
        g.drawPixmap(Assets.mainMenu, 175, 500 + 300, 0, 145, 450,72);


    }
    @Override
    public void pause() {Settings.save(game.getFileIO());}

    public void resume(){

    }

    public void dispose(){

    }
}

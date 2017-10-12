package com.filip.teenagemutantmonsterrampage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input;
import com.filip.androidgames.framework.Screen;

import java.util.List;

public class CreditsScreen extends Screen {

    public CreditsScreen(Game game){ super(game);}

    public void update(float deltaTime){
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){



                if ( inBounds(event, 300, 600+400, 192, 42)) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new SettingsScreen(game));
                    return;
                }

            }
        }

    }



    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.settingsMenu, 300, 600 - 300, 1, 73, 226,36);
        g.drawPixmap(Assets.backButton, 300, 600 + 400, 0,0,226,36);
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

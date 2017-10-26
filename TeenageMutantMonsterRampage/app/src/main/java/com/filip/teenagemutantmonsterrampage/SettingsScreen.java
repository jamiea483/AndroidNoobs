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

public class SettingsScreen extends Screen {

    public SettingsScreen(Game game){ super(game);}

    public void update(float deltaTime){
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){

                //SFX
                if(inBounds(event, 300,  600, 340, 54)){

                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    switchSoundFX();

                }
                //Music
                if(inBounds(event, 300, 600+100, 340, 54)) {
                    game.setScreen(new SettingsScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    switchMusic();

                }
                //Credits
                if(inBounds(event, 300, 600+200, 340, 54)){
                    //
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new CreditsScreen(game));
                    return;

                }

                if ( inBounds(event, 300, 600+400, 340, 42)) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }

            }
        }

    }

    private void switchSoundFX() {
        Graphics g = game.getGraphics();
        Settings.soundEnabled = !Settings.soundEnabled;
        if ( Settings.soundEnabled )
            g.drawPixmap(Assets.settingsMenu, 200, 600 , 0, 0, 340, 54);
        else
            g.drawPixmap(Assets.settingsMenu, 200, 600 , 340, 0, 340, 54);
    }

    private void switchMusic() {
        Graphics g = game.getGraphics();
        Settings.musicEnabled = !Settings.musicEnabled;
        if ( Settings.musicEnabled )
            g.drawPixmap(Assets.settingsMenu, 200, 600 + 100, 1, 55, 340, 54);
        else
            g.drawPixmap(Assets.settingsMenu, 200, 600 + 100, 340, 55, 340, 54);
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        if ( Settings.soundEnabled )
            g.drawPixmap(Assets.settingsMenu, 200, 600 , 0, 0, 340, 54);
        else
            g.drawPixmap(Assets.settingsMenu, 200, 600 , 340, 0, 340, 54);
        if ( Settings.musicEnabled )
            g.drawPixmap(Assets.settingsMenu, 200, 600 + 100, 1, 55, 340, 54);
        else
            g.drawPixmap(Assets.settingsMenu, 200, 600 + 100, 340, 55, 340, 54);
        g.drawPixmap(Assets.settingsMenu, 200, 600 + 200, 1, 110, 340, 54);

        g.drawPixmap(Assets.backButton, 200, 600 + 400, 0,0,338,55);
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

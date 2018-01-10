package com.filip.teenagemutantmonsterrampage;



import android.graphics.Color;
import android.util.Log;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;
import com.filip.androidgames.framework.impl.AndroidGame;
import com.google.android.gms.games.Games;

import java.util.List;

/**
 * Created by jamie on 10/4/2017.
 */

public class GameScreen extends Screen {
    private static final String TAG = TMMR.class.getSimpleName();

    enum GameState{
        Ready,
        Running,
        Pause,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    int freehumans = 0;
    String free = "0";

    public GameScreen(Game game){
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Pause)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
        {
            game.unlock("A5");
            game.submitScore(oldScore);
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents){
        if(touchEvents.size() > 0 )
            state = GameState.Running;
    }


    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime){
        int len = touchEvents.size();

        //pause button
        for(int i = 0;i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                //Check if tapped on a human
                world.Capture(event.x,event.y);
                if (event.x < 100 && event.y < 98) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Pause;
                    game.showBanner();
                    game.showInterstitialAd();
                    return;
                }


                //Fire Breath
                if(event.x > 200 && event.x <200+100 &&
                event.y > 1100 && event.y < 1100+98 &&
                        world.fireBreathUsed == false &&
                        world.fireActive == false){
                        world.fireActive = true;
                    Log.d(TAG, "Ability Active"  );
                }
                else if (event.x > 200 && event.x <200+100 &&
                        event.y > 1100 && event.y < 1100+98 &&
                        world.fireBreathUsed == false &&
                        world.fireActive == true) {
                    Log.d(TAG, "Ability Deactivited"  );
                    world.fireActive = false;
                    world.fire.setPos(0,0);

                }
            }
            if (event.type == TouchEvent.TOUCH_DOWN) {

                //if FireBreath is Active set position to where the player tap
                //and set the floor it was used on.
                if(world.fireActive == true){
                    world.fire.setPos(event.x,event.y);
                    world.fire.applyAffect();
                    Log.d(TAG, String.valueOf(world.fire.getPos().x)+","+ String.valueOf(world.fire.getPos().y));
                    Log.d(TAG, "Ability set"  );
                }
                //Check if tapped on a human
                world.Capture(event.x, event.y);
            }
        }

        world.update(deltaTime);

        if(world.fire.active){
            game.unlock("A4");
        }

        if (world.gameOver){
            state = GameState.GameOver;
        }
        if (oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if (Settings.soundEnabled) {
                //Assets.eat.play(1);
            }
            if (oldScore > 10) {
                game.unlock("A2");
            }
            if (oldScore > 100) {
                game.unlock("A3");
            }
        }
            if(freehumans != world.freeCivilian){
                freehumans = world.freeCivilian;
                free = ""+freehumans;
            }


    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();

        //unpause button
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 250 && event.x < 450 &&
                        event.y > 550 && event.y < 650 ) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Running;
                    return;
                }
                //menu button
                if (event.x > 250 && event.x < 450 &&
                        event.y > 700 && event.y < 750) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();

        //menu button
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x <= 300 && event.x <= 300 + 201 &&
                        event.y <= 600 && event.y <= 600 + 200) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                }
            }

        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);

        if(state == GameState.Ready)
            drawReadyUI();
        if(state == GameState.Running) {
            drawRunningUI();
        }
        if(state == GameState.Pause)
            drawPauseUI();
        if(state == GameState.GameOver)
            drawGameOverUI();


        drawText(g, score, g.getWidth()/2-score.length()*32/2,20);

        drawText(g, free, (g.getWidth()/2-free.length()*32/2)+200, 20);
    }

    private void drawWorld(World world){
        Graphics g = game.getGraphics();

        //Draws the civilian in the building
        g.drawPixmap(Assets.BuildingBackground, 50,150);

        if(world.fire.active) {
            g.drawSprite(Assets.Fire, Floors.stairsUpStartX[1] + 60, Floors.floorsY[world.fire.getFloor()] - 120, world.fire.curFrame, false);
        }
        for (Human human : world.humans) {
            g.drawSprite(Assets.humanSpriteSheet, (int)human.pos.x, (int)human.pos.y - human.spriteHeight, human.curFrame, !human.facingRight);
        }
        g.drawPixmap(Assets.Building, 50,150);

    }


    private void drawReadyUI(){
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 150, 500);

    }

    private void drawRunningUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 0,0);

        //Fire Breath
        g.drawPixmap(Assets.boxBackground, 220,1120);
        g.drawPixmap(Assets.fireBreath, 215,1115,0,0, 70,Math.round(world.fireBreathCooldown) * 14);
        g.drawPixmap(Assets.box, 200,1100);

    }

    private void drawPauseUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.resume, 250, 550);
        g.drawPixmap(Assets.back, 250, 700);

    }

    private void drawGameOverUI(){
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.GameOver, 30,100);
        drawText(g, score, 296, 468 );
        g.drawPixmap(Assets.back, 0, 0);
    }

    //Score Text
    public void drawText(Graphics g, String line, int x, int y){
        int len = line.length();
        for(int i = 0; i < len; i++){
            char character = line.charAt(i);

            if(character == ' '){
                x+=31;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if(character == '.'){
                srcX = 321;
                srcWidth = 18;
            }else{
                srcX = (character -'0') * 32;
                srcWidth = 32;
            }

            g.drawPixmap(Assets.highscore, x, y, srcX, 0, srcWidth, 48);
            x+=srcWidth;

        }
    }

    @Override
    public void pause() {

        if (state == GameState.Running)
            state = GameState.Pause;
         if (world.gameOver){
        Settings.addScore(world.score);
          Settings.save(game.getFileIO());
         }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
package com.filip.teenagemutantmonsterrampage;



import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by jamie on 10/4/2017.
 */

public class GameScreen extends Screen {
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
            updateGameOver(touchEvents);
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
                if (event.x < 100 && event.y < 98) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Pause;
                    return;
                }

                //Fire Breath
                if(event.x > 200 && event.x <200+100 &&
                event.y > 1100 && event.y < 1100+98 &&
                        world.fireBreathUsed == false){
                    world.fireBreathUsed = true;
                    world.fireBreathCooldown = 0f;
                }

                //Electric Tongue
                if(event.x >100+400 && event.x < 100+500 &&
                        event.y > 1100 && event.y < 1100+98 &&
                        world.electricTongueUsed == false){
                    world.electricTongueUsed = true;
                    world.electricTongueCooldown = 0f;
                }
            }

        }

        world.update(deltaTime);
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
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Pause)
            drawPauseUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
    }

    private void drawWorld(World world){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.BuildingBackground, 150,475);
        //Hunman animation goes here
        g.drawPixmap(Assets.Building, 150,475);
    }

    private void drawReadyUI(){
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 150, 500);

    }

    private void drawRunningUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 0,0);

        //Fire Breath
        g.drawPixmap(Assets.boxBackground, 200,1100);
        g.drawPixmap(Assets.fireBreath, 200,1100,0,0, 70,Math.round(world.fireBreathCooldown) * 14);
        g.drawPixmap(Assets.box, 200,1100);


        //Electric Tongue
        g.drawPixmap(Assets.boxBackground, 100+400,1100);
       // g.drawPixmap(Assets.fireBreath, 100+400,1100, 0,world.electrictTongueCooldown * 40);
        g.drawPixmap(Assets.box, 100+400,1100);

    }

    private void drawPauseUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.resume, 250, 550);
        g.drawPixmap(Assets.back, 250, 700);

    }

    private void drawGameOverUI(){
        Graphics g = game.getGraphics();

    }

    @Override
    public void pause() {

        if (state == GameState.Running)
            state = GameState.Pause;
        // if (world.gameover){
        //Settings.addScore(world.score);
        //  Settings.save(game.getFileIO());
        // }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
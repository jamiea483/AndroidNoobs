package com.filip.teenagemutantmonsterrampage;

/**
 * Created by jamie on 10/4/2017.
 */

public class World {
    static final int WORLD_WIDTH = 4;
    static final int WORLD_HEIGHT = 6;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;

    public boolean gameOver = false;
    public int score = 0;

    boolean field[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    public World(){

    }

    public void update(float deltaTime){
        if(gameOver)
            return;
        tickTime += deltaTime;
        while (tickTime > tick){
            tickTime -= tick;
        }
    }
}

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
    public boolean fireBreathUsed = false;
    private float maxFireBreathCooldown = 5f;
    public float fireBreathCooldown = 5f;
    public boolean electricTongueUsed = false;
    private float maxElectricTongueCooldown = 5f;
    public float electricTongueCooldown = 5f;

    boolean field[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    public World(){

    }

    public void update(float deltaTime){
        if(gameOver)
            return;
        if (fireBreathUsed){
            fireBreathCooldown += deltaTime;
            if(fireBreathCooldown >= maxFireBreathCooldown){
                fireBreathUsed = false;
                fireBreathCooldown = maxFireBreathCooldown;
            }
        }
        if (electricTongueUsed){
            electricTongueCooldown += deltaTime;
            if(electricTongueCooldown >= maxElectricTongueCooldown){
                electricTongueUsed = false;
                electricTongueCooldown = maxElectricTongueCooldown;
            }
        }
        tickTime += deltaTime;
        while (tickTime > tick){
            tickTime -= tick;
        }
    }
}

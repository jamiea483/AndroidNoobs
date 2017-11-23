package com.filip.teenagemutantmonsterrampage;

import com.filip.androidgames.framework.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jamie on 10/4/2017.
 */

public class World {
    static final int WINDOW_WIDTH = 95;
    static final int WINDOW_HEIGHT = 65;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;

    public boolean gameOver = false;
    public int freeCivilian = 0;
    public int score = 0;
    public boolean fireBreathUsed = false;
    private float maxFireBreathCooldown = 5f;
    public float fireBreathCooldown = 5f;
    public boolean electricTongueUsed = false;
    private float maxElectricTongueCooldown = 5f;
    public float electricTongueCooldown = 5f;

    boolean field[][] = new boolean[WINDOW_WIDTH][WINDOW_HEIGHT];
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    List<Human> humans = new ArrayList<>();
    Game game;


    public World(){

        spawnHuman(0,"civilian");
        spawnHuman(1,"civilian");
        spawnHuman(2,"civilian");
        spawnHuman(3,"civilian");
        spawnHuman(0,"civilian");
        spawnHuman(1,"civilian");
        spawnHuman(2,"civilian");
        spawnHuman(3,"civilian");

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

            //Goes though the array of active civilian and checks if the are at the top and
            //if they are makes them safe and adds them to the freeCivilian counter.
            for(int x = 0; x < humans.size(); x++){
               if( humans.get(x).topFloor == true && humans.get(x).safe == false ){
                   freeCivilian++;
                   humans.get(x).safe = true;
               }
            }
            //Gameover equals true when 4 civilian reach the top
            if (freeCivilian == 4){
                gameOver = true;
            }
        }

        for (Human human : humans )
            human.update(deltaTime);

    }

    public void spawnHuman(int floor, String humanType ) {
        Random rand = new Random();

        if ( humanType == "civilian" )
            humans.add(new Civilian(new Vector2(rand.nextInt(360)+260, Floors.floorsY[floor]), floor));

    }
}

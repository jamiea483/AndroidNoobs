package com.filip.teenagemutantmonsterrampage;

import android.util.Log;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Input;
import com.filip.androidgames.framework.impl.AndroidGame;
import com.google.android.gms.games.Games;

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
    public boolean fireActive = false;
    public boolean fireBreathUsed = false;
    private float maxFireBreathCooldown = 5f;
    public float fireBreathCooldown = 5f;
    private static final String TAG = TMMR.class.getSimpleName();
    boolean field[][] = new boolean[WINDOW_WIDTH][WINDOW_HEIGHT];
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    List<Human> humans = new ArrayList<>();
    FireBreath fire;
    Game game;


    public World() {

        spawnHuman(0, "civilian");
        spawnHuman(1, "civilian");
        spawnHuman(2, "civilian");
        spawnHuman(3, "civilian");
        spawnHuman(0, "civilian");
        spawnHuman(1, "civilian");
        spawnHuman(2, "civilian");
        spawnHuman(3, "civilian");

        fire = new FireBreath(0,0);
    }

    public void Capture(int touchx , int touchy) {
        for (int x = 0; x < humans.size(); x++) {
            if (touchx < humans.get(x).pos.x + 15 && touchx > humans.get(x).pos.x - 15
                    && touchy < humans.get(x).pos.y + 35 && touchy > humans.get(x).pos.y - 35) {
                score += 10;
                Random rand = new Random();
                spawnHuman(0,"civilian");
                Log.d(TAG,"Human captured.");

                humans.remove(x);

                //Future solution to save array space will reuse captured human
                //humans.get(x).curFloor = rand.nextInt(2);
                //humans.get(x).pos = new Vector2(rand.nextInt(360)+260,Floors.floorsY(humans.get(x).curFloor));
            }
        }
    }


    public void update(float deltaTime) {
        if (gameOver)
            return;

   if(fireActive) {

       Log.d(TAG, String.valueOf(fire.getFloor())  );
       Log.d(TAG, String.valueOf(fire.getPos().x)+","+ String.valueOf(fire.getPos().y));
        if(fire.getFloor() == 0){
            Log.d(TAG, "Use Abilitiy on 1"  );
            for (int x = 0; x < humans.size(); x++){
                if(humans.get(x).curFloor == 0){
                    humans.remove(x);
                    score += 10;
                }
            }
            fireBreathUsed = true;
            fireBreathCooldown = 0;

        }else if(fire.getFloor() == 1){
            Log.d(TAG, "Use Abilitiy on 2"  );
            for (int x = 0; x < humans.size(); x++){
                if(humans.get(x).curFloor == 1){
                    humans.remove(x);
                    score += 10;
                }
            }
            fireBreathUsed = true;
            fireBreathCooldown = 0;

        }else if(fire.getFloor() == 2){
            Log.d(TAG, "Use Abilitiy on 3"  );
            for (int x = 0; x < humans.size(); x++){
                if(humans.get(x).curFloor == 2){
                    humans.remove(x);
                    score += 10;
                }
            }
            fireBreathUsed = true;
            fireBreathCooldown = 0;

        }else if(fire.getFloor() == 3){
            Log.d(TAG, "Use Abilitiy on 4"  );
            for (int x = 0; x < humans.size(); x++){
                if(humans.get(x).curFloor == 3){
                    humans.remove(x);
                    score += 10;
                }
            }
            fireBreathUsed = true;
            fireBreathCooldown = 0;
        }
    }



     if (fireBreathUsed) {
       fireActive = false;
         fire.setPos(0,0);
         fire.floor = 4;
         fireBreathCooldown += deltaTime;
             if (fireBreathCooldown >= maxFireBreathCooldown) {
                fireBreathUsed = false;
               fireBreathCooldown = maxFireBreathCooldown;

           }
     }

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;

            //Goes though the array of active civilian and checks if the are at the top and
            //if they are makes them safe and adds them to the freeCivilian counter.
            for (int x = 0; x < humans.size(); x++) {
                if (humans.get(x).topFloor == true && humans.get(x).safe == false) {
                    freeCivilian++;
                    humans.get(x).safe = true;
                }
            }
                //Gameover equals true when 4 civilian reach the top
                if (freeCivilian == 7) {
                    gameOver = true;
                }
            }

            for (Human human : humans)
                human.update(deltaTime);

        }



    public void spawnHuman(int floor, String humanType ) {
        Random rand = new Random();

        if ( humanType == "civilian" )
            humans.add(new Civilian(new Vector2(rand.nextInt(360)+260, Floors.floorsY[floor]), floor));

    }
}

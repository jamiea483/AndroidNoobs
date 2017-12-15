package com.filip.teenagemutantmonsterrampage;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;

/**
 * Created by darkf on 2017-10-25.
 */

public class Human {

    public Vector2 pos;
    public int spriteHeight;
    public int curFloor;
    public String state;
    public String goal;
    public Vector2 vel;
    public float xSpd;
    public float ySpd;
    public Vector2 stairsPos;
    public boolean climbingStairs;
    public boolean topFloor;
    public boolean safe;
    public boolean canBeEaten;

    public Human(Vector2 position, int curFloor ) {
        pos = position;
        this.curFloor = curFloor;
        state = "idle";
        goal = "think";
    }

    public void think() {

    }

    public void update( float deltaTime ) {


    }

}

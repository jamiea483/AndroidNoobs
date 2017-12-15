package com.filip.teenagemutantmonsterrampage;

import android.graphics.Color;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;

/**
 * Created by darkf on 2017-10-25.
 */

public class Civilian extends Human {


    public Civilian(Vector2 position, int curFloor ) {
        super(position, curFloor);
        spriteHeight = 70;
        xSpd = 100;
        ySpd = 107;
        vel = new Vector2(0,0);
        stairsPos = new Vector2(0,0);
        climbingStairs = false;
        topFloor = false;
        safe = false;
        canBeEaten = false;
        curFrame = 0;
        facingRight = true;

    }

    @Override
    public void think() {
        if ( state == "idle" )
            idleThinker();
        else if ( state == "run" )
            runThinker();
        else if ( state == "climb right stairs" )
            rightStairsThinker();
        else if ( state == "climb left stairs" )
            leftStairsThinker();
    }

    public void idleThinker() {
        if ( curFloor < 4 ) {
            goal = "go to floor above";
            state = "run";
        }else {
            state = "idle";
            topFloor = true;
        }

    }

    public void runThinker() {
        if ( goal == "go to floor above" ) {
            if ( ( curFloor == 0 || curFloor == 2 ) ){
                if ( pos.x < Floors.stairsUpStartX[0] )
                    vel.x = xSpd;
                else {
                    state = "climb right stairs";
                    stairsPos.x = 0;
                    stairsPos.y = 0;
                    climbingStairs = true;
                }
            }
            else if ( ( curFloor == 1 ) ){
                if ( pos.x > Floors.stairsUpStartX[1] )
                    vel.x = -xSpd;
                else {
                    state = "climb left stairs";
                    stairsPos.x = 0;
                    stairsPos.y = 0;
                    climbingStairs = true;
                }
            }
            else if ( curFloor == 3 ) {
                if ( pos.x < Floors.stairsUpStartX[3] + 8 && pos.x > Floors.stairsUpStartX[3] - 8){
                    pos.x = Floors.stairsUpStartX[3];
                    state = "climb left stairs";
                    stairsPos.x = 0;
                    stairsPos.y = 0;
                    climbingStairs = true;
                }
                else if ( pos.x > Floors.stairsUpStartX[3] )
                    vel.x = -xSpd;
                else if ( pos.x < Floors.stairsUpStartX[3])
                    vel.x = xSpd;

            }

        }
    }

    public void rightStairsThinker() {
        if ( goal == "go to floor above" ) {
            if ( stairsPos.y >= -Floors.bottomHalfStairsHeight && stairsPos.x >= -Floors.stairsWidth ) {
                vel.x = -xSpd*0.7f;
                vel.y = -ySpd*0.7f;

            }
            else if ( stairsPos.y < Floors.bottomHalfStairsHeight && stairsPos.x < 0 ) {
                vel.x = xSpd*0.7f;
                vel.y = -ySpd*0.7f;
            }

            if ( stairsPos.y <= -214 ) {
                climbingStairs = false;
                curFloor += 1;
                stairsPos.x = 0;
                stairsPos.y = 0;
                state = "idle";
                vel.y = 0;
                vel.x = 0;
            }
        }
    }

    public void leftStairsThinker() {
        if ( goal == "go to floor above" ) {
            if ( stairsPos.y >= -Floors.bottomHalfStairsHeight && stairsPos.x <= Floors.stairsWidth ) {
                vel.x = xSpd*0.7f;
                vel.y = -ySpd*0.7f;

            }
            else if ( stairsPos.y < Floors.bottomHalfStairsHeight && stairsPos.x > 0 ) {
                vel.x = -xSpd*0.7f;
                vel.y = -ySpd*0.7f;
            }

            if ( stairsPos.y <= -214 ) {
                climbingStairs = false;
                curFloor += 1;
                stairsPos.x = 0;
                stairsPos.y = 0;
                state = "idle";
                vel.y = 0;
                vel.x = 0;
            }
        }
    }

    @Override
    public void update( float deltaTime ) {

        think();
        pos.x += vel.x*deltaTime;
        pos.y += vel.y*deltaTime;
        if ( climbingStairs ) {
            stairsPos.x += vel.x*deltaTime;
            stairsPos.y += vel.y*deltaTime;
        }

        curFrame++;
        if ( curFrame > 7 )
            curFrame = 0;


        if ( vel.x < 0 )
            facingRight = false;
        else
            facingRight = true;
    }
}
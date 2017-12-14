package com.filip.teenagemutantmonsterrampage;

import android.util.Log;

/**
 * Created by jamie on 12/9/2017.
 */

public class FireBreath extends Abilities {
    private static final String TAG = TMMR.class.getSimpleName();

    public FireBreath(int posX, int posY){
        super (posX,posY);
        floor = 4;
        curFrame = 0;
        active = false;
    }

    //Checks the position of the firebreath and sets
    //the floor.
    public void applyAffect(){

        if(getPos().y <= Floors.floorsY[0] && getPos().y >= Floors.floorsY[1] + 10
                &&getPos().x >= Floors.stairsUpStartX[1] && getPos().x <= Floors.stairsUpStartX[0]){
            floor = 0;
            Log.d(TAG, "Ability floor set to 0."  );
        }else if(getPos().y <= Floors.floorsY[1] && getPos().y >= Floors.floorsY[2] + 10
                &&getPos().x >= Floors.stairsUpStartX[1] && getPos().x <= Floors.stairsUpStartX[0]){
            floor = 1;
            Log.d(TAG, "Ability floor set to 1."  );
        }else if(getPos().y <= Floors.floorsY[2] && getPos().y >= Floors.floorsY[3] + 10
                &&getPos().x >= Floors.stairsUpStartX[1] && getPos().x <= Floors.stairsUpStartX[0]){
            floor = 2;
            Log.d(TAG, "Ability floor set to 2."  );
        }else if(getPos().y <= Floors.floorsY[3] && getPos().y >= Floors.floorsY[4] + 10
                &&getPos().x >= Floors.stairsUpStartX[1] && getPos().x <= Floors.stairsUpStartX[0]){
            floor = 3;
            Log.d(TAG, "Ability floor set to 3."  );
        }

    }

    @Override
    public void update( float deltaTime ) {

        if(active) {
            curFrame++;
        }
        if ( curFrame > 30 ) {
            curFrame = 0;
            active = false;
        }
    }
}

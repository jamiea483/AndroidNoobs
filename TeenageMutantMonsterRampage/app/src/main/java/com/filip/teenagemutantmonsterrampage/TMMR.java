package com.filip.teenagemutantmonsterrampage;

import android.util.Log;

import com.filip.androidgames.framework.Screen;
import com.filip.androidgames.framework.impl.AndroidGame;

/**
 * Created by jamie on 10/4/2017.
 */

public class TMMR extends AndroidGame {
    private static final String TAG = TMMR.class.getSimpleName();

    @Override
    public Screen getStartScreen(){
        Log.d(TAG,"Loading assets.");
        return new LoadingScreen(this);

    }

    @Override
    public void onSignInFailed() {

    }

   @Override
    public void onSignInSucceeded() {

   }



}

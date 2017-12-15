package com.filip.teenagemutantmonsterrampage;

/**
 * Created by jamie on 12/10/2017.
 */

public class Abilities {

    private Vector2 pos;
     public int floor;



    public Abilities(int posX, int posY){
        this.pos = new Vector2(posX,posY);
    }

    public void setPos(int posX, int posY){
        this.pos = new Vector2(posX, posY);
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getFloor() {
        return floor;
    }


}

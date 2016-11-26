package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Stone {

    public Stone(){

    }

    private StoneColor stoneColor;

    public StoneColor getStoneColor(){
        return this.stoneColor;
    }

    public void setStoneColor(StoneColor color) {
        this.stoneColor = color;
    }


}

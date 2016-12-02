package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Stone {

    /**
     * A stone has a color, which is a enum StoneColor
     */
    private StoneColor stoneColor;

    /**
     * Default constructor for a stone
     */
    public Stone() {

    }

    public StoneColor getStoneColor() {
        return this.stoneColor;
    }

    public void setStoneColor(StoneColor color) {
        this.stoneColor = color;
    }


}

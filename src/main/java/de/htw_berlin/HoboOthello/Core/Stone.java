package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Stone {

    /**
     * A stone has a color, which is a enum named Color
     */
    private Color stoneColor;

    /**
     * Default constructor for a stone
     */
    public Stone() {

    }

    public Color getStoneColor() {
        return this.stoneColor;
    }

    public void setStoneColor(Color color) {
        this.stoneColor = color;
    }


}

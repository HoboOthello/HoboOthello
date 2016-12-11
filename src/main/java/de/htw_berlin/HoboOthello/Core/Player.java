package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Player {

    /**
     * A player has a color, which is a enum Color
     */
    private Color color;


    /**
     * Default constructor for a player
     */
    // todo do we need a empty construktor? Very dangerous!
    public Player(){

    }


    /**
     * Constructor for a player
     *
     * @param color StoneColor which can be WHITE or BLACK
     *
     */
    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Field setMove(Board board) {
        return null;
    }

}

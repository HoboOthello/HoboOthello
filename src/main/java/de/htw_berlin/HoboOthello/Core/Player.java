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

    /**
     * Method which sets the next Player (changes the color of the Player)
     * If Player is white it will be set to black,
     * and if the Player is black than it will be set to white
     */
    //TODO laura: do we need this method?
    public void setNextPlayer() {
        if (this.color == Color.WHITE) {
            setColor(Color.BLACK);
        }
        if (this.color == Color.BLACK) {
            setColor(Color.WHITE);
        } else {
            throw new IllegalArgumentException("Color is not defined! Error.");
        }

    }

}

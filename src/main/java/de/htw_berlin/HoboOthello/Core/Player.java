package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Player {

    /**
     * A player has a color, which is a enum PlayerColor
     */
    private PlayerColor playerColor;

//TODO Question: Do we need smth like this?: player method which sets the stoneColor to its own PlayerColor?
    //TODO: Not only for Color, more for Game, example: is a Player User, KI or Network

    /**
     * Default constructor for a player
     */
    public Player() {

    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Method which sets the next Player (changes the color of the Player)
     * If Player is white it will be set to black,
     * and if the Player is black than it will be set to white
     */
    public void setNextPlayer() {
        if (this.playerColor == PlayerColor.WHITE){
            setPlayerColor(PlayerColor.BLACK);
        }
        if (this.playerColor == PlayerColor.BLACK){
            setPlayerColor(PlayerColor.WHITE);
        } else {
            throw new IllegalArgumentException("PlayerColor is not defined! Error.");
        }

    }


}

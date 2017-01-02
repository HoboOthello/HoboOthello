package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Player {

    /**
     * A player has a color, which is a enum Color
     */
    private Color color;

    private PlayerType playerType;


    /**
     * Constructor for a player
     *
     * @param color StoneColor which can be WHITE or BLACK
     *
     */
    public Player(Color color) {
        this.color = color;
        this.playerType = PlayerType.DESKTOP;
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


    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}

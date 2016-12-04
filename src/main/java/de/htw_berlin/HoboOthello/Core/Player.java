package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Player {

    /**
     * A player has a color, which is a enum PlayerColor
     */
    private PlayerColor playerColor;




    /**
     * Default constructor for a player
     *
     * @param playerColor StoneColor which can be WIHTE or BLACK
     *
     */
    public Player(PlayerColor playerColor) {
        this.playerColor = playerColor;

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
    //TODO laura: do we need this method?
    public void setNextPlayer() {
        if (this.playerColor == PlayerColor.WHITE) {
            setPlayerColor(PlayerColor.BLACK);
        }
        if (this.playerColor == PlayerColor.BLACK) {
            setPlayerColor(PlayerColor.WHITE);
        } else {
            throw new IllegalArgumentException("PlayerColor is not defined! Error.");
        }

    }

    /**
     * Get the StoneColor, is allmost the same like PlayerColor, but it will return
     * a enum StoneColor with the value WHITE or BLACK instead of PlayerColor
     *
     * @return StoneColor WHITE or BLACK, the same Color like PlayerColor, only another enum class
     */
    public StoneColor getStoneColor() {
        if (this.playerColor == PlayerColor.BLACK) {
            return StoneColor.BLACK;
        } else {
            return StoneColor.WHITE;
        }
    }


    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}

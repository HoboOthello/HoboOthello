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
     * Player Typ, like DESKTOP, KI, NETWORK, which is a enum PlayerTyp
     */
    private PlayerTyp playerTyp;


    /**
     * Default constructor for a player
     *
     * @param playerColor StoneColor wich can be WIHTE or BLACK
     * @param playerTyp   PlayerTyp wich can be DESKTOP, KI or NETWORK
     */
    public Player(PlayerColor playerColor, PlayerTyp playerTyp) {
        this.playerColor = playerColor;
        this.playerTyp = playerTyp;
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


    public PlayerTyp getPlayerTyp() {
        return playerTyp;
    }

    public void setPlayerTyp(PlayerTyp playerTyp) {
        this.playerTyp = playerTyp;
    }
}

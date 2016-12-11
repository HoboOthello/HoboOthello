package de.htw_berlin.HoboOthello.Core;

import java.awt.*;

/**
 * Created by laura on 24.11.16.
 */
public class Player {

    /**
     * A player has a color, which is a enum Color
     */
    private Color color;

    private PlayerTyp playerTyp;

    private Level level;

    /**
     * Constructor for a player
     *
     * @param color StoneColor which can be WHITE or BLACK
     *
     */
    public Player(Color color) {
        this.color = color;
        this.playerTyp = PlayerTyp.DESKTOP;
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


    public PlayerTyp getPlayerTyp() {
        return playerTyp;
    }

    public void setPlayerTyp(PlayerTyp playerTyp) {
        this.playerTyp = playerTyp;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}

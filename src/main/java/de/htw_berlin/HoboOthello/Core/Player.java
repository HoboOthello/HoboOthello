package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class Player {

    public Player(){

    }

    /**
     * Set the current Player
     * true == Black
     * false == White
     * default is Black cause Black start the game
     */
    // TODO: REFACTOR!! You will blow up your leg otherwise!
    private boolean CurrentPlayer = true;

    public boolean isCurrentPlayer() {
        return CurrentPlayer;
    }

    /**
     * Get current Player and return it as easy readable char
     *
     * @return b == Black Player
     * w == White Player
     */
    // TODO: REFACTOR! You will blow up your leg otherwise!
    public char isCurrentPlayerAsChar() {
        if (CurrentPlayer) {
            return 'b';
        } else {
            return 'w';
        }
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        CurrentPlayer = currentPlayer;
    }

    /**
     * Set the current User with a Char, is added to
     * debug the code
     *
     * @param currentPlayer b == Black
     *                      w == White
     */
    public void setCurrentPlayerAsChar(char currentPlayer) {
        switch (currentPlayer) {
            case 'b':
                this.CurrentPlayer = true;
                break;
            case 'w':
                this.CurrentPlayer = false;
                break;
            default:
                throw new IllegalArgumentException("Wrong input. Only b and w are allowed to set!");
        }
    }

    /**
     * Method which sets the next Player
     * If CurrentPlayer is Black than it will be set to White,
     * and if the CurrentPlayer is White it will be set to Black
     */
    public void setNextPlayer() {
        this.CurrentPlayer = !this.CurrentPlayer;
    }


}

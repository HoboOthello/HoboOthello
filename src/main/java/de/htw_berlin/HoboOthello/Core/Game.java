package de.htw_berlin.HoboOthello.Core;

import de.htw_berlin.HoboOthello.KI.KI;
import de.htw_berlin.HoboOthello.Network.Network;

/**
 * Created by Steffen Exler on 30.11.16.
 */
public class Game {

    private Player playerBlack;
    private Player playerWhite;

    private Player currentPlayer;

    private GameState gameState;

    private Board gameBoard;

    private Network network;

    private KI ki;

    /**
     * create a new Game
     *
     * @param boardSize int 6, 8 or 10
     * @param newPlayerBlack the Black Player
     * @param newPlayerWhite the White Player
     */
    public void newGame(int boardSize, Player newPlayerBlack, Player newPlayerWhite) {
        //TODO add exception
        //init Board
        this.gameBoard = new Board(boardSize);

        // init Players
        this.playerBlack = newPlayerBlack;
        this.playerWhite = newPlayerWhite;

        this.currentPlayer = this.playerBlack;

        /*
        // init ki && network, if needed
        if (gameType == GameType.KI) {
            this.ki = new KI();
        }

        if (gameType == GameType.NETWORK) {
            // todo steffen: need add network stuff
            this.network = new Network();
        }
        */

        // init gameState
        this.gameState = GameState.RUNNING;

        // set all others vars to default or create new game class and make this to a constructor
    }

    /**
     * Set the game turn for the current user, return true if the turn was valid
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @return  true == turn is valid
     *          false == turn is not valid
     */
    public boolean setTurn(Field field) {
        if (this.gameState != GameState.STOP) {
            //todo add some kind of output why it fails
            return false;
        }

        // return if the turn was successful (possible)
        GameRule move = new GameRule(this.gameBoard.isFields());

        // check if this turn is allowed
        boolean moveAllowed = move.isMoveAllowed(field, currentPlayer.getColor());

        if (moveAllowed) {
            // set turn
            move.setMove(field, currentPlayer.getColor());
            nextPlayer();
            move.changeAllPossibleFieldsToTrue(currentPlayer.getColor());
            this.gameBoard.setFields(move.getFields());
        } else {
            return false;
        }

        if (move.getPossibleMoves() == 0) {
            // currentPlayer can't make a possible turn, so set the next Player
            nextPlayer();
            move.changeAllPossibleFieldsToTrue(currentPlayer.getColor());
            this.gameBoard.setFields(move.getFields());

            if (move.getPossibleMoves() == 0) {
                // no Player can make a Possible move anymore, so end the game
                this.gameState = GameState.STOP;
            }

            /*
            // if the currentPlayer ist KI or NETWORK, do there special move
            switch (currentPlayer.getPlayerType()) {
                case KI:
                    this.ki.setFields(this.gameBoard.isFields());
                    ki.setMove(currentPlayer.getColor());
                    this.gameBoard.setFields(ki.getFields());
                    nextPlayer();
                    break;
                case NETWORK:
                    //todo steffen do some awesome stuff as well
                    nextPlayer();
                    break;
            }
            */
        }

        // return if this tun was successful
        return false;
    }

    private void setKiNetworkTurn() {

    }


    /**
     * Calls a method to count the number of BLACK and WHITE Stones
     *
     * @param color BLACK or WHITE
     * @return Player Points as int
     *
     */
    //TODO Steffen: hab die Methode refactored, aber erscheint mir ein wenig überflüssig?
    public int countPlayerPoints(Color color) {

        Color colorToCount = null;

        if (colorToCount == Color.BLACK) {
            colorToCount = Color.BLACK;
        }
        if (colorToCount == Color.WHITE){
            colorToCount = Color.WHITE;
        } else {
            throw new NullPointerException("Color is null!");
        }

        return gameBoard.getNumberOfFieldsOccupiedByStone(colorToCount);
    }

    /**
     * set the currentPlayer to playerWhite or playerBlack
     */
    private void nextPlayer() {
        if (this.currentPlayer == playerBlack) {
            this.currentPlayer = playerWhite;
        } else {
            this.currentPlayer = playerBlack;
        }
    }

    //TODO Fields Method, confused. What is this? Why here? Also, why is and not get? It's not a boolean..
    public Field[][] isFields() {
        return this.gameBoard.isFields();
    }
}

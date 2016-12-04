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
    public boolean setTurn(int x, int y) {
        if (this.gameState != GameState.STOP) {
            //todo add some kind of output why it fails
            return false;
        }

        // return if the turn was successful (possible)
        GameRule move = new GameRule(this.gameBoard.isFields());

        // check if this turn is allowed
        boolean moveAllowed = move.isMoveAllowed(x, y, currentPlayer.getStoneColor());

        if (moveAllowed) {
            // set turn
            move.setMove(x, y, currentPlayer.getStoneColor());
            nextPlayer();
            move.changeAllPossibleFieldsToTrue(currentPlayer.getStoneColor());
            this.gameBoard.setFields(move.getFields());
        } else {
            return false;
        }

        if (move.getPossibleMoves() == 0) {
            // currentPlayer can't make a possible turn, so set the next Player
            nextPlayer();
            move.changeAllPossibleFieldsToTrue(currentPlayer.getStoneColor());
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
                    ki.setMove(currentPlayer.getStoneColor());
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
     * Get the Player BLACK and WHITE Points as int back.
     *
     * @param color BLACK or WHITE
     * @return Player Points as int
     */
    public int getPlayerPoints(Color color) {

        StoneColor stoneColor;

        if (color == Color.BLACK) {
            stoneColor = StoneColor.BLACK;
        } else {
            stoneColor = StoneColor.WHITE;
        }

        return gameBoard.getNumberOfFieldsOccupiedByStone(stoneColor);
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

    //TODO Fields Method, confused. Why here? Also, why is and not get?
    public Field[][] isFields() {
        return this.gameBoard.isFields();
    }
}

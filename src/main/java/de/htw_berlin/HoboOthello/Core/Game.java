package de.htw_berlin.HoboOthello.Core;

import de.htw_berlin.HoboOthello.KI.KI;
import de.htw_berlin.HoboOthello.Network.Network;

import java.util.List;

/**
 * Created by Steffen Exler on 30.11.16.
 */
public class Game {

    private Player playerBlack;
    private Player playerWhite;

    private Player currentPlayer;

    private GameState gameState;

    private Board gameBoard;

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

        // init gameState
        this.gameState = GameState.RUNNING;

        // set possible moves
        GameRule move = new GameRule(this.gameBoard.isFields());
        move.changeAllPossibleFieldsToTrue(currentPlayer.getColor());
        this.gameBoard.setFields(move.getFields());

        // todo remove debug code
        System.out.println(gameBoard.getBoardOverview());
    }

    /**
     * Set the game turn for the current user, return true if the turn was valid
     *
     * @param field field to set the turn in
     * @return  true == turn is valid
     *          false == turn is not valid
     */
    public boolean setTurn(Field field) {
        if (this.gameState == GameState.STOP) {
            //todo add some kind of output why it fails
            return false;
        }

        // return if the turn was successful (possible)
        GameRule move = new GameRule(this.gameBoard.isFields());

        // todo remove debug code
        System.out.printf("Field: %d:%d%n", field.getX(), field.getY());
        this.gameBoard.setFields(move.getFields());
        System.out.println(gameBoard.getBoardOverview());

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
        } else {
            Field playerTurn = currentPlayer.setMove(this.gameBoard);
            if (playerTurn != null) {
                setTurn(playerTurn);
            }

        }

        // todo remove debug code
        System.out.println(gameBoard.getBoardOverview());

        // return if this tun was successful
        return true;
    }

    /**
     * Calls a method to count the number of BLACK and WHITE Stones
     *
     * @param color BLACK or WHITE
     * @return Player Points as int
     *
     */
    public int countPlayerPoints(Color color) {

        if (color == null) {
            throw new NullPointerException("Color is null!");
        }

        return gameBoard.getNumberOfFieldsOccupiedByStone(color);
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *  Fields for Controller
     * @return alls fields in Field[][]
     */
    public Field[][] isFields() {
        return this.gameBoard.isFields();
    }

    public List<Field> iterateThroughAllFields() {
        return gameBoard.iterateThroughAllFields();
    }
}

package de.htw_berlin.HoboOthello.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.htw_berlin.HoboOthello.KI.KI;
import de.htw_berlin.HoboOthello.Network.Network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
     * @param boardSize      int 6, 8 or 10
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

        // if playerBlack is KI, do the turn
        if (currentPlayer.getClass() == KI.class) {
            setTurn(currentPlayer.setMove(gameBoard));
        }

        // if playerBlack is Network Server, do the turn
        if (currentPlayer.getClass() == Network.class) {
            setTurn(currentPlayer.setMove(gameBoard));
        }

        // todo remove debug code
        System.out.println(gameBoard.getBoardOverview());
        saveFieldToJson();
    }

    /**
     * Set the game turn for the current user, return true if the turn was valid
     *
     * @param field field to set the turn in
     * @return true == turn is valid
     * false == turn is not valid
     */
    public boolean setTurn(Field field) {
        if (this.gameState == GameState.STOP) {
            return false;
        }

        if (this.gameState == GameState.WAITING) {
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
                // todo add userfeedback
                // todo delete savegame.json
                this.gameState = GameState.STOP;
            }
        } else {
            this.gameState = GameState.WAITING;
            Field playerTurn = currentPlayer.setMove(this.gameBoard);
            if (playerTurn != null) {
                setTurn(playerTurn);
            }
            this.gameState = GameState.RUNNING;

        }

        // todo remove debug code
        System.out.println(gameBoard.getBoardOverview());
        saveFieldToJson();

        // return if this tun was successful
        return true;
    }

    /**
     * Calls a method to count the number of BLACK and WHITE Stones
     *
     * @param color BLACK or WHITE
     * @return Player Points as int
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
     * update the player Typ after load a Game from a savegame
     */
    public void updatePlayerTyp () {
        // todo update for network
        switch (this.playerBlack.getPlayerType()) {
            case KI_LEVEL1:
                this.playerBlack = new KI(this.playerBlack.getColor(), Level.LEVEL1);
                break;
            case KI_LEVEL2:
                this.playerBlack = new KI(this.playerBlack.getColor(), Level.LEVEL2);
                break;
            case KI_LEVEL3:
                this.playerBlack = new KI(this.playerBlack.getColor(), Level.LEVEL3);
                break;
        }

        switch (this.playerWhite.getPlayerType()) {
            case KI_LEVEL1:
                this.playerWhite = new KI(this.playerWhite.getColor(), Level.LEVEL1);
                break;
            case KI_LEVEL2:
                this.playerWhite = new KI(this.playerWhite.getColor(), Level.LEVEL2);
                break;
            case KI_LEVEL3:
                this.playerWhite = new KI(this.playerWhite.getColor(), Level.LEVEL3);
                break;
        }
    }

    /**
     * Debug Method, save current gameBoard.fields to a Json File
     */
    private void saveFieldToJson() {
        File file = new File("field.json");

        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(gameBoard.isFields());

        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the winner text, if there is a winner
     *
     * @return if there is no winner, the return is null
     * if the game is stop, it will create a winner output string
     */
    public String getWinner() {
        if (this.gameState == GameState.STOP) {
            if (this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.BLACK) < this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.WHITE)) {
                return String.format("White Player win with %d points!", this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.WHITE));
            } else if (this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.BLACK) > this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.WHITE)) {
                return String.format("Black Player win with %d points!", this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.BLACK));
            } else {
                return String.format("Both player got %d points!", this.gameBoard.getNumberOfFieldsOccupiedByStone(Color.WHITE));
            }
        }

        return null;
    }

    public int getBoardSize() {
        return gameBoard.getBoardSize();
    }

    /**
     * Method which iterates through all the fields on the board in this specific order:
     * Starts at [0][0], moves down the first vertical vector ([0][++]),
     * then moves one column to the right ([1][0]) and down the second  vertical vector ([1][++]), etc.
     * int i = counter on x-axis
     * int j = counter on y-axis
     *
     * @return listOfFields which is a lists of all fields, in this specific order
     */
    public List<Field> iterateThroughAllFields() {
        return gameBoard.iterateThroughAllFields();
    }
}

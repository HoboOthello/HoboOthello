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

    private Gamestate gamestate;

    private Board gameBoard;

    private Network network;

    private KI ki;

    public Game() {
    }

    public void newGame(int boardSize, PlayerTyp newPlayerBlack, PlayerTyp newPlayerWhite, GameTyp gameTyp) {
        //TODO add exception
        //init Board
        this.gameBoard = new Board(boardSize);

        // init Players
        this.playerBlack = new Player(PlayerColor.BLACK, newPlayerBlack);
        this.playerWhite = new Player(PlayerColor.WHITE, newPlayerWhite);

        this.currentPlayer = this.playerBlack;

        // init ki && network, if needed
        if (gameTyp == GameTyp.KI) {
            this.ki = new KI();
        }

        if (gameTyp == GameTyp.NETWORK ) {
            // todo steffen: need add network stuff
            this.network = new Network();
        }

        // init gamestate
        this.gamestate = Gamestate.RUNNING;

        // set all others vars to default or create new game class and make this to a constructor
    }

    /**
     * Set the game turn for the current user, return true if the turn was valid
     * @param x
     * @param y
     * @return true == trurn is valid
     *         false == turn is not valid
     */
    public boolean setTurn(int x, int y) {
        if (this.gamestate != Gamestate.STOP) {
            //todo add some kind of output why it fails
            return false;
        }

        // return if the turn was succesfull (possible)
        GameRule move = new GameRule(this.gameBoard.isFields());

        // check if this turn is allowed
        boolean moveAllowed = move.IsMoveAllowed(x, y, currentPlayer.getStoneColor());

        if (moveAllowed) {
            // set turn
            move.setMove(x, y, currentPlayer.getStoneColor());
            nextPlayer();
            move.setAllPossibleMoves(currentPlayer.getStoneColor());
            this.gameBoard.setFields(move.getFields());
        } else {
            return false;
        }

        if (move.getPossibleMoves() == 0) {
            // currentPlayer can't make a possible turn, so set the next Player
            nextPlayer();
            move.setAllPossibleMoves(currentPlayer.getStoneColor());
            this.gameBoard.setFields(move.getFields());

            if (move.getPossibleMoves() == 0) {
                // no Player can make a Possible move anymore, so end the game
                this.gamestate = Gamestate.STOP;
            }

            setKiNetworkTurn();
        }

        // return if this tun was successful
        return false;
    }

    private void setKiNetworkTurn () {
        switch (currentPlayer.getPlayerTyp()) {
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
    }


    public int getPlayerPoints(PlayerColor playerColor) {

        StoneColor stoneColor;

        if (playerColor == PlayerColor.BLACK) {
            stoneColor = StoneColor.BLACK;
        } else {
            stoneColor = StoneColor.WHITE;
        }

        return gameBoard.getNumberOfFieldsOccupiedByStone(stoneColor);
    }

    private void nextPlayer() {
        if (this.currentPlayer == playerBlack) {
            this.currentPlayer = playerWhite;
        } else {
            this.currentPlayer = playerBlack;
        }
    }
}

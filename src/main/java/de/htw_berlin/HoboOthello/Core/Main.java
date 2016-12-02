package de.htw_berlin.HoboOthello.Core;

import de.htw_berlin.HoboOthello.Controller.GameController;
import de.htw_berlin.HoboOthello.GUI.Gameview;

/**
 * Created by Steffen Exler on 16.11.16.
 */
public class Main {

    public static void main(String[] args) {

        Gameview gameview = new Gameview();

        Game game = new Game();
        game.newGame(8, PlayerTyp.DESKTOP, PlayerTyp.DESKTOP, GameTyp.DESKTOP);

        GameController gameController = new GameController(gameview, game);

        gameview.setVisible(true);

        // Board boardStandard = new Board(8);
        // Board boardSmall = new Board(6);
        // Board boardLarge = new Board(10);

        //GameRule move = new GameRule();
        //boardStandard.setFields(move.getAllPossibleMoves(StoneColor.BLACK, boardStandard.isFields()));

        // System.out.println(boardStandard.getBoardOverview());
    }
}

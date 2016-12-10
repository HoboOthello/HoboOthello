package de.htw_berlin.HoboOthello.Core;

import de.htw_berlin.HoboOthello.Controller.GameController;
import de.htw_berlin.HoboOthello.GUI.Gameview;
import de.htw_berlin.HoboOthello.KI.KI;

/**
 * Created by Steffen Exler on 16.11.16.
 */
public class Main {

    public static void main(String[] args) {
        int boardDefaultSize = 8;

        Gameview gameview = new Gameview(boardDefaultSize);

        Game game = new Game();

        game.newGame(boardDefaultSize, new Player(Color.BLACK), new Player(Color.WHITE));

        // for KI Debuging
        // game.newGame(boardDefaultSize, new Player(Color.BLACK), new KI(Color.WHITE, Level.LEVEL1));

        GameController gameController = new GameController(gameview, game);

        gameview.setVisible(true);
    }

    //TODO BUG REPORTING AND BESEITIGEN
    public void restart(int boardSize){

        Gameview gameview = new Gameview(boardSize);

        Game game = new Game();

        game.newGame(8, new Player(Color.BLACK), new Player(Color.WHITE));

        GameController gameController = new GameController(gameview, game);

        gameview.setVisible(true);


    }
}

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

        game.newGame(8, new Player(Color.BLACK), new Player(Color.WHITE));

        GameController gameController = new GameController(gameview, game);

        gameview.setVisible(true);
    }
}

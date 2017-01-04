package de.htw_berlin.HoboOthello.Core;

import de.htw_berlin.HoboOthello.Controller.GameController;
import de.htw_berlin.HoboOthello.GUI.Gameview;

/**
 * Created by Steffen Exler on 16.11.16.
 */
public class Main {

    public static void main(String[] args) {
        int boardDefaultSize = 8; // 8 for default board size

        Savegames savegames = new Savegames();
        Game game = savegames.load();

        if (game == null) {
            game = new Game();
            game.newGame(boardDefaultSize, new Player(Color.BLACK), new Player(Color.WHITE));
        }

        Gameview gameview = new Gameview(game.getBoardSize());

        GameController gameController = new GameController(gameview, game);

        gameview.setVisible(true);

    }
}

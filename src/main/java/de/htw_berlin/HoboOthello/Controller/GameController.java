package de.htw_berlin.HoboOthello.Controller;

import de.htw_berlin.HoboOthello.Core.*;
import de.htw_berlin.HoboOthello.GUI.Gameview;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by laura on 24.11.16.
 */
public class GameController {

    private Gameview gameview;
    private Game theGame;
    private Board theBoard;
    private Main theMain;

    public GameController(Gameview theView, Game theGame) {

        this.gameview = theView;
        this.theGame = theGame;

        theView.addBoardListener(new BoardListener());
        theView.addMenuListener(new MenuListener());

        updateGameBoard();
    }


    /**
     * inside class
     * BoardListener method to check which Button has been clicked
     */
    class BoardListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                for (int x = 0; x < gameview.getFieldViewLength(); x++) {
                    for (int y = 0; y < gameview.getFieldViewLength(); y++) {
                        if (e.getSource() == gameview.getFieldView(x, y)) {
                            theGame.setTurn(new Field(x, y));
                            updateGameBoard();
                        }
                    }
                }

            } catch (NumberFormatException ex) {

                gameview.displayErrorMessage("Illegal Move!");

            }
        }

    }

    //TODO this class is unused may be deleted
    class MenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //TODO BJOERN write a line that starts a new game. the restart() method does throw a nullPointer
            try {

                /*
                see
                toogleMenu = new JMenuItem[5];
                toogleMenu[0] = six;
                toogleMenu[1] = eight;
                toogleMenu[2] = ten;
                toogleMenu[3] = closeGame;
                toogleMenu[4] = aboutItem;
                 */

                if (e.getSource() == gameview.getToogleMenu(0)) {
                    theMain.restart(6);

                } else if (e.getSource() == gameview.getToogleMenu(1)) {
                    theMain.restart(8);

                } else if (e.getSource() == gameview.getToogleMenu(2)) {
                    theMain.restart(10);

                } else if (e.getSource() == gameview.getToogleMenu(3)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You're a true Hobo!",
                            "GoodBye!",
                            JOptionPane.INFORMATION_MESSAGE);
                    gameview.setVisible(false);

                } else if (e.getSource() == gameview.getToogleMenu(4)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "HoboOthello created by: Laura, Steffen and Bjoern",
                            "ABOUT",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (NumberFormatException ex) {

                gameview.displayErrorMessage("Ups! Something is wrong?!");

            }
        }

    }

    //TODO this class is unused may be deleted
    class ExitListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

            try {

                gameview.displayErrorMessage("You're a true Hobo!");
                gameview.setVisible(false);
            } catch (NumberFormatException ex) {

                gameview.displayErrorMessage("Ups! Something is wrong?!");

            }
        }

    }

    public void updateGameBoard () {
        // todo may need refactor

        // update fields
        for (Field field : theGame.iterateThroughAllFields()) {
            gameview.updateBoardFields(field);
        }

        // update player points
        gameview.updateBoardPlayerPoints(Color.BLACK, theGame.countPlayerPoints(Color.BLACK));
        gameview.updateBoardPlayerPoints(Color.WHITE, theGame.countPlayerPoints(Color.WHITE));

        // show which player is
        System.out.println(theGame.getCurrentPlayer().getColor());
    }

}







package de.htw_berlin.HoboOthello.Controller;

import de.htw_berlin.HoboOthello.Core.*;
import de.htw_berlin.HoboOthello.GUI.Gameview;
import de.htw_berlin.HoboOthello.KI.KI;
import de.htw_berlin.HoboOthello.Network.Network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by laura on 24.11.16.
 */
public class GameController {

    private Gameview gameview;
    private Game theGame;


    public GameController(Gameview theView, Game theGame) {
        newGame(theView, theGame);
    }

    public void newGame(Gameview theView, Game theGame) {
        this.gameview = theView;
        this.theGame = theGame;

        theView.addBoardListener(new BoardListener());
        theView.addMenuListener(new MenuListener());
        theView.addHintListener(new HintListener());

        gameview.setPlayerTyp(theGame.getPlayerBlack(), theGame.getPlayerWhite());
        updateGameBoard();
    }


    /**
     * inside classes
     * BoardListener method to check which Button has been clicked
     * MenuListener method to check which MenuItem was clicked
     * HintListener method to set off a hint for the next best move (random move is shown)
     */
    class BoardListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                /* TODO: complexity of n**n which equals with the nested komplexity of Board.iterateThroughAllFields a
                    total complexity of n**n * m**m
                */
                for (int x = 0; x < gameview.getFieldViewLength(); x++) {
                    for (int y = 0; y < gameview.getFieldViewLength(); y++) {

                        if (e.getSource() == gameview.getFieldView(x, y)) {

                            System.out.println(gameview.getFieldView(x, y).getWidth());
                            theGame.setTurn(new Field(x, y));
                            updateGameBoard();

                            // todo save for a network game?
                            Savegames savegames = new Savegames();
                            savegames.save(theGame);

                            // todo hobomode
                            /*
                            theGame.activateHobeMode();
                            theGame.getLastHobeModeType();
                            updateGameBoard();
                            */

                            // check if the game is ended
                            String gameWinner = theGame.getWinner();
                            if (gameWinner != null) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        gameWinner,
                                        "Game End",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                gameview.displayErrorMessage("Illegal Move!");
            }
        }
    }
    class MenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //TODO BJOERN write a line that starts a new game. the restart() method does throw a nullPointer
            try {
                // todo refactor? look not quite efficient
                /*
                see
                toogleMenu[0] = newGame;
                toogleMenu[1] = closeGame;
                toogleMenu[2] = aboutItem;

                 */

                if (e.getSource() == gameview.getToogleMenu(0)) {
                    // todo have fun björn to redesign the showConfirmDialog :)
                    JPanel panel = new JPanel();

                    // board size panel
                    panel.add(new JLabel("Game Board Size:"));
                    DefaultComboBoxModel boardSize = new DefaultComboBoxModel();
                    boardSize.addElement("6");
                    boardSize.addElement("8");
                    boardSize.addElement("10");
                    JComboBox comboBoxBoardSize = new JComboBox(boardSize);
                    panel.add(comboBoxBoardSize);

                    // player Black
                    panel.add(new JLabel("Black Player:"));
                    DefaultComboBoxModel playerBlack = new DefaultComboBoxModel();
                    playerBlack.addElement("Human");
                    playerBlack.addElement("Computer Level 1");
                    playerBlack.addElement("Computer Level 2");
                    playerBlack.addElement("Computer Level 3");
                    playerBlack.addElement("Network Server");
                    JComboBox comboBoxPlayerBlack = new JComboBox(playerBlack);
                    panel.add(comboBoxPlayerBlack);

                    // player White
                    panel.add(new JLabel("White Player:"));
                    DefaultComboBoxModel playerWhite = new DefaultComboBoxModel();
                    playerWhite.addElement("Human");
                    playerWhite.addElement("Computer Level 1");
                    playerWhite.addElement("Computer Level 2");
                    playerWhite.addElement("Computer Level 3");
                    playerWhite.addElement("Network Client");
                    JComboBox comboBoxPlayerWhite = new JComboBox(playerWhite);
                    panel.add(comboBoxPlayerWhite);

                    // Server IP
                    panel.add(new JLabel("Server IP:"));
                    JTextField serverIP = new JTextField();
                    serverIP.setText("localhost");
                    serverIP.setColumns(25);
                    panel.add(serverIP);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        Player newBlackPlayer = new Player(Color.BLACK);
                        Player newWhitePlayer = new Player(Color.WHITE);
                        int newBoardSize = Integer.parseInt(boardSize.getSelectedItem().toString());

                        switch (comboBoxPlayerBlack.getSelectedIndex()) {
                            case 0:
                                newBlackPlayer = new Player(Color.BLACK);
                                break;
                            case 1:
                                newBlackPlayer = new KI(Color.BLACK, Level.LEVEL1);
                                break;
                            case 2:
                                newBlackPlayer = new KI(Color.BLACK, Level.LEVEL2);
                                break;
                            case 3:
                                newBlackPlayer = new KI(Color.BLACK, Level.LEVEL3);
                                break;
                            case 4:
                                newBlackPlayer = new Network(Color.BLACK);
                                break;
                        }

                        switch (comboBoxPlayerWhite.getSelectedIndex()) {
                            case 0:
                                newWhitePlayer = new Player(Color.WHITE);
                                break;
                            case 1:
                                newWhitePlayer = new KI(Color.WHITE, Level.LEVEL1);
                                break;
                            case 2:
                                newWhitePlayer = new KI(Color.WHITE, Level.LEVEL2);
                                break;
                            case 3:
                                newWhitePlayer = new KI(Color.WHITE, Level.LEVEL3);
                                break;
                            case 4:
                                newWhitePlayer = new Network(Color.WHITE, serverIP.getText());
                                break;
                        }

                        if (newBlackPlayer.getClass() != Player.class & newWhitePlayer.getClass() != Player.class) {
                            throw new IllegalArgumentException("Min one Human Player is required!");
                        }

                        // destroy the current JFrame
                        gameview.dispose();

                        // new game
                        Game game = new Game();
                        game.newGame(newBoardSize, newBlackPlayer, newWhitePlayer);
                        Gameview newGameview = new Gameview(newBoardSize);
                        newGame(newGameview, game);

                        game.firstRound();
                    }

                } else if (e.getSource() == gameview.getToogleMenu(1)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You're a true Hobo!",
                            "GoodBye!",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);

                } else if (e.getSource() == gameview.getToogleMenu(2)) {
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
    class HintListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

             if (e.getSource() == gameview.getShowHint())
                updateGameBoard();
                Field field = theGame.showHint();
                gameview.showHint(field);

            } catch (NumberFormatException ex) {

                gameview.displayErrorMessage("Ups! Something is wrong?!");

            }
        }
    }

    /**
     * Update the Gameview with the current Board Infos
     *
     */
    public void updateGameBoard() {
        for (Field field : theGame.iterateThroughAllFields()) {
            gameview.getFieldView(field.getX(),field.getY()).setIcon(null);
            gameview.updateBoardFields(field);
        }

        // update player points
        gameview.updateBoardPlayerPoints(Color.BLACK, theGame.countPlayerPoints(Color.BLACK));
        gameview.updateBoardPlayerPoints(Color.WHITE, theGame.countPlayerPoints(Color.WHITE));

        // show which player is
        gameview.updateCurrentPlayer(theGame.getCurrentPlayer().getColor().toString());
    }

}







package de.htw_berlin.HoboOthello.GUI;

/**
 * Gameview class
 * methods
 * <p>
 * createGameView()
 * addButtonListener()
 * displayErrorMessage()
 * setBoard() - Groesse festlegen
 */


import de.htw_berlin.HoboOthello.Core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by laura on 24.11.16.
 */
public class Gameview extends JFrame {


    private static final long serialVersionUID = 1L;

    private JLabel whiteScore = new JLabel("WHITE");
    private JLabel blackScore = new JLabel("BLACK");
    private Color backgroundColor = new Color(0, 150, 0);
    private JButton startButton = new JButton();
    private JButton[][] fieldView;

    private JMenuItem[] toogleMenu;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem closeGame;
    private JMenuItem newGame;
    private JMenu aboutMenu;
    private JMenuItem aboutItem;
    //todo showHint
    private JMenu hintMenu;
    private JMenuItem hintItem;

    /**
     * the method to set up the board
     *
     * @param boardSize TODO in the Controller set Boardsize or add int boardSize to the Constructor Gameview()
     */
    //TODO create instance of method in controller
    public void setBoard(int boardSize) {

        fieldView = new JButton[boardSize][boardSize];

    }


    /**
     * Constructor to create the gui
     */

    public Gameview(int boardSize) {

        /*
         * sets up the main frame of the game
         */


        this.setTitle("HoboOthello");
        this.setLocation(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        /*
         * MenuBar and all components
         */

        gameMenu = new JMenu("Datei");
        closeGame = new JMenuItem("Exit");
        newGame = new JMenuItem("New Game");
        aboutMenu = new JMenu("About");
        aboutItem = new JMenuItem("About");
        //todo showHint
        hintMenu = new JMenu("Hint");
        hintItem = new JMenuItem("Hint");

        gameMenu.add(newGame);
        gameMenu.add(closeGame);
        aboutMenu.add(aboutItem);
        //todo showHint
        hintMenu.add(hintItem);

        //adding all JMenuItems to an array. Changes here need to be check in the GameController as well
        //todo showHint
        toogleMenu = new JMenuItem[4];
        toogleMenu[0] = newGame;
        toogleMenu[1] = closeGame;
        toogleMenu[2] = aboutItem;
        //todo showHint
        toogleMenu[3] = hintItem;


        /*
         * create a board. board is the center panel
         */
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardPanel.setBorder(BorderFactory.createEtchedBorder());


		/*
         * create Fields out of the Field class and fill the board with fields
		 * field ist initialized with magic numbers but that will later be changed to a variable 'boardsize'
		 */
        //TODO add the method setBoard() and get the getBoardSize()
        fieldView = new JButton[boardSize][boardSize];

        for (int x = 0; x < fieldView.length; x++) {
            for (int y = 0; y < fieldView.length; y++) {

                fieldView[y][x] = new JButton();
                fieldView[y][x].setBackground(backgroundColor);
                fieldView[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                boardPanel.add(fieldView[y][x]).setVisible(true);

            }
        }


        /*
         * the action panel which holds the start button
         */
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createEtchedBorder());
        startButton.setText("Start");
        startButton.setPreferredSize(new Dimension(80, 30));
        actionPanel.add(startButton);


        /*
         * the score panel to display the actual score
         */
        JPanel scorePanel = new JPanel(new FlowLayout());
        whiteScore.setBorder(BorderFactory.createEtchedBorder());
        blackScore.setBorder(BorderFactory.createEtchedBorder());
        scorePanel.add(whiteScore);
        scorePanel.add(blackScore);


        /*
         * filler spaces for the right and left hand side
         */
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();


        /*
         * BorderLayout dimensions of the Center Panel
         */
        actionPanel.setPreferredSize(new Dimension(0, 100));
        scorePanel.setPreferredSize(new Dimension(0, 50));
        westPanel.setPreferredSize(new Dimension(75, 0));
        eastPanel.setPreferredSize(new Dimension(75, 0));


        /*
         * adding all elements to the main frame
         */

        this.getContentPane().add(scorePanel, BorderLayout.NORTH);
        this.getContentPane().add(boardPanel, BorderLayout.CENTER);
        this.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        this.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.getContentPane().add(westPanel, BorderLayout.WEST);

        //adding the menu bar to the main frame
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(gameMenu);
        this.getJMenuBar().add(aboutMenu);
        //todo showHint
        this.getJMenuBar().add(hintMenu);

        this.setVisible(true);

    }


    /*
     * method to add ActionListener to the board
     */
    public void addBoardListener(ActionListener listenerForFieldButton) {

        for (int row = 0; row < fieldView.length; row++) {
            for (int column = 0; column < fieldView.length; column++) {
                fieldView[row][column].addActionListener(listenerForFieldButton);
            }
        }
    }

    public void addMenuListener(ActionListener listenerForMenuClick) {
        for (int i = 0; i < toogleMenu.length; i++) {
            toogleMenu[i].addActionListener(listenerForMenuClick);
        }
    }


    /*
     * Getter for the board to check which Button was clicked
     * and to check the length of the board (6, 8, 10)
     * used in GameController -> inner Class BoardListener
     */
    public JButton getFieldView(int x, int y) {
        return fieldView[x][y];
    }

    public int getFieldViewLength() {
        return fieldView.length;
    }

    public JMenuItem getToogleMenu(int nbr) {
        return toogleMenu[nbr];
    }


    /*
     * error message to display errors
     * e.g. wrong button clicked = illegal move
     */
    public void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(this, errorMessage);

    }

    /**
     * update a Field in GameView
     *
     * @param field The field wich should be update
     */
    public void updateBoardFields(Field field) {
        Color color = backgroundColor;

        if (field.isOccupiedByStone()) {
            switch (field.getStone().getColor()) {
                case BLACK:
                    color = Color.BLACK;
                    break;
                case WHITE:
                    color = Color.WHITE;
                    break;
            }
        }

        if (field.isPossibleMove()) {
            color = Color.blue;
        }

        fieldView[field.getX()][field.getY()].setBackground(color);
    }

    /**
     * update the Player Scores in GameView
     *
     * @param color  the Player Color BLACK or WHITE
     * @param points the Player current Points
     */
    public void updateBoardPlayerPoints(de.htw_berlin.HoboOthello.Core.Color color, int points) {
        if (color == de.htw_berlin.HoboOthello.Core.Color.BLACK) {
            this.blackScore.setText("BLACK " + points);
        } else {
            this.whiteScore.setText("WHITE " + points);
        }
    }

    /**
     * Show the best possible move for the current player
     * @param field
     */
    //todo showHint
    public void showHint(Field field) {
        fieldView[field.getX()][field.getY()].setBackground(Color.gray);
    }

}

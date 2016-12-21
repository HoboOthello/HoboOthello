package de.htw_berlin.HoboOthello.GUI;

import de.htw_berlin.HoboOthello.Core.Field;
import de.htw_berlin.HoboOthello.Core.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by laura on 24.11.16.
 */
public class Gameview extends JFrame {


    private static final long serialVersionUID = 1L;

    private String blackPlayerTyp = "BLACK";
    private String whitePlayerTyp = "WHITE";

    private JLabel whiteScore = new JLabel(whitePlayerTyp);
    private JLabel blackScore = new JLabel(blackPlayerTyp);
    private JLabel whosTurn   = new JLabel();

    private Color backgroundColor;

    private JButton[][] fieldView;
    private JButton showHint;

    private JMenuItem[] toogleMenu;
    private JMenu gameMenu;
    private JMenuItem closeGame;
    private JMenuItem newGame;
    private JMenu aboutMenu;
    private JMenuItem aboutItem;
    private JMenu hintMenu;
    private JMenuItem hintItem;

    Font font18 = new Font("Courier new", Font.BOLD, 18);
    Font font14 = new Font("Courier new", Font.BOLD, 14);

    //Create the Stones
    private ImageIcon white;
    private ImageIcon black;
    private ImageIcon grey;
    private ImageIcon hint;





    /**
     * Constructor to create the gui
     */

    public Gameview(int boardSize) {

        /*
         * sets up the main frame of the game
         */
        this.setTitle("HoboOthello");
        //this.setLocationRelativeTo(null); //Centers the Frame with top left corner in the middle of the screen
        this.setLocation(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.backgroundColor = new Color(0,150,0);


        /*
         * MenuBar and menu components
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

        toogleMenu = new JMenuItem[4];
        toogleMenu[0] = newGame;
        toogleMenu[1] = closeGame;
        toogleMenu[2] = aboutItem;
        //todo showHint
        toogleMenu[3] = hintItem;


        /*
         * create a board. board is the center panel. adding buttons to the board
         */
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardPanel.setBorder(BorderFactory.createEtchedBorder());
        fieldView = new JButton[boardSize][boardSize];

        for (int x = 0; x < fieldView.length; x++) {
            for (int y = 0; y < fieldView.length; y++) {

                fieldView[x][y] = new JButton();
                fieldView[x][y].setBackground(backgroundColor);
                fieldView[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                boardPanel.add(fieldView[x][y]).setVisible(true);

                // ---> to disable green background field <---
                    // fieldView[x][y].setBorder(null);
                    // fieldView[x][y].setBackground(null);
            }
        }


        /*
         * the action panel which holds the hint button
         */
        JPanel actionPanel = new JPanel();
        //actionPanel.setBorder(BorderFactory.createEtchedBorder()); // a frame around the panel
        showHint = new JButton("hint");
        actionPanel.add(showHint);

        /*
         * the score panel to display the actual score
         */
        //TODO BJOERN switch to JSeperator
        JLabel line = new JLabel(" | ");
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        scorePanel.add(whiteScore, c);
        c.gridx = 2;
        c.gridy = 0;
        scorePanel.add(blackScore, c);
        whosTurn.setPreferredSize(new Dimension(220, 30));
        whosTurn.setFont(font18);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        scorePanel.add(whosTurn, c);
        //  whiteScore.setBorder(BorderFactory.createEtchedBorder());
        //  blackScore.setBorder(BorderFactory.createEtchedBorder());
        //  whosTurn.setBorder(BorderFactory.createEtchedBorder());



        /*
         * filler spaces for the right and left hand side
         */
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        /*
         * BorderLayout dimensions of the Center Panel
         * DO NOT CHANGE THIS
         */
        actionPanel.setPreferredSize( new Dimension(0, 58) );
        scorePanel.setPreferredSize( new Dimension(0, 78) );
        westPanel.setPreferredSize( new Dimension(92, 0) );
        eastPanel.setPreferredSize( new Dimension(92, 0) );

        /*
         * adding all elements to the main frame
         */
        this.getContentPane().add(scorePanel, BorderLayout.NORTH);
        this.getContentPane().add(boardPanel, BorderLayout.CENTER);
        this.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        this.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.getContentPane().add(westPanel, BorderLayout.WEST);

        /*
         * adding the menu bar to the main frame
         */
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(gameMenu);
        this.getJMenuBar().add(aboutMenu);
        //todo showHint
        this.getJMenuBar().add(hintMenu);


        /*
        grey  = new ImageIcon(this.getClass().getResource("src/images/greybutton.png"));
        white = new ImageIcon(this.getClass().getResource("src/images/whitebutton.png"));
        black = new ImageIcon(this.getClass().getResource("src/images/blackbutton.png"));
        hint  = new ImageIcon(this.getClass().getResource("src/images/hint.png"));

        Image whiteImage = white.getImage();
        Image newWhite = whiteImage.getScaledInstance( (int) (fieldView[0][0].getWidth()* 0.88), (int) (fieldView[0][0].getHeight()* 0.88), java.awt.Image.SCALE_SMOOTH );
        white = new ImageIcon(newWhite);

        Image blackImage = black.getImage();
        Image newBlack = blackImage.getScaledInstance( (int) (fieldView[0][0].getWidth()* 0.88), (int) (fieldView[0][0].getHeight()* 0.88), java.awt.Image.SCALE_SMOOTH );
        black = new ImageIcon(newBlack);

        Image greyImage = grey.getImage();
        Image newGrey = greyImage.getScaledInstance(
                (int) ( fieldView[0][0].getWidth()* 0.68), (int) (fieldView[0][0].getHeight()* 0.68), java.awt.Image.SCALE_SMOOTH );
        grey = new ImageIcon(newGrey);

        Image hintImage = hint.getImage();
        Image newHint = hintImage.getScaledInstance( (int) (fieldView[0][0].getWidth()* 0.68), (int) (fieldView[0][0].getHeight()* 0.68), java.awt.Image.SCALE_SMOOTH );
        hint = new ImageIcon(newHint);
        */
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
     * @param field The field which should be update
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
     * ---> WhitePlayer Points | Points BlackPlayer <---
     */
    public void updateBoardPlayerPoints(de.htw_berlin.HoboOthello.Core.Color color, int points) {
        if (color == de.htw_berlin.HoboOthello.Core.Color.BLACK) {
            this.blackScore.setText("|         " + points + "     :" + this.blackPlayerTyp + "   ");
        } else {
            this.whiteScore.setText("   " + this.whitePlayerTyp + ":     " + points + "   ");
        }
    }

    /**
     * Show the current Player Name (White or Black)
     * @param currentPlayerName String which can be Black or White
     */
    public void updateCurrentPlayer(String currentPlayerName) {
        //todo change button to label & also refactor
        whosTurn.setText("Players turn: " + currentPlayerName);
    }

    public void setPlayerTyp(Player blackPlayer, Player whitePlayer) {
        switch (blackPlayer.getPlayerType()) {
            case DESKTOP:
                this.blackPlayerTyp = "HUMAN";
                break;
            case KI_LEVEL1:
                this.blackPlayerTyp = "KI 1";
                break;
            case KI_LEVEL2:
                this.blackPlayerTyp = "KI 2";
                break;
            case KI_LEVEL3:
                this.blackPlayerTyp = "KI 3";
                break;
            case NETWORK_SERVER:
                this.blackPlayerTyp = "NETWORK SERVER";
                break;
        }

        switch (whitePlayer.getPlayerType()) {
            case DESKTOP:
                this.whitePlayerTyp = "HUMAN";
                break;
            case KI_LEVEL1:
                this.whitePlayerTyp = "KI 1";
                break;
            case KI_LEVEL2:
                this.whitePlayerTyp = "KI 2";
                break;
            case KI_LEVEL3:
                this.whitePlayerTyp = "KI 3";
                break;
            case NETWORK_CLIENT:
                this.whitePlayerTyp = "NETWORK CLIENT";
                break;
        }
    }

    /**
     * Show the best possible move for the current player
     * @param field
     */
    //todo showHint
    public void showHint(Field field) {
        fieldView[field.getX()][field.getY()].setBackground(Color.MAGENTA);
    }



    /**
     *
     * @param stone can be 0, 1, 2, 3
     *              whereas 0=white, 1=black, 2=grey, 3=hint
     * @param x, @param x and y: the row and column designators
     */
    public void changeStone(int stone, int x, int y)
    {
        if (stone == 0)
        {
            fieldView[x][y].setIcon(white);

        } else if (stone == 1)
        {
            fieldView[x][y].setIcon(black);

        } else if (stone == 2)
        {
            fieldView[x][y].setIcon(grey);

        } else if (stone == 3)
        {
            fieldView[x][y].setIcon(hint);

        }

    }



}

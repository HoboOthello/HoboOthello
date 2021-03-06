package de.htw_berlin.HoboOthello.GUI;

import de.htw_berlin.HoboOthello.Core.Field;
import de.htw_berlin.HoboOthello.Core.Player;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.InputStream;

/**
 * Created by laura on 24.11.16.
 */
public class Gameview extends JFrame {

    private static final long serialVersionUID = 1L;

    private String blackPlayerTyp = "BLACK";
    private String whitePlayerTyp = "WHITE";

    private JLabel whiteScore = new JLabel(whitePlayerTyp);
    private JLabel blackScore = new JLabel(blackPlayerTyp);
    private JLabel whosTurn = new JLabel();

    private JPanel scorePanel;
    private JPanel actionPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JPanel boardPanel;

    private Color backgroundColor;

    private JButton[][] fieldView;
    private JButton showHint;
    private JButton goHobo;

    private JMenuItem[] toogleMenu;
    private JMenu gameMenu;
    private JMenuItem closeGame;
    private JMenuItem newGame;
    private JMenu aboutMenu;
    private JMenuItem aboutItem;

    Font font18 = new Font("Courier new", Font.BOLD, 18);
    Font font14 = new Font("Courier new", Font.BOLD, 14);

    //Create the Stones
    private ImageIcon white;
    private ImageIcon black;
    private ImageIcon grey;
    private ImageIcon hint;
    private int var;
    private int varSmall;

    /*
     * values to scale the center panel if window size changes
    */
    int trueWidth;
    int trueHeight;
    int newWidth;
    int newHeight;

    /**
     * Constructor to create the gui
     */

    public Gameview(int boardSize) {

        /*
         * sets up the main frame of the game
         */
        this.setTitle("HoboOthello");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.backgroundColor = new Color(0, 150, 0);

        /*
         * MenuBar and menu components
         */
        gameMenu = new JMenu("Datei");
        closeGame = new JMenuItem("Exit");
        newGame = new JMenuItem("New Game");
        aboutMenu = new JMenu("About");
        aboutItem = new JMenuItem("About");

        gameMenu.add(newGame);
        gameMenu.add(closeGame);
        aboutMenu.add(aboutItem);

        toogleMenu = new JMenuItem[3];
        toogleMenu[0] = newGame;
        toogleMenu[1] = closeGame;
        toogleMenu[2] = aboutItem;


        /*
         * create a board. board is the center panel. adding buttons to the board
         */
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        //boardPanel.setBorder(BorderFactory.createEtchedBorder());
        fieldView = new JButton[boardSize][boardSize];

        for (int x = 0; x < fieldView.length; x++) {
            for (int y = 0; y < fieldView.length; y++) {

                fieldView[x][y] = new JButton();
                fieldView[x][y].setBackground(backgroundColor);
                //fieldView[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                boardPanel.add(fieldView[x][y]).setVisible(true);

                // ---> to disable green background field <---
                //fieldView[x][y].setBorder(null);
                //fieldView[x][y].setBackground(null);
            }
        }


        /*
         * the action panel which holds the hint button
         */
        actionPanel = new JPanel();
        //actionPanel.setBorder(BorderFactory.createEtchedBorder()); // a frame around the panel
        showHint = new JButton(" Hint? ");
        goHobo = new JButton  ("Go Hobo");
        actionPanel.add(showHint);
        actionPanel.add(goHobo);

        /*
         * the score panel to display the actual score
         */
        scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        scorePanel.add(whiteScore, c);
        c.gridx = 2;
        c.gridy = 1;
        scorePanel.add(blackScore, c);
        whosTurn.setPreferredSize(new Dimension(220, 30));
        whosTurn.setFont(font18);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        scorePanel.add(whosTurn, c);


        /*
         * filler spaces for the right and left hand side
         */
        eastPanel = new JPanel();
        westPanel = new JPanel();

        /*
         * BorderLayout dimensions of the Center Panel
         * DO NOT CHANGE THIS
         */
        actionPanel.setPreferredSize(new Dimension(0, 58));
        scorePanel.setPreferredSize(new Dimension(0, 78));
        westPanel.setPreferredSize(new Dimension(92, 0));
        eastPanel.setPreferredSize(new Dimension(92, 0));

        actionPanel.setBackground(new Color(120, 160, 160));
        scorePanel.setBackground(new Color(120, 160, 160));
        westPanel.setBackground(new Color(120, 160, 160));
        eastPanel.setBackground(new Color(120, 160, 160));
        boardPanel.setBackground(new Color(120, 160, 160));

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

        /*
         * initialise all stones
         */
        grey = new ImageIcon(this.getClass().getResource("/greybutton.png"));
        white = new ImageIcon(this.getClass().getResource("/whitebutton.png"));
        black = new ImageIcon(this.getClass().getResource("/blackbutton.png"));
        hint = new ImageIcon(this.getClass().getResource("/hint.png"));

        setupScaleFactors(fieldView.length);
        setupBlackImageIcon(var);
        setupGreyImageIcon(var);
        setUpWhiteImageIcon(var);

    }


    /**
     * all methods to add ActionListeners to the board
     */
    public void addBoardListener(ActionListener listenerForFieldButton) {

        for (int x = 0; x < fieldView.length; x++) {
            for (int y = 0; y < fieldView.length; y++) {
                fieldView[x][y].addActionListener(listenerForFieldButton);
            }
        }
    }

    public void addMenuListener(ActionListener listenerForMenuClick) {
        for (int i = 0; i < toogleMenu.length; i++) {
            toogleMenu[i].addActionListener(listenerForMenuClick);
        }
    }

    public void addHintListener(ActionListener listenerForHintClick) {
        this.showHint.addActionListener(listenerForHintClick);
    }

    public void addSizeListener(ComponentListener listenerForReSize) {
        this.getContentPane().addComponentListener(listenerForReSize);
    }

    public void addHoboListener(ActionListener listenerForGoHobo) {
        this.goHobo.addActionListener(listenerForGoHobo);
    }


    /**
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

    public JButton getShowHint() {
        return showHint;
    }

    public JButton getGoHobo() {
        return goHobo;
    }


    /**
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
     */ //TODO This method calls non GUI Parameters : remove from GUI
    public void updateBoardFields(Field field) {
        Color color = backgroundColor;

        if (field.isOccupiedByStone()) {
            switch (field.getStone().getColor()) {
                case BLACK:
                    changeStone(1, field.getX(), field.getY());
                    //color = Color.BLACK;
                    break;
                case WHITE:
                    changeStone(0, field.getX(), field.getY());
                    //color = Color.WHITE;
                    break;
            }
        }

        if (field.isPossibleMove()) {
            changeStone(2, field.getX(), field.getY());
            //color = Color.blue;
        }

        //fieldView[field.getX()][field.getY()].setBackground(color);
    }

    /**
     * update the Player Scores in GameView
     *
     * @param color  the Player Color BLACK or WHITE
     * @param points the Player current Points
     *               ---> WhitePlayer Points | Points BlackPlayer <---
     */
    public void updateBoardPlayerPoints(de.htw_berlin.HoboOthello.Core.Color color, int points) {
        if (color == de.htw_berlin.HoboOthello.Core.Color.BLACK) {
            this.blackScore.setText(this.blackPlayerTyp + "   " + points);
            this.blackScore.setFont(font14);
            this.blackScore.setForeground(Color.BLACK);
        } else {
            this.whiteScore.setText(this.whitePlayerTyp + "   " + points);
            this.whiteScore.setFont(font14);
            this.whiteScore.setForeground(Color.WHITE);
        }
    }

    /**
     * Show the current Player Name (White or Black)
     *
     * @param currentPlayerName String which can be Black or White
     */
    public void updateCurrentPlayer(String currentPlayerName) {

        // TODO: this is an check for equality of the objectid
        if (currentPlayerName == "WHITE") {
            this.whosTurn.setText("Players turn: " + currentPlayerName);
            this.whosTurn.setForeground(Color.WHITE);

        } else
            //(currentPlayerName == "Black")
            {
            this.whosTurn.setText("Players turn: " + currentPlayerName);
            this.whosTurn.setForeground(Color.BLACK);

        }
        /*
        else {
            this.whosTurn.setText("Players turn: " + currentPlayerName);
            this.whosTurn.setForeground(Color.BLACK);

        }
        */
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
     *
     * @param field
     */

    public void showHint(Field field) {
        this.fieldView[field.getX()][field.getY()].setIcon(null);
        this.changeStone(3, field.getX(), field.getY());
        //fieldView[field.getX()][field.getY()].setBackground(Color.MAGENTA);
    }

    /**
     * @param stone can be 0, 1, 2, 3
     *              whereas 0=white, 1=black, 2=grey, 3=hint
     * @param x:    the row designator
     * @param y:    the column designator
     */
    public void changeStone(int stone, int x, int y) {
        if (stone == 0) {
            playPloppSound();
            fieldView[x][y].setIcon(white);

        } else if (stone == 1) {
            playPloppSound();
            fieldView[x][y].setIcon(black);

        } else if (stone == 2) {
            playPloppSound();
            fieldView[x][y].setIcon(grey);

        } else if (stone == 3) {
            Image hintImage = hint.getImage();
            Image newHint = hintImage.getScaledInstance(varSmall, varSmall, java.awt.Image.SCALE_SMOOTH);
            hint = new ImageIcon(newHint);
            playPloppSound();
            fieldView[x][y].setIcon(hint);

        }

    }

    private void setupScaleFactors(int scale) {
        var = (int) (60 * 0.88);
        varSmall = (int) (60 * 0.48);
        switch (scale) {
            case 6: {
                var = (int) (100 * 0.88);
                varSmall = (int) (100 * 0.48);
                break;
            }
            case 8: {
                varSmall = (int) (75 * 0.48);
                var = (int) (75 * 0.88);
                break;
            }
            case 10: {
                varSmall = (int) (60 * 0.48);
                var = (int) (60 * 0.88);
                break;
            }
            default:
                System.out.println("Ups! Something went wrong");
                break;
        }
    }

    private void setupGreyImageIcon(int varSmall) {
        Image greyImage = grey.getImage();
        Image newGrey = greyImage.getScaledInstance(varSmall, varSmall, Image.SCALE_SMOOTH);
        grey = new ImageIcon(newGrey);
    }

    private void setupBlackImageIcon(int var) {
        Image blackImage = black.getImage();
        Image newBlack = blackImage.getScaledInstance(var, var, Image.SCALE_SMOOTH);
        black = new ImageIcon(newBlack);
    }

    private void setUpWhiteImageIcon(int var) {
        Image whiteImage = white.getImage();
        Image newWhite = whiteImage.getScaledInstance(var, var, Image.SCALE_SMOOTH);
        white = new ImageIcon(newWhite);
    }


    /*
     * adapt values width and height to changes of the size of the frame
     */
    public void getTrueSize(int width, int height) {
        this.trueHeight = height;
        this.trueWidth = width;
        int frameSize;          //extra variable for calculation issues

        if (this.trueHeight >= this.trueWidth) {
            frameSize = (int) ((double) this.trueWidth * 0.885);
        } else {
            frameSize = (int) ((double) this.trueHeight * 0.83);        // the int value of the pixels used for the

            this.newWidth = (this.trueWidth - frameSize) / 2;
            this.newHeight = (this.trueHeight - frameSize) / 2;
        }
    }


    /*
     * to resize if the frame size is being changed
     */
    public void reSize(){
        this.getTrueSize(this.getContentPane().getWidth(), this.getContentPane().getHeight());
        this.actionPanel.setPreferredSize(new Dimension(trueWidth,newHeight));
        this.scorePanel.setPreferredSize(new Dimension(trueWidth,newHeight));
        this.eastPanel.setPreferredSize(new Dimension(newWidth,trueHeight));
        this.westPanel.setPreferredSize(new Dimension(newWidth,trueHeight));

    }

    public void playHoboGiggle() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("Giggle.wav");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e){
            //...to be filled
        }
    }

    public void playExitSound() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("ExitCrash.wav");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e){
            //...to be filled
        }
    }

    public void playPloppSound() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("Drop.wav");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e){
            //...to be filled
        }
    }
}

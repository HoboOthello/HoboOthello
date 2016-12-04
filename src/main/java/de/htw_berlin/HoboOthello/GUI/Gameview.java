package de.htw_berlin.HoboOthello.GUI;

/**
 * Gameview class
 * methods
 *
 * createGameView()
 * addButtonListener()
 * displayErrorMessage()
 * setBoard() - Groesse festlegen
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by laura on 24.11.16.
 */
public class Gameview extends JFrame {


    public Gameview(){
        super();
    }

    private JLabel whiteScore = new JLabel("WHITE");
    private JLabel blackScore = new JLabel("BLACK");
    private Color backgroundColor = new Color(0, 150, 0);
    private JButton startButton = new JButton();
    private JButton fieldView[][];


    /**
     * the method to set up the board
     * @param boardSize
     *
     */
    //TODO create instance of method in controller
    public void setBoard(int boardSize){

        fieldView = new JButton[boardSize][boardSize];

    }


    /**
     * method to create the gui
     */

    public void createGameView() {

        /*
         * sets up the main frame of the game
         */
        this.setTitle("HoboOthello");
        this.setLocation(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        /*
         * Menue
         */
        this.setJMenuBar( new JMenuBar() );
        JMenu gameMenu = new JMenu ("Datei");
        JMenuItem closeGame = new JMenuItem("Exit");
        JMenuItem newGame = new JMenuItem("New Game");
        gameMenu.add(newGame);
        gameMenu.add(closeGame);
        this.getJMenuBar().add(gameMenu);

        /*
         * create a board. board is the center panel
         */
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout( new GridLayout(8,8) );
        boardPanel.setBorder( BorderFactory.createEtchedBorder() );

		/*
		 * create Fields out of the Field class and fill the board with fields
		 * field ist initialized with magic numbers but that will later be changed to a variable 'boardsize'
		 */
        //TODO add the double for-loop to the method setBoard()


        fieldView = new JButton[8][8];

        for(int row=0;row<fieldView.length;row++){
            for(int column=0;column<fieldView.length;column++){

                fieldView[row][column] = new JButton();
                fieldView[row][column].setBackground( backgroundColor );
                fieldView[row][column].setBorder( BorderFactory.createLineBorder( Color.BLACK, 1) );
                boardPanel.add( fieldView[row][column] ).setVisible( true );

            }
        }


        /*
         * the action panel which holds the start button
         */
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder (BorderFactory.createEtchedBorder());
        startButton.setText("Start");
        startButton.setPreferredSize(new Dimension(80,30));
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
         * BorderLayout dimensions
         */
        actionPanel.setPreferredSize( new Dimension(0, 100) );
        scorePanel.setPreferredSize( new Dimension(0, 50) );
        westPanel.setPreferredSize( new Dimension(75, 0) );
        eastPanel.setPreferredSize( new Dimension(75, 0) );


        /*
         * adding all elements to the main frame
         */
        this.getContentPane().add( scorePanel, BorderLayout.NORTH );
        this.getContentPane().add( boardPanel, BorderLayout.CENTER );
        this.getContentPane().add( actionPanel, BorderLayout.SOUTH );
        this.getContentPane().add( eastPanel, BorderLayout.EAST );
        this.getContentPane().add( westPanel, BorderLayout.WEST );
        this.setJMenuBar( new JMenuBar() );
        this.setVisible(true);

    }


    /*
     * the method to add an actionListener to the Gameview
     */
    //TODO add ActionListener to Controller
    void addButtonListener(ActionListener listenerForFieldButton) {

        for( int row=0; row<fieldView.length; row++ ) {
            for( int column=0; column<fieldView.length; column++ ) {

                fieldView[ row ][ column ].addActionListener( listenerForFieldButton );

            }
        }
    }

    /*
     * error message to display errors
     * e.g. wrong button clicked = illegal move
     */
    public void displayErrorMessage( String errorMessage ) {

        JOptionPane.showMessageDialog(this, errorMessage);

    }

}

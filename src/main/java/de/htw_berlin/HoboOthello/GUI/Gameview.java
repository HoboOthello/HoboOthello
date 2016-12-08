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

    private JMenu gameMenu;
    private JMenuItem closeGame;
    private JMenuItem newGame;
    private JMenu aboutMenu;
    private JMenuItem aboutItem;

    /**
     * the method to set up the board
     * @param boardSize
     * TODO in the Controller set Boardsize or add int boardSize to the Constructor Gameview()
     *
     */
    //TODO create instance of method in controller
    public void setBoard(int boardSize){

        fieldView = new JButton[boardSize][boardSize];

    }


    /**
     * Constructor to create the gui
     */

    public Gameview() {

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
         * MenuBar and all components
         */
        gameMenu = new JMenu ("Datei");
        closeGame = new JMenuItem("Exit");
        newGame = new JMenuItem("New Game");
        closeGame.addActionListener(
                new ActionListener() {public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog (null, "You're a true Hobo!","GoodBye!", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }});
        gameMenu.add(newGame);
        gameMenu.add(closeGame);
        // todo bugfix nullpointexception
        // ticket in https://github.com/HoboOthello/HoboOthello/issues/4
        // this.getJMenuBar().add(gameMenu);

        aboutMenu = new JMenu ("About");
        aboutItem = new JMenuItem ("About");
        aboutItem.addActionListener(
                new ActionListener() {public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog (null, "HoboOthello created by: Laura, Steffen and Bjoern","Info", JOptionPane.INFORMATION_MESSAGE);
                }});
        aboutMenu.add(aboutItem);
        // todo bugfix nullpointexception
        // ticket in https://github.com/HoboOthello/HoboOthello/issues/4
        // this.getJMenuBar().add(aboutMenu);


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
        //TODO add the method setBoard() and get the getBoardSize()
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
         * BorderLayout dimensions of the Center Panel
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

        this.setVisible(true);

    }




    /*
     * method to add ActionListener to the board
     */
    public void addBoardListener(ActionListener listenerForFieldButton) {

        for( int row=0; row<fieldView.length; row++ ) {
            for( int column=0; column<fieldView.length; column++ ) {
                fieldView[row][column].addActionListener( listenerForFieldButton );
            }
        }
    }



    /*
     * Getter for the board to check which Button was clicked
     * and to check the length of the board (6, 8, 10)
     * used in GameController -> inner Class BoardListener
     */
    public JButton getFieldView(int x, int y)
    {
        return fieldView[x][y];
    }

    public int getFieldViewLength()
    {
        return fieldView.length;
    }



    /*
     * error message to display errors
     * e.g. wrong button clicked = illegal move
     */
    public void displayErrorMessage( String errorMessage ) {

        JOptionPane.showMessageDialog(this, errorMessage);

    }

}

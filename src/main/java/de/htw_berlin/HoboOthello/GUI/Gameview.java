package de.htw_berlin.HoboOthello.GUI;

import de.htw_berlin.HoboOthello.Core.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by laura on 24.11.16.
 */
public class Gameview extends JFrame {

    private JLabel whiteScore = new JLabel("WHITE");
    private JLabel blackScore = new JLabel("BLACK");
    private Color backgroundColor = new Color(0, 150, 0);
    private JButton startButton = new JButton();
    private JButton passButton = new JButton();


    private Field[][] fields;

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    public void CreateGameView() {

        //sets up the main frame of the game
        JFrame mainFrame = new JFrame();

        mainFrame.setTitle("HoboOthello");
        mainFrame.setLocation(800, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setVisible(true);


        //create a board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(650, 650));
        boardPanel.setBorder(BorderFactory.createEtchedBorder());


		/*
         * create Fields out of the Field class and fill the board with fields
		 * field ist initialized with magic numbers but that will later be changed to a variable 'boardsize'
		 */
        //TODO adding a variable for the boardsize and fix the size of the gui for larger or smaller boards

        for (int row = 0; row < this.fields.length; row++) {
            for (int column = 0; column < this.fields.length; column++) {
                // fields[row][column] = new Field();
                // fields[row][column].setBackground(backgroundColor);
                // fields[row][column].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                // boardPanel.add(fields[row][column]).setVisible(true);

            }
        }


        //the panel for all action buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setPreferredSize(new Dimension(800, 100));
        actionPanel.setBorder(BorderFactory.createEtchedBorder());
        startButton.setText("Start");
        startButton.setPreferredSize(new Dimension(80, 30));
        actionPanel.add(startButton);
        passButton.setText("Passen");
        passButton.setPreferredSize(new Dimension(80, 30));
        actionPanel.add(passButton);

        //the panel to display the actual score
        JPanel scorePanel = new JPanel(new FlowLayout());
        scorePanel.setPreferredSize(new Dimension(800, 50));
//		scorePanel.setBorder (BorderFactory.createEtchedBorder());
        whiteScore.setBorder(BorderFactory.createEtchedBorder());
        blackScore.setBorder(BorderFactory.createEtchedBorder());
        scorePanel.add(whiteScore);
        scorePanel.add(blackScore);

        //filler space for the right hand side
        JPanel eastPanel = new JPanel(new GridLayout());
        eastPanel.setPreferredSize(new Dimension(75, 700));
//		eastPanel.setBorder (BorderFactory.createEtchedBorder());

        //filler pace for the left hand side
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(75, 700));
//		westPanel.setBorder (BorderFactory.createEtchedBorder());

        //adding all elements to the main frame
        mainFrame.getContentPane().add(scorePanel, "North");
        mainFrame.getContentPane().add(boardPanel, "Center");
        mainFrame.getContentPane().add(actionPanel, "South");
        mainFrame.getContentPane().add(eastPanel, "East");
        mainFrame.getContentPane().add(westPanel, "West");
        mainFrame.setJMenuBar(new JMenuBar());
        mainFrame.setVisible(true);

    }


}

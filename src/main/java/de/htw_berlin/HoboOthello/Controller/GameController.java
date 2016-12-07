package de.htw_berlin.HoboOthello.Controller;

import de.htw_berlin.HoboOthello.Core.Game;
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

    public GameController(Gameview theView, Game theGame){

        this.gameview = theView;
        this.theGame = theGame;

        theView.addBoardListener(new BoardListener());

    }


    /**
     * inside class
     * BoardListener method to check which Button has been clicked
     */
    class BoardListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            try{

                for( int r=0; r<gameview.getFieldViewLength() ; r++ )
                {	for( int c=0; c<gameview.getFieldViewLength() ; c++ )
                    {
                        if( e.getSource() == gameview.getFieldView(r, c) )
                        {

                            System.out.println("IT'S A HIT!");
                            System.out.println(r);
                            System.out.println(c);
                            System.out.println();

                        }
                    }
                }

                }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("Illegal Move!");

            }
        }

    }
    //TODO this class is unused may be deleted
    class MenuListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0){

            try{

                //TODO BJOERN write a line that starts a new game

            }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("Ups! Something is wrong?!");

            }
        }

    }

    //TODO this class is unused may be deleted
    class ExitListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0){

            try{

                gameview.displayErrorMessage("You're a true Hobo!");
                gameview.setVisible(false);
            }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("Ups! Something is wrong?!");

            }
        }

    }





}







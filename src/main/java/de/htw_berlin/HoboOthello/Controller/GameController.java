package de.htw_berlin.HoboOthello.Controller;

import de.htw_berlin.HoboOthello.Core.Game;
import de.htw_berlin.HoboOthello.Core.GameRule;
import de.htw_berlin.HoboOthello.GUI.Gameview;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by laura on 24.11.16.
 */
public class GameController {

    private Gameview gameview;
    private Game theGame;
    private GameRule theGameRules;

    public GameController(Gameview theView, Game theGame, GameRule theGameRules){

        this.gameview = theView;
        this.theGame = theGame;
        this.theGameRules = theGameRules;

        this.gameview.addBoardListener(new BoardListener());
        this.gameview.addMenuListener(new MenuListener());
        this.gameview.addExitListener(new ExitListener());

    }


    //inside classes

    class BoardListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0){

            try{

                //TODO BJOERN write a line that checks if the field is a legal field to click on

            }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("Illegal Move!");

            }
        }

    }

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

    class ExitListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0){

            try{

                //TODO BJOERN write a line that ends a new game
                gameview.setVisible(false);
                gameview.displayErrorMessage("You're a true Hobo!");
            }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("Ups! Something is wrong?!");

            }
        }

    }





}







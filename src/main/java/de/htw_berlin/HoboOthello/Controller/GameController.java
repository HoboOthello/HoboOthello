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

    }


    //inside class

    class BoardListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0){

            try{

//                theGameRules.isMoveAllowed()

            }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("Illegal Move!");

            }
        }

    }

}







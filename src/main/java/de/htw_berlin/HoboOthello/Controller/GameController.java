package de.htw_berlin.HoboOthello.Controller;

import de.htw_berlin.HoboOthello.Core.Game;
import de.htw_berlin.HoboOthello.GUI.Gameview;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by laura on 24.11.16.
 */
public class GameController {

    private Gameview gameview;
    private Game theModel;


    public GameController(Gameview theView, Game theModel){

        this.gameview = theView;
        this.theModel = theModel;

        // todo add Listener ?

    }

    /*
    Björns example

    //INNERE KLASSE

    class CalculationListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0){

            int firstNumber, secondNumber = 0;

            try{

                firstNumber = gameview.getFirstNumber();
                secondNumber = gameview.getSecondNumber();

                */
                /**
                 * Instead, one could do:
                 theModel.addTwoNumbers(theView.getFirstNumber(), theView.getSecondNumber());
                 * This way I have two values stored in CONTROL which makes for easier to understand code
                 */
                /*
                theModel.addTwoNumbers(firstNumber, secondNumber);

                gameview.setCalcSolution(theModel.getCalculationValue());

            }

            catch(NumberFormatException ex){

                gameview.displayErrorMessage("You need to enter two Integers");

            }
        }

    }
                    */
}







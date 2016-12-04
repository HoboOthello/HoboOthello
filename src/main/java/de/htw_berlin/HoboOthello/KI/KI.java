package de.htw_berlin.HoboOthello.KI;

import de.htw_berlin.HoboOthello.Core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KI extends Player {

    private Color kiColor;
    private Level level;

    private Board board;
    private Field field;
    private GameRule gameRule;

    public KI(Color kiColor) {
        this.kiColor = kiColor;
    }
    //rückgabe an controller ist ein field
    public Field setMove(Board board) {
        //return Field fieldToSetMove;
        return null;
    }

    private List<Field> listPossibleMoves() {
        List<Field> listOfPossibleMoves = new ArrayList<Field>();

        for (Field field : board.iterateThroughAllFields()) {
            if (gameRule.isMoveAllowed(field, kiColor)) {
                listOfPossibleMoves.add(this.field);
            }

        }
        return listOfPossibleMoves;
    }

    private Field pickRandomFieldFromList(){
        List<Field> listOfPossibleMoves = listPossibleMoves();
        int randomNumber = (int )(Math.random() * listOfPossibleMoves.size() + 1); // picks random index of field in list
        return listOfPossibleMoves.get(randomNumber);

    }

}

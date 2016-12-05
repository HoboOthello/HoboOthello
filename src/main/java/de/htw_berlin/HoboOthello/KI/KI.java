package de.htw_berlin.HoboOthello.KI;

import de.htw_berlin.HoboOthello.Core.*;

import java.util.ArrayList;
import java.util.List;


public class KI extends Player {

    private Color kiColor;
    private Level level;

    private Board board;
    private Field field;
    private GameRule gameRule;

    public KI(Color kiColor) {
        this.kiColor = kiColor;
    }

    /**
     * Method which will be used by the controller to let the KI pick a field
     *
     * @param board actual board, information for the KI to act upon
     * @return Field with coordinates where the KI wants to put down a stone
     */
    //rückgabe an controller ist ein field
    public Field setMove(Board board) {
        if (level == level.LEVEL1) {
            return pickRandomFieldFromList();
        }
        if (level == level.LEVEL2) {
            //return Field fieldToSetMove;
            return null;
        }
        if (level == level.LEVEL3) {
            //return Field fieldToSetMove;
            return null;
        } else {
            throw new IllegalArgumentException("Level of KI is off...!");
        }
    }

    /**
     * Method which lists all possible moves for the KI
     *
     * @return listOfPossibleMoves
     */
    private List<Field> listPossibleMoves() {
        List<Field> listOfPossibleMoves = new ArrayList<Field>();

        for (Field field : board.iterateThroughAllFields()) {
            if (gameRule.isMoveAllowed(field, kiColor)) {
                listOfPossibleMoves.add(field);
            }
        }
        return listOfPossibleMoves;
    }

    /**
     * Method which picks a random move from the list of all possible moves for the KI
     *
     * @return field which is randomly chosen by this method
     */
    private Field pickRandomFieldFromList() {
        List<Field> listOfPossibleMoves = listPossibleMoves();
        int randomNumber = (int) (Math.random() * listOfPossibleMoves.size() + 1); // picks random index of field in list
        return listOfPossibleMoves.get(randomNumber);

    }

}

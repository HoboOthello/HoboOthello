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
    //TODO bestimmen was wir machen wenn das field null ist
    //rückgabe an controller ist ein field
    public Field setMove(Board board) {
        if (level == level.LEVEL1) {
            return pickRandomFieldFromList();
        }

        if (level == level.LEVEL2) {
            Field fieldToSetMove;
            fieldToSetMove = pickCornerOrSideFieldFromList();
            if (fieldToSetMove == null) {
                fieldToSetMove = pickRandomFieldFromList();
            }
            return fieldToSetMove;
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
    //TODO: what do we do if there are no possible moves for the ki; aka list is empty
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
        int randomNumber = (int) (Math.random() * listOfPossibleMoves.size()); // picks random index of field in list
        return listOfPossibleMoves.get(randomNumber);
    }

    /**
     * Method which picks the first possible corner field or the first possible side field for a turn
     * If there are no corner or side fields possible for this turn, this method returns null
     *
     * @return cornerOrSideField a possible corner or side field, null field if both are impossible
     */
    private Field pickCornerOrSideFieldFromList() {
        List<Field> listOfPossibleMoves = listPossibleMoves();
        Field cornerOrSideField = null;

        int cornerFieldIndex = 0;
        while (cornerFieldIndex < listOfPossibleMoves.size()) {
            Field field = listOfPossibleMoves.get(cornerFieldIndex);
            if (board.isCornerField(field)) {
                cornerOrSideField = field;
            } else {
                cornerFieldIndex++;
            }
        }

        int sideFieldIndex = 0;
        while (sideFieldIndex < listOfPossibleMoves.size()) {
            Field field = listOfPossibleMoves.get(sideFieldIndex);
            if (board.isSideField(field)) {
                cornerOrSideField = field;
            } else {
                sideFieldIndex++;
            }
        }
        return cornerOrSideField;
    }



}

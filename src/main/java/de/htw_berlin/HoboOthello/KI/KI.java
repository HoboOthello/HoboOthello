package de.htw_berlin.HoboOthello.KI;

import de.htw_berlin.HoboOthello.Core.*;

import java.util.ArrayList;
import java.util.List;


public class KI extends Player {

    private Color kiColor;
    private Level level;

    private Board board = new Board(getBoardSize());
    private Field field;
    private GameRule gameRule;

    public KI(Color color, Level level) {
        super(color);
        this.kiColor = color;
        this.level = level;
    }

    /**
     * Method which will be used by the controller to let the KI pick a field
     *
     * @param board actual board, information for the KI to act upon
     * @return field with coordinates where the KI wants to put down a stone,
     * return field is null in case there is no possible move for the KI
     */
    public Field setMove(Board board) {
        Field fieldToSetMove;

        if (level == level.LEVEL1) {
            fieldToSetMove = pickRandomFieldFromList();
            return fieldToSetMove;
        }

        if (level == level.LEVEL2) {
            fieldToSetMove = pickCornerOrSideFieldFromList();
            if (fieldToSetMove == null) {
                fieldToSetMove = pickRandomFieldFromList();
            }
            return fieldToSetMove;
        }

        if (level == level.LEVEL3) {
            fieldToSetMove = pickTacticalField();
            return fieldToSetMove;

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
        Field fieldToSet;
        List<Field> listOfPossibleMoves = listPossibleMoves();
        int randomNumber = (int) (Math.random() * listOfPossibleMoves.size()); // picks random index of field in list
        fieldToSet = listOfPossibleMoves.get(randomNumber);
        return fieldToSet;
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

    /**
     * Method which lists all corner fields that are available for the ki in the current turn
     *
     * @return listOfPossibleCornerFields, null if there are no possible corner fields
     */
    private List<Field> listPossibleCornerFields() {
        List<Field> listOfPossibleMoves = listPossibleMoves();
        List<Field> listOfPossibleCornerFields = null;

        int cornerFieldIndex = 0;
        while (cornerFieldIndex < listOfPossibleMoves.size()) {
            Field field = listOfPossibleMoves.get(cornerFieldIndex);
            if (board.isCornerField(field)) {
                listOfPossibleCornerFields.add(field);
            } else {
                cornerFieldIndex++;
            }
        }
        return listOfPossibleCornerFields;
    }

    /**
     * Method which lists all side (or border) fields that are available for the ki in the current turn
     *
     * @return listOfPossibleSideFields, null if there are no possible side fields
     */
    private List<Field> listPossibleSideFields() {
        List<Field> listOfPossibleMoves = listPossibleMoves();
        List<Field> listOfPossibleSideFields = null;

        int sideFieldIndex = 0;
        while (sideFieldIndex < listOfPossibleMoves.size()) {
            Field field = listOfPossibleMoves.get(sideFieldIndex);
            if (board.isSideField(field)) {
                listOfPossibleSideFields.add(field);
            } else {
                sideFieldIndex++;
            }
        }
        return listOfPossibleSideFields;

    }

    /**
     * Method which lists all fields that are not on the border-minus-one-range and are available for the ki in the current turn.
     * It is smart to make a move on a field not too close to the border,
     * so the other player can't get a stone in a corner of on a border field.
     *
     * @return listOfPossibleSideFields, null if there are no possible side fields
     */
    private List<Field> listPossibleFieldsNotTooCloseToBorder() {
        List<Field> listOfPossibleMoves = listPossibleMoves();
        List<Field> listOfFieldsNotCloseToBorder = null;
        int count = 0;
        while (count < listOfPossibleMoves.size()) {
            Field field = listOfPossibleMoves.get(count);
            if (board.isNotSideMinusOneField(field)) {
                listOfFieldsNotCloseToBorder.add(field);
            } else {
                count++;
            }
        }
        return listOfFieldsNotCloseToBorder;
    }

    /**
     * Method which picks the most tactical field to make a move on
     *
     * @return tacticalField, return null if there is no possible move
     */
    private Field pickTacticalField() {

        //If there are corner fields available, these are a very good and tactical pick!
        List<Field> listOfCornerFields = listPossibleCornerFields();
        if (listOfCornerFields != null) {
            return pickTacticalFieldFromList(listOfCornerFields);
        }

        //Side fields are also very good and tactical spots
        List<Field> listOfSideFields = listPossibleSideFields();
        if (listOfSideFields != null) {
            return pickTacticalFieldFromList(listOfSideFields);
        }

        //It is smart to make a move on a field not too close to the border,
        //so the other player can't get a stone in a corner of on a border field.
        List<Field> listOfFieldsNotTooCloseToBorder = listPossibleFieldsNotTooCloseToBorder();
        if (listOfFieldsNotTooCloseToBorder != null) {
            return pickTacticalFieldFromList(listOfFieldsNotTooCloseToBorder);
        }

        List<Field> listOfAllPossibleMoves = listPossibleMoves();
        if (listOfAllPossibleMoves != null) {
            return pickTacticalFieldFromList(listOfAllPossibleMoves);
        } else {
            return null;
        }
    }


    /**
     * Method which goes through a list of possible tactical fields and checks how many stones would be turned
     * if this field would be chosen to put the stone on
     *
     * @param listToPickFrom hands over a list of fields this method needs to calculate and pick the best option from
     * @return tacticalField, return null if there is no possible move
     */
    private Field pickTacticalFieldFromList(List<Field> listToPickFrom) {
        Field tacticalField = null;
        int numberOfStonesFlipped;
        int mostFlippedStones = 0;

        for (int indexFieldToCheck = 0; indexFieldToCheck < listToPickFrom.size(); indexFieldToCheck++) {
            numberOfStonesFlipped = testHowManyStonesAreFlipped(listToPickFrom.get(indexFieldToCheck));
            if (numberOfStonesFlipped > mostFlippedStones) {
                tacticalField = listToPickFrom.get(numberOfStonesFlipped);
                mostFlippedStones = numberOfStonesFlipped;
            }
        }
        return tacticalField;
    }


    private int testHowManyStonesAreFlipped(Field field) {
        int actualNumberOfStones = board.getNumberOfFieldsOccupiedByStone(kiColor);
        //TODO Frage: Wo wird das gespeichert, wie lösche ich das wieder? thnx!
        // wird in gamerule gesichert
        // useage gamerule neu erstellen -> mit felder füllen -> testen -> auswerten -> löschen
        // solange wiederholen wie dir spass macht
        gameRule.setMove(field, kiColor);
        int newNumberOfStones = board.getNumberOfFieldsOccupiedByStone(kiColor);
        int numberOfStonesFlipped = (newNumberOfStones - actualNumberOfStones);
        return numberOfStonesFlipped;
    }

/*
    int randomNumber = (int) (Math.random() * listOfFieldsNotCloseToBorder.size()); // picks random index of field in list
    fieldToSet = listOfFieldsNotCloseToBorder.get(randomNumber);
        return fieldToSet;
*/
}

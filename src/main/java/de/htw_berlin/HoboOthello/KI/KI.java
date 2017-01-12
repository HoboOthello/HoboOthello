package de.htw_berlin.HoboOthello.KI;

import de.htw_berlin.HoboOthello.Core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laura on 17.11.16.
 */
public class KI extends Player {

    private Color kiColor;
    private Level level;

    private Board board;
    private Field field;
    private GameRule gameRule;

    public KI(Color color, Level level) {
        super(color);
        this.kiColor = color;
        this.level = level;

        switch (level) {
            case LEVEL1:
                setPlayerType(PlayerType.KI_LEVEL1);
                break;
            case LEVEL2:
                setPlayerType(PlayerType.KI_LEVEL2);
                break;
            case LEVEL3:
                setPlayerType(PlayerType.KI_LEVEL3);
                break;
        }

    }

    /**
     * Method which will be used by the controller to let the KI pick a field
     *
     * @param board actual board, information for the KI to act upon
     * @return field with coordinates where the KI wants to put down a stone,
     * return field is null in case there is no possible move for the KI
     */
    public Field setMove(Board board) {
        this.board = board;
        this.gameRule = new GameRule(this.board.getFields());
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
        List<Field> listOfAllFields = board.iterateThroughAllFields();

        if (listOfAllFields != null && listOfAllFields.size() != 0) {
            for (Field field : listOfAllFields) {
                if (gameRule.isMoveAllowed(field, kiColor)) {
                    listOfPossibleMoves.add(field);
                }
            }
        } else {

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
        while (cornerFieldIndex < listOfPossibleMoves.size() - 1) {
            Field field = listOfPossibleMoves.get(cornerFieldIndex);
            if (board.isCornerField(field)) {
                cornerOrSideField = field;
            }
            cornerFieldIndex++;
        }

        int sideFieldIndex = 0;
        while (sideFieldIndex < listOfPossibleMoves.size() - 1) {
            Field field = listOfPossibleMoves.get(sideFieldIndex);
            if (board.isSideField(field)) {
                cornerOrSideField = field;
            }
            sideFieldIndex++;
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
        List<Field> listOfPossibleCornerFields = new ArrayList<Field>();

        int cornerFieldIndex = 0;
        while (cornerFieldIndex < listOfPossibleMoves.size()) {
            Field field = listOfPossibleMoves.get(cornerFieldIndex);
            if (board.isCornerField(field)) {
                listOfPossibleCornerFields.add(field);
            }
            cornerFieldIndex++;
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
        List<Field> listOfPossibleSideFields = new ArrayList<Field>();

        int sideFieldIndex = 0;
        while (sideFieldIndex < listOfPossibleMoves.size() - 1) {
            Field field = listOfPossibleMoves.get(sideFieldIndex);
            if (board.isSideField(field)) {
                listOfPossibleSideFields.add(field);
            }
            sideFieldIndex++;
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
        List<Field> listOfFieldsNotCloseToBorder = new ArrayList<Field>();
        int count = 0;
        while (count < listOfPossibleMoves.size() - 1) {
            Field field = listOfPossibleMoves.get(count);
            if (board.isNotSideMinusOneField(field)) {
                listOfFieldsNotCloseToBorder.add(field);
            }
            count++;
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
        if (listOfCornerFields != null && listOfCornerFields.size() != 0) {
            return pickTacticalFieldFromList(listOfCornerFields);
        }

        //Side fields are also very good and tactical spots
        List<Field> listOfSideFields = listPossibleSideFields();
        if (listOfSideFields != null && listOfSideFields.size() != 0) {
            return pickTacticalFieldFromList(listOfSideFields);
        }

        //It is smart to make a move on a field not too close to the border,
        //so the other player can't get a stone in a corner of on a border field.
        List<Field> listOfFieldsNotTooCloseToBorder = listPossibleFieldsNotTooCloseToBorder();
        if (listOfFieldsNotTooCloseToBorder != null && listOfFieldsNotTooCloseToBorder.size() != 0) {
            return pickTacticalFieldFromList(listOfFieldsNotTooCloseToBorder);
        }

        List<Field> listOfAllPossibleMoves = listPossibleMoves();
        if (listOfAllPossibleMoves != null && listOfAllPossibleMoves.size() != 0) {
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

        for (Field field : listToPickFrom) {
            numberOfStonesFlipped = testHowManyStonesAreFlipped(field);
            if (numberOfStonesFlipped > mostFlippedStones) {
                tacticalField = field;
                mostFlippedStones = numberOfStonesFlipped;
            }
        }
        return tacticalField;
    }


    private int testHowManyStonesAreFlipped(Field field) {
        int actualNumberOfStones = board.getNumberOfFieldsOccupiedByStone(kiColor);

        Field[][] copiedFields = copyFields(board.getFields());
        GameRule newGameRule = new GameRule(copiedFields);
        newGameRule.setMove(field, kiColor);

        Board newBoard = new Board(board.getBoardSize());
        newBoard.setFields(newGameRule.getFields());

        int newNumberOfStones = newBoard.getNumberOfFieldsOccupiedByStone(kiColor);
        int numberOfStonesFlipped = (newNumberOfStones - actualNumberOfStones);
        return numberOfStonesFlipped;
    }

    private Field[][] copyFields(Field[][] originalFields) {
        Field[][] copiedFields = new Field[originalFields.length][originalFields[0].length];
        for (int i = 0; i < originalFields.length; i++) {
            for (int j = 0; j < originalFields[i].length; j++) {
                copiedFields[i][j] = new Field(originalFields[i][j]);
            }
        }
        return copiedFields;
    }


}

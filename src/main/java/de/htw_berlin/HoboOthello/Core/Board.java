package de.htw_berlin.HoboOthello.Core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laura on 17.11.16.
 */
public class Board {

    public static final int BOARD_SIZE_EIGHT = 8;
    public static final int BOARD_SIZE_SIX = 6;
    public static final int BOARD_SIZE_TEN = 10;

    private int boardSize;

    private Field[][] fields;

    /**
     * Constructor of Board which declares and constructs an two-dimensional array of fields
     * Calls a method to fill the field with default values
     * Standard size of Board is 8 x 8
     * Small size of Board is 6 x 6
     * Large size of Board is 10 x 10
     */
    public Board(int i) {
        initBoard(i);
    }

    private void initBoard(int newBoardSize) {
        if (isBoardSizeAllowed(newBoardSize)) {
            this.boardSize = newBoardSize;
            fields = new Field[newBoardSize][newBoardSize];
            fields = fillWithDefaultValues(fields);
        } else {
            throw new IllegalArgumentException("Invalid Board Size!");
        }
    }

    private boolean isBoardSizeAllowed(int boardSize) {
        return boardSize == BOARD_SIZE_EIGHT
                || boardSize == BOARD_SIZE_SIX
                || boardSize == BOARD_SIZE_TEN;
    }

    /**
     * Method which iterates through all the fields on the board in this specific order:
     * Starts at [0][0], moves down the first vertical vector ([0][++]),
     * then moves one column to the right ([1][0]) and down the second  vertical vector ([1][++]), etc.
     * int i = counter on x-axis
     * int j = counter on y-axis
     *
     * @return listOfFields which is a lists of all fields, in this specific order
     */
    public List<Field> iterateThroughAllFields() {

        List<Field> listOfFields = new ArrayList<Field>();

        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                listOfFields.add(this.fields[i][j]);
            }
        }
        return listOfFields;
    }

    /**
     * Method to check if a field is in the corner of the board
     *
     * @param field that is being checked
     * @return true is field is in the corner of the board
     */
    public boolean isCornerField(Field field) {
        if (field == this.fields[0][0] ||
                field == this.fields[0][fields.length] ||
                field == this.fields[fields.length][0] ||
                field == this.fields[fields.length][fields.length]) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if a field is on the side of the board
     *
     * @param field that is being checked
     * @return true is field is on the side of the board
     */
    public boolean isSideField(Field field) {
        boolean isSideField = false;
        int counter = 0;
        while (counter < fields.length || isSideField) {
            if (field == this.fields[0][counter] ||
                    field == this.fields[counter][0] ||
                    field == this.fields[fields.length][counter] ||
                    field == this.fields[counter][fields.length]) {
                isSideField = true;
            } else {
                counter++;
            }
        }
        return isSideField;
    }

    /**
     * Construct the current Board as a multi-line String for debugging
     *
     * @return Board overview as a String
     */
    public String getBoardOverview() {
        String boardOverview = "";

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                char StoneColor = ' ';

                if (!this.fields[i][j].isEmpty()) {
                    switch (this.fields[i][j].getStone().getStoneColor()) {
                        case BLACK:
                            StoneColor = 'B';
                            break;
                        case WHITE:
                            StoneColor = 'W';
                            break;
                    }
                } else if (this.fields[i][j].isPossibleMove()) {
                    StoneColor = 'x';
                }

                boardOverview = boardOverview + StoneColor;

            }
            // next line
            boardOverview = boardOverview + String.format("%n");
        }

        return boardOverview;
    }


    /**
     * Method which fills fields with default values.
     * Default setup of a new board at the start of the game is:
     * <p>
     * +------------+
     * |            |
     * |            |
     * |     BW     |
     * |     WB     |
     * |            |
     * |            |
     * +------------+
     * <p>
     *
     * @param fieldsToFill fields which are being filled
     * @return fields with default values as pictured above
     */
    private Field[][] fillWithDefaultValues(Field[][] fieldsToFill) {
        for (int i = 0; i < fieldsToFill.length; i++) {
            for (int j = 0; j < fieldsToFill.length; j++) {
                fieldsToFill[i][j] = new Field();
            }
        }

        // add starting stones for both players
        Stone stoneBlack = new Stone();
        stoneBlack.setStoneColor(Color.BLACK);
        Stone stoneWhite = new Stone();
        stoneWhite.setStoneColor(Color.WHITE);

        int BoardHalfLength = fieldsToFill.length / 2;
        int middle_x_left = BoardHalfLength - 1;
        int middle_x_right = BoardHalfLength;
        int middle_y_up = BoardHalfLength - 1;
        int middle_y_down = BoardHalfLength;

        fieldsToFill[middle_x_left][middle_y_up].setStone(stoneBlack);
        fieldsToFill[middle_x_right][middle_y_up].setStone(stoneWhite);
        fieldsToFill[middle_x_left][middle_y_down].setStone(stoneWhite);
        fieldsToFill[middle_x_right][middle_y_down].setStone(stoneBlack);

        return fieldsToFill;
    }

    /**
     * Method which counts how many fields are occupied by a stone of a certain color
     *
     * @param colorOfStonesToCount the color of stones which are being counted
     * @return int number of fields which are occupied by a stone of that color
     */
    private int numberOfFieldsOccupiedByStoneColor(Color colorOfStonesToCount) {
        int counterOccupiedByStoneColor = 0;
        Stone stone = new Stone();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (this.fields[i][j].isOccupiedByStone()) {
                    if (stone.getStoneColor() == colorOfStonesToCount)
                        counterOccupiedByStoneColor++;
                }
            }
        }
        return counterOccupiedByStoneColor;
    }

    /**
     * Gets how many fields are occupied by a stone from a specific color
     *
     * @param stoneColor the color of stones which are being counted
     * @return int number of fields which are occupied by a stone of that color
     */
    public int getNumberOfFieldsOccupiedByStone(Color stoneColor) {
        return numberOfFieldsOccupiedByStoneColor(stoneColor);
    }

    /**
     * Total number of fields which are occupied by a stone
     */
    private int numberOfOccupiedFields;

    /**
     * Gets how many fields are occupied by a stone
     *
     * @return the total number of fields which are occupied by a stone
     */
    public int getNumberOfOccupiedFields() {
        this.numberOfOccupiedFields = numberOfFieldsOccupiedByStoneColor(Color.WHITE) + numberOfFieldsOccupiedByStoneColor(Color.BLACK);
        return numberOfOccupiedFields;
    }

    /**
     * Boolean to determine is the board is completely filled with stones
     *
     * @return true if board is completely filled with stones
     */
    private boolean boardIsFull() {
        int numberOfFieldsOnBoard = boardSize * boardSize;
        return (numberOfFieldsOnBoard == this.numberOfOccupiedFields);
    }

    /**
     * Boolean to ask the board if the board is completely filled with stones
     *
     * @return true if board is completely filled with stones
     */
    public boolean getBoardIsFull() {
        return boardIsFull();
    }

    public Field[][] isFields() {
        return this.fields;
    }

    public void setFields(Field[][] newFields) {
        this.fields = newFields;
    }

    public int getBoardSize() {
        return this.boardSize;
    }


}


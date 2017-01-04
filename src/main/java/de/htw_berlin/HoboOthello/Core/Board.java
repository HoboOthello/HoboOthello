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

        List<Field> listOfFields = new ArrayList<Field>(fields.length * fields.length);
        // TODO: n**m complexity
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
                field == this.fields[0][fields.length - 1] ||
                field == this.fields[fields.length - 1][0] ||
                field == this.fields[fields.length - 1][fields.length - 1]) {
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
        if (field.getX() == 0 || field.getX() == boardSize - 1) {
            return true;
        }
        if (field.getY() == 0 || field.getY() == boardSize - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNotSideMinusOneField(Field field) {
        if (field.getX() == 1 || field.getX() == boardSize - 2) {
            return true;
        }

        if (field.getY() == 1 || field.getY() == boardSize - 2) {
            return true;
        }

        return false;

    }

    /**
     * Construct the current Board as a multi-line String for debugging
     *
     * @return Board overview as a String
     */

    public String getBoardOverview() {
        String boardOverview = "";

        for (int y = 0; y < this.boardSize; y++) {
            for (int x = 0; x < this.boardSize; x++) {
                char StoneColor = ' ';

                if (!this.fields[x][y].isEmpty()) {
                    switch (this.fields[x][y].getStone().getColor()) {
                        case BLACK:
                            StoneColor = 'B';
                            break;
                        case WHITE:
                            StoneColor = 'W';
                            break;
                    }
                } else if (this.fields[x][y].isPossibleMove()) {
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
        for (int x = 0; x < fieldsToFill.length; x++) {
            for (int y = 0; y < fieldsToFill.length; y++) {
                fieldsToFill[x][y] = new Field(x, y);
            }
        }

        // add starting stones for both players
        Stone stoneBlack = new Stone();
        stoneBlack.setColor(Color.BLACK);
        Stone stoneWhite = new Stone();
        stoneWhite.setColor(Color.WHITE);

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
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (this.fields[i][j].isOccupiedByStone()) {
                    if (this.fields[i][j].getStone().getColor() == colorOfStonesToCount)
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


    public Field[][] getFields() {
        return this.fields;
    }

    public void setFields(Field[][] newFields) {
        this.fields = newFields;
    }

    public int getBoardSize() {
        return this.boardSize;
    }


}


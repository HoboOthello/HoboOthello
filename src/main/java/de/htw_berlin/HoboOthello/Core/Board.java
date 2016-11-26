package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 17.11.16.
 */
public class Board {

    public static final int BOARD_SIZE_EIGHT = 8;
    public static final int BOARD_SIZE_SIX = 6;
    public static final int BOARD_SIZE_TEN = 10;

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

    private void initBoard(int boardSize) {
        if (isBoardSizeAllowed(boardSize)) {
            fields = new Field[boardSize][boardSize];
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
     * Method which fills fields with default values.
     * Default value of a field is:
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
     * @return fields with default values (true, false, false)
     */
    private Field[][] fillWithDefaultValues(Field[][] fieldsToFill) {
        for (int i = 0; i < fieldsToFill.length; i++) {
            for (int j = 0; j < fieldsToFill.length; j++) {
                fieldsToFill[i][j] = new Field();
            }
        }

        // add starting stones for both players
        Stone stoneBlack = new Stone();
        stoneBlack.setStoneColor(StoneColor.BLACK);
        Stone stoneWhite = new Stone();
        stoneWhite.setStoneColor(StoneColor.WHITE);

        int BoardHalfLength = fieldsToFill.length / 2;
        int middle_x_left = BoardHalfLength -1;
        int middle_x_right = BoardHalfLength;
        int middle_y_up = BoardHalfLength -1;
        int middle_y_down = BoardHalfLength;

        fieldsToFill[middle_x_left][middle_y_up].setStone(stoneBlack);
        fieldsToFill[middle_x_right][middle_y_up].setStone(stoneWhite);
        fieldsToFill[middle_x_left][middle_y_down].setStone(stoneWhite);
        fieldsToFill[middle_x_right][middle_y_down].setStone(stoneBlack);

        return fieldsToFill;
    }

    private int numberOfFieldsOccupiedByStone(StoneColor colorOfStonesToCount) {
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


    private int numberOfOccupiedFields = numberOfFieldsOccupiedByStone(StoneColor.WHITE) + numberOfFieldsOccupiedByStone(StoneColor.BLACK);

    public int getNumberOfOccupiedFields() {
        return numberOfOccupiedFields;
    }

    /**
     * Get the Board as an String, to show it for the developers
     * <p>
     * Example:
     * 0 1 2 3 4 5
     * +-------------+
     * 0|             |
     * 1|             |
     * 2|             |
     * 3|     b w     |
     * 4|     w b     |
     * 5|             |
     * +-------------+
     *
     * @return get the Board Overview as a String
     */
    //TODO Refctor as well
  /*
    public String BoardOverview() {
        // init var
        String Overview = "  ";

        // add header
        for (int i = 0; i < this.fields.length; i++) {
            Overview = Overview + String.format("%2d", i);
        }
        Overview = Overview + String.format("%n  +");

        for (int i = 0; i < this.fields.length; i++) {
            Overview = Overview + String.format("--");
        }
        Overview = Overview + String.format("+%n");

        // content
        for (int i = 0; i < this.fields.length; i++) {
            Overview = Overview + String.format("%2d|", i);
            for (int j = 0; j < this.fields.length; j++) {
                char temp = this.fields[j][i].isFieldState();
                if (temp == 'e') {
                    temp = ' ';
                }
                Overview = Overview + String.format("%c ", temp);
            }
            Overview = Overview + String.format("|%n");
        }

        // add footer
        Overview = Overview + String.format("  +");
        for (int i = 0; i < this.fields.length; i++) {
            Overview = Overview + String.format("--");
        }
        Overview = Overview + String.format("+");

        return Overview;
    }
*/
}


package de.htw_berlin.HoboOthello.core;

/**
 * Created by laura on 17.11.16.
 */
public class Board {

    public static final int STANDARD_BOARD_SIZE_EIGHT = 8;
    public static final int SMALL_BOARD_SIZE_SIX = 6;
    public static final int LARGE_BOARD_SIZE_TEN = 10;


    /**
     * Set the curret Player
     * true == Black
     * false == White
     * default is Black cause Black start the game
     */
    private boolean CurrentPlayer = true;
    private int PossibleFields = 0;

    private Field[][] fields;

    /**
     * Constructor of Board which declares and constructs an two dimensional array of fields
     * Small size of Board is 6 x 6
     * Standard size of Board is 8 x 8
     * Large size of Board is 10 x 10
     * Calls a method to fill the field with default values
     */

    public Board(int i) {
        switch (i) {
            case 6:
                fields = new Field[SMALL_BOARD_SIZE_SIX][SMALL_BOARD_SIZE_SIX];
                fields = fillWithDefaultValues(fields);
                break;
            case 8:
                fields = new Field[STANDARD_BOARD_SIZE_EIGHT][STANDARD_BOARD_SIZE_EIGHT];
                fields = fillWithDefaultValues(fields);
                break;
            case 10:
                fields = new Field[LARGE_BOARD_SIZE_TEN][LARGE_BOARD_SIZE_TEN];
                fields = fillWithDefaultValues(fields);
                break;
            default:
                System.out.println("Invalid size for a HoboOthello Board");
                //todo : decide if case 8 becomes default
        }
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
     * @param fields fields which are being filled
     * @return fields with default values (true, false, false)
     */
    private Field[][] fillWithDefaultValues(Field[][] fields) {
        // todo change var name 'fields' in this method to a unique name
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = new Field();
            }
        }

        // add starting stones for both players
        fields[fields.length / 2 - 1][fields.length / 2 - 1].setBlack();
        fields[fields.length / 2][fields.length / 2 - 1].setWhite();
        fields[fields.length / 2 - 1][fields.length / 2].setWhite();
        fields[fields.length / 2][fields.length / 2].setBlack();

        return fields;
    }

    public boolean isCurrentPlayer() {
        return CurrentPlayer;
    }

    /**
     * Get current Player and return it as easy readable char
     *
     * @return b == Black Player
     * w == White Player
     */
    public char isCurrentPlayerAsChar() {
        if (CurrentPlayer) {
            return 'b';
        } else {
            return 'w';
        }
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        CurrentPlayer = currentPlayer;
    }

    /**
     * Set the current User with a Char, is added to
     * debug the code
     *
     * @param currentPlayer b == Black
     *                      w == White
     */
    public void setCurrentPlayerAsChar(char currentPlayer) {
        switch (currentPlayer) {
            case 'b':
                this.CurrentPlayer = true;
                break;
            case 'w':
                this.CurrentPlayer = false;
                break;
            default:
                //todo return a waring for wrong input
        }
    }

    /**
     * set the next Player
     * if current User is Black than it will be set to white
     * and if the current User is White it will be set to Black
     */
    public void setNextPlayer() {
        this.CurrentPlayer = !this.CurrentPlayer;
    }

    /**
     * set in Field if this field is a possible
     * option to put the stone for the current player
     */
    public void setPossibleFields() {
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                this.fields[i][j].setPossibleForActualPlayer(
                        checkPossibleField(i, j)
                );
            }
        }
    }

    /**
     * set for a field if it possible for the current user to
     * put the stone
     * @param x X-acis from the board, begin with 0
     * @param y Y-acis from the board, begin with 0
     */
    private boolean checkPossibleField(int x, int y) {
        // check if in the field already a stone
        if (!this.fields[x][y].isEmpty()) {
            return false;
        }

        /*
        * +-----------+
        * |           |
        * |   x x x   |
        * |   x S x   |
        * |   x x x   |
        * |           |
        * +-----------+
        */

        int xBoardMin = 0,
                xBoardMax = 0,
                yBoardMin = 0,
                yBoardMax = 0;

        if (x == 0) {
            xBoardMin = x;
        } else {
            xBoardMin = x - 1;
        }

        if (x == fields.length-1) {
            xBoardMax = x;
        } else {
            xBoardMax = x + 1;
        }

        if (y == 0) {
            yBoardMin = y;
        } else {
            yBoardMin = y - 1;
        }

        if (y == fields.length-1) {
            yBoardMax = y;
        } else {
            yBoardMax = y + 1;
        }

        // todo add this method, I'm to tired for this stuff, sorry
        for (int i = xBoardMin; i < xBoardMax; i++) {
            for (int j = yBoardMin; j < yBoardMax; j++) {
                // check if the field is the same state as the current player
                if (isCurrentPlayerAsChar() == fields[i][j].isFieldState()) {
                    return false;
                }
            }
        }

        return false;
    }
}

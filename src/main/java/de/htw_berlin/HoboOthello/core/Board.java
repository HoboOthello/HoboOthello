package de.htw_berlin.HoboOthello.core;

/**
 * Created by laura on 17.11.16.
 */
public class Board {

    public static final int BOARD_SIZE_EIGHT = 8;
    public static final int BOARD_SIZE_SIX = 6;
    public static final int BOARD_SIZE_TEN = 10;

    /**
     * Set the current Player
     * true == Black
     * false == White
     * default is Black cause Black start the game
     */
    // TODO: REFACTOR!! You will blow up your leg otherwise!
    private boolean CurrentPlayer = true;

    private int PossibleFields = 0;

    private Field[][] fields;

    /**
     * Constructor of Board which declares and constructs an two dimensional array of fields
     * Default, Standard size of Board is 8 x 8
     * Small size of Board is 6 x 6
     * Large size of Board is 10 x 10
     * Calls a method to fill the field with default values
     */
    public Board(int i) {
        initBoard(i);
    }

    private void initBoard(int boardSize) {
        if (isBoardSizeAllowed(boardSize)) {
            fields = new Field[boardSize][boardSize];
            fields = fillWithDefaultValues(fields);
        } else {
            throw new IllegalArgumentException("Invalid Board Size");
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
        // TODO: Clean up && Remove Magic Numbers
        fieldsToFill[fieldsToFill.length / 2 - 1][fieldsToFill.length / 2 - 1].setBlack();
        fieldsToFill[fieldsToFill.length / 2][fieldsToFill.length / 2 - 1].setWhite();
        fieldsToFill[fieldsToFill.length / 2 - 1][fieldsToFill.length / 2].setWhite();
        fieldsToFill[fieldsToFill.length / 2][fieldsToFill.length / 2].setBlack();

        return fieldsToFill;
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
    // TODO: REFACTOR! You will blow up your leg otherwise!
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
                throw new IllegalArgumentException("Wrong input. Only b and w are allowed to set!");
        }
    }

    /**
     * Method which sets the next Player
     * If CurrentPlayer is Black than it will be set to White,
     * and if the CurrentPlayer is White it will be set to Black
     */
    public void setNextPlayer() {
        this.CurrentPlayer = !this.CurrentPlayer;
    }

    /**
     * Method which checks Field for a possible
     * options to put down the stone for the CurrentPlayer
     */
    public void setPossibleFields() {
        //todo Steffen
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                this.fields[i][j].setPossibleForCurrentPlayer(
                        checkPossibleField(i, j)
                );
            }
        }
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

    /**
     * Method which checks the possibility for the CurrentPlayer to
     * put down the stone in this field
     *
     * @param x X-axis of the board, begin with 0
     * @param y Y-axis of the board, begin with 0
     */
    // TODO REFACTOR!
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

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0) {
                    boolean possible;
                    possible = checkPossibleFieldDirection(
                            x,
                            y,
                            i,
                            j,
                            0
                    );
                    if (possible) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Recursively check if this field is possible for the CurrentPlayer
     *
     * @param x
     * @param y
     * @param xDirection
     * @param yDirection
     * @param counter
     * @return <ul>
     * <li>True == Possible for the CurrentPlayer</li>
     * <li>False == not Possible for the CurrentPlayer</li>
     * </ul>
     */
    public boolean checkPossibleFieldDirection(int x, int y, int xDirection, int yDirection, int counter) {
        // set current x & y Axis
        x += xDirection;
        y += yDirection;

        if (x < 0 | y < 0 | x >= this.fields.length | y >= this.fields.length) {
            return false;
        }

        /* If fields state is the same as CurrentPlayer && there is at least
         * one field from another Player between than return true */
            if (this.fields[x][y].isFieldState() == this.isCurrentPlayerAsChar()
                    &&
                    counter > 0
                    ) {
                return true;
            }

        // If fields state != CurrentPlayer, call checkPossibleFieldDirection recursively
        if (this.fields[x][y].isFieldState() != this.isCurrentPlayerAsChar()) {
            counter++;
            return checkPossibleFieldDirection(
                    x,
                    y,
                    xDirection,
                    yDirection,
                    counter
            );
        }

        return false;
    }
}

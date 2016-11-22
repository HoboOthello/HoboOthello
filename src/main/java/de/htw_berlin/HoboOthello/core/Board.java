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
     * Get current Player and return it as easy readable char,
     * is designed to debug the code
     * @return b == Black Player
     *         w == White Player
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
}

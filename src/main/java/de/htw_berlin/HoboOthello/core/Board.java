package de.htw_berlin.HoboOthello.core;

/**
 * Created by laura on 17.11.16.
 */
public class Board {

    public static final int STANDARD_BOARD_SIZE_EIGHT = 8;
    public static final int SMALL_BOARD_SIZE_SIX = 6;
    public static final int LARGE_BOARD_SIZE_TEN = 10;

    private Field[][] fields;

    /**
     * Constructor of Board which declares and constructs an two dimensional array of fields
     * Standard size of Board is 8 x 8
     * Calls a method to fill with default values
     */
    public Board(int i) {
        while(true) {
            if (i == 6) {
                fields = new Field[SMALL_BOARD_SIZE_SIX][SMALL_BOARD_SIZE_SIX];
                fields = fillWithDefaultValues(fields);
            }
            if (i == 8) {
                fields = new Field[STANDARD_BOARD_SIZE_EIGHT][STANDARD_BOARD_SIZE_EIGHT];
                fields = fillWithDefaultValues(fields);
            }
            if (i == 10) {
                fields = new Field[LARGE_BOARD_SIZE_TEN][LARGE_BOARD_SIZE_TEN];
                fields = fillWithDefaultValues(fields);
            } else {
                System.out.println("Invalid size for a HoboOthello Board");
            }
        }
    }

    /**
     * Method which fills fields with default values.
     * Default value is:
     * empty = true
     * white = false
     * black = false
     * <p>
     * @param fields fields which are being filled
     * @return fields with default values (true, false, false)
     */
    private Field[][] fillWithDefaultValues(Field[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = new Field(true, false, false);
            }
        }
        return fields;
    }
}

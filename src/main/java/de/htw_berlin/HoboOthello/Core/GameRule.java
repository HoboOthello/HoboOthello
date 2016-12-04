package de.htw_berlin.HoboOthello.Core;

/**
 * Created by Steffen Exler on 29.12.16.
 */
public class GameRule {

    private Field[][] fields;

    /**
     * Default Constructor
     *
     * @param fields the gameboard fields
     */
    public GameRule(Field[][] fields) {
        this.fields = fields;
    }

    /**
     * Set all fields which are possible move for the player with the color playerColor to true in PossibleMove
     *
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @return fields where PossibleMove is set right
     */
    //TODO change name of method
    public void setAllPossibleMoves(StoneColor playerColor) {
        for (int x = 0; x < this.fields.length; x++) {
            for (int y = 0; y < this.fields.length; y++) {
                this.fields[x][y].setPossibleMove(isMoveAllowed(this.fields[x][y], playerColor));
            }
        }
    }

    /**
     * Check if the field in fields[x][y] is a possible move for the PlayerColor
     *
     * @param field       single Field
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    public boolean isMoveAllowed(Field field, StoneColor playerColor) {
        return move(field, playerColor, false);
    }

    /**
     * Set the move in field in fields[x][y] and flip every Stone wich is now owned by
     * this Player
     *
     * @param field       single Field
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    public boolean setMove(Field field, StoneColor playerColor) {
        return move(field, playerColor, true);
    }

    /**
     * The Method for setMove && isMoveAllowed wich do the work.
     *
     * @param field       single Field
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @param flipStones  true --> will flip every stone wich will now owned by other Player
     *                    false --> just return if this field could be use for a possible turn
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    private boolean move(Field field, StoneColor playerColor, boolean flipStones) {
        if (field.isOccupiedByStone()) {
            return false;
        }

        // set the vectors
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x != 0 | y != 0) {
                    if (isPossibleField(field, playerColor, x, y, 0)) {
                        if (flipStones) {
                            flipStones(field, playerColor, x, y);

                        }
                        //TODO BUGFIX STEFFEN have a look at flipstones if all rows are being flipped
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Flip every Stone on this.fields wich is now owned by the currentPlayer, for setTurn
     *
     * @param field       single Field
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     */
    private void flipStones(Field field, StoneColor playerColor, int x_Direction, int y_Direction) {
        do {
            int x = field.getX() + x_Direction;
            int y = field.getY() + y_Direction;

            field = this.fields[x][y];

            field.getStone().setStoneColor(playerColor);
        } while (field.getStone().getStoneColor() != playerColor);
    }

    /**
     * Method for isMoveAllowed, wich check recursively if this move is possible for this direction
     *
     * @param field       single Field
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     * @param counter     how many stones are between from the start stone and the current stone
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    private boolean isPossibleField(Field field, StoneColor playerColor, int x_Direction, int y_Direction, int counter) {
        int x = field.getX() + x_Direction;
        int y = field.getY() + y_Direction;

        field = this.fields[x][y];

        if (x >= 0 && y >= 0 && x < this.fields.length && y < this.fields.length) {
            if (field.isEmpty()) {
                return false;
            }

            if (field.getStone().getStoneColor() == playerColor && counter > 0) {
                return true;
            }

            if (field.getStone().getStoneColor() != playerColor) {
                counter++;
                return isPossibleField(field, playerColor, x_Direction, y_Direction, counter);
            }
        }

        return false;
    }

    public Field[][] getFields() {
        return fields;
    }

    public int getPossibleMoves() {
        int countPossibleMoves = 0;
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                if (this.fields[i][j].isPossibleMove()) {
                    countPossibleMoves++;
                }
            }
        }

        return countPossibleMoves;
    }
}

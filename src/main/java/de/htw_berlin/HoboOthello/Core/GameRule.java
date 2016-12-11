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
     * Method which switches the boolean possibleMove for all fields which are a
     * possible option for the current Player to true
     *
     * @param playerColor Color.BLACK or Color.WHITE
     * @return fields where PossibleMove is set right
     */
    public void changeAllPossibleFieldsToTrue(Color playerColor) {
        for (int x = 0; x < this.fields.length; x++) {
            for (int y = 0; y < this.fields.length; y++) {
                this.fields[x][y].setPossibleMove(isMoveAllowed(this.fields[x][y], playerColor));
            }
        }
    }

    /**
     * Check if the field in fields[x][y] is a possible move for the current Player (which is defined by it's Color)
     *
     * @param field a single Field
     * @param color Color.BLACK or Color.WHITE
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    public boolean isMoveAllowed(Field field, Color color) {
        return move(field, color, false);
    }

    /**
     * Set the move in field in fields[x][y] and flip every Stone which is now owned by
     * this Player
     *
     * @param field a single Field
     * @param color Color.BLACK or Color.WHITE
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    public boolean setMove(Field field, Color color) {
        if (move(field, color, true)) {
            this.fields[field.getX()][field.getY()].setStone(new Stone(color));
            return true;
        } else {
            return false;
        }
    }

    /**
     * The Method for setMove && isMoveAllowed which do the work.
     *
     * @param field      single Field
     * @param color      Color.BLACK or Color.WHITE
     * @param flipStones true --> will flip every stone which will now owned by other Player
     *                   false --> just return if this field could be use for a possible turn
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    private boolean move(Field field, Color color, boolean flipStones) {
        if (field.isOccupiedByStone()) {
            return false;
        }

        // if there is a possible field, set it to true
        boolean possibleField = false;

        // set the direction vectors for methods flipStones && isPossibleField
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x != 0 | y != 0) {
                    if (isPossibleField(field, color, x, y, 0)) {
                        if (flipStones) {
                            flipStones(field, color, x, y);
                            possibleField = true;
                        } else {
                            // if this method called from isMoveAllowed than return true and break this loop
                            return true;
                        }
                    }
                }
            }
        }

        return possibleField;
    }

    /**
     * Flip every Stone on this.fields wich is now owned by the currentPlayer, for setTurn
     *
     * @param field       single Field
     * @param color       Color.BLACK or Color.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     */
    private void flipStones(Field field, Color color, int x_Direction, int y_Direction) {
        int x = field.getX() + x_Direction;
        int y = field.getY() + y_Direction;

        while (this.fields[x][y].getStone().getColor() != color) {
            this.fields[x][y].setStone(new Stone(color));

            x = x + x_Direction;
            y = y + y_Direction;
        }
    }

    /**
     * Method for isMoveAllowed, which check recursively if this move is possible for this direction
     *
     * @param field       single Field
     * @param color       Color.BLACK or Color.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     * @param counter     how many stones are between from the start stone and the current stone
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    private boolean isPossibleField(Field field, Color color, int x_Direction, int y_Direction, int counter) {
        int x = field.getX() + x_Direction;
        int y = field.getY() + y_Direction;

        if (x >= 0 && y >= 0 && x < this.fields.length && y < this.fields.length) {
            field = this.fields[x][y];

            if (field.isEmpty()) {
                return false;
            }

            if (field.getStone().getColor() == color && counter > 0) {
                return true;
            }

            if (field.getStone().getColor() != color) {
                counter++;
                return isPossibleField(field, color, x_Direction, y_Direction, counter);
            }
        }

        return false;
    }

    public Field[][] getFields() {
        return fields;
    }

    /**
     * Count every possible move for the current player
     *
     * @return all possible moves for the current player in INT
     */
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

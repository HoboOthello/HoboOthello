package de.htw_berlin.HoboOthello.Core;

/**
 * Created by Steffen Exler on 29.12.16.
 */
public class IsMoveAllowedRule extends GameRule {

    /**
     * Set all fields wich are possible move for the player with the color PlayerColor to true in PossibleMove
     *
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @param fields The same fields like in Board.fields
     * @return fields where PossibleMove is set right
     */
    public Field[][] getAllPossibleMoves(StoneColor PlayerColor, Field[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j].setPossibleMove(IsMoveAllowed(i, j, PlayerColor, fields));
            }
        }
        return fields;
    }

    /**
     * Check if the field in fields[x][y] is a possible move for the PlayerColor
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @param fields The same fields like in Board.fields
     * @return true == the move is possible for this color
     *         false == the move is not possible for this color
     */
    public boolean IsMoveAllowed(int x, int y, StoneColor PlayerColor, Field[][] fields) {
        if (fields[x][y].isOccupiedByStone()) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 | j != 0) {
                    if (isPossibleField(x, y, PlayerColor, fields, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Method for IsMoveAllowed, wich check recursively if this move is possible for this direction
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @param fields The same fields like in Board.fields
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     * @param counter how many stones are between from the start stone and the current stone
     * @return true == the move is possible for this color
     *         false == the move is not possible for this color
     */
    private boolean isPossibleField(int x, int y, StoneColor PlayerColor, Field[][] fields, int x_Direction, int y_Direction, int counter) {
        x = x + x_Direction;
        y = y + y_Direction;

        if (x >= 0 && y >= 0 && x < fields.length && y < fields.length) {
            if (fields[x][y].isEmpty()) {
                return false;
            }

            if (fields[x][y].getStone().getStoneColor() == PlayerColor && counter > 0) {
                return true;
            }

            if (fields[x][y].getStone().getStoneColor() != PlayerColor) {
                counter++;
                return isPossibleField(x, y, PlayerColor, fields, x_Direction, y_Direction, counter);
            }
        }

        return false;
    }
}

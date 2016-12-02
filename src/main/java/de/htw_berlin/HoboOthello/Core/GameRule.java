package de.htw_berlin.HoboOthello.Core;

/**
 * Created by Steffen Exler on 29.12.16.
 */
public class GameRule {

    private Field[][] fields;

    /**
     * Default Constructor
     * @param fields the gameboard fields
     */
    public GameRule(Field[][] fields) {
        this.fields = fields;
    }

    /**
     * Set all fields wich are possible move for the player with the color PlayerColor to true in PossibleMove
     *
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @return fields where PossibleMove is set right
     */
    public void setAllPossibleMoves(StoneColor PlayerColor) {
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                this.fields[i][j].setPossibleMove(IsMoveAllowed(i, j, PlayerColor));
            }
        }
    }

    /**
     * Check if the field in fields[x][y] is a possible move for the PlayerColor
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @return true == the move is possible for this color
     *         false == the move is not possible for this color
     */
    public boolean IsMoveAllowed(int x, int y, StoneColor PlayerColor) {
        return move(x, y, PlayerColor, false);
    }

    /**
     * Set the move in field in fields[x][y] and flip every Stone wich is now owned by
     * this Player
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @return true == the move is possible for this color
     *         false == the move is not possible for this color
     */
    public boolean setMove(int x, int y, StoneColor PlayerColor) {
        return move(x, y, PlayerColor, true);
    }

    /**
     * The Method for setMove && IsMoveAllowed wich do the work.
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @param flipStones true --> will flip every stone wich will now owned by other Player
     *                   false --> just return if this field could be use for a possible turn
     * @return true == the move is possible for this color
     *         false == the move is not possible for this color
     */
    private boolean move(int x, int y, StoneColor PlayerColor, boolean flipStones) {
        if (this.fields[x][y].isOccupiedByStone()) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 | j != 0) {
                    if (isPossibleField(x, y, PlayerColor, i, j, 0)) {
                        if (flipStones) {
                            flipStones(x, y, PlayerColor, i, j);
                        }
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
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     */
    private void flipStones(int x, int y, StoneColor PlayerColor, int x_Direction, int y_Direction) {
        do {
            x = x + x_Direction;
            y = y + y_Direction;

            this.fields[x][y].getStone().setStoneColor(PlayerColor);
        } while (this.fields[x][y].getStone().getStoneColor() != PlayerColor);
    }

    /**
     * Method for IsMoveAllowed, wich check recursively if this move is possible for this direction
     *
     * @param x x-axis on the board
     * @param y y-axis on the board
     * @param PlayerColor StoneColor.BLACK or StoneColor.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     * @param counter how many stones are between from the start stone and the current stone
     * @return true == the move is possible for this color
     *         false == the move is not possible for this color
     */
    private boolean isPossibleField(int x, int y, StoneColor PlayerColor, int x_Direction, int y_Direction, int counter) {
        x = x + x_Direction;
        y = y + y_Direction;

        if (x >= 0 && y >= 0 && x < this.fields.length && y < this.fields.length) {
            if (this.fields[x][y].isEmpty()) {
                return false;
            }

            if (this.fields[x][y].getStone().getStoneColor() == PlayerColor && counter > 0) {
                return true;
            }

            if (this.fields[x][y].getStone().getStoneColor() != PlayerColor) {
                counter++;
                return isPossibleField(x, y, PlayerColor, x_Direction, y_Direction, counter);
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

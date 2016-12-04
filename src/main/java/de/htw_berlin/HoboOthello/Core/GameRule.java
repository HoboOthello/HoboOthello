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
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                this.fields[i][j].setPossibleMove(isMoveAllowed(i, j, playerColor));
            }
        }
    }

    /**
     * Check if the field in fields[x][y] is a possible move for the PlayerColor
     *
     * @param x           x-axis on the board
     * @param y           y-axis on the board
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    public boolean isMoveAllowed(int x, int y, StoneColor playerColor) {
        return move(x, y, playerColor, false);
    }

    /**
     * Set the move in field in fields[x][y] and flip every Stone wich is now owned by
     * this Player
     *
     * @param x           x-axis on the board
     * @param y           y-axis on the board
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    public boolean setMove(int x, int y, StoneColor playerColor) {
        return move(x, y, playerColor, true);
    }

    /**
     * The Method for setMove && isMoveAllowed wich do the work.
     *
     * @param x           x-axis on the board
     * @param y           y-axis on the board
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @param flipStones  true --> will flip every stone wich will now owned by other Player
     *                    false --> just return if this field could be use for a possible turn
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    private boolean move(int x, int y, StoneColor playerColor, boolean flipStones) {
        if (this.fields[x][y].isOccupiedByStone()) {
            return false;
        }

        //TODO STEFFEN refactoring for-Loop
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 | j != 0) {
                    if (isPossibleField(x, y, playerColor, i, j, 0)) {
                        if (flipStones) {
                            flipStones(x, y, playerColor, i, j);

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
     * @param x           x-axis on the board
     * @param y           y-axis on the board
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     */
    private void flipStones(int x, int y, StoneColor playerColor, int x_Direction, int y_Direction) {
        do {
            x = x + x_Direction;
            y = y + y_Direction;

            this.fields[x][y].getStone().setStoneColor(playerColor);
        } while (this.fields[x][y].getStone().getStoneColor() != playerColor);
    }

    /**
     * Method for isMoveAllowed, wich check recursively if this move is possible for this direction
     *
     * @param x           x-axis on the board
     * @param y           y-axis on the board
     * @param playerColor StoneColor.BLACK or StoneColor.WHITE
     * @param x_Direction the direction on the x-axis
     * @param y_Direction the direction on the y-axis
     * @param counter     how many stones are between from the start stone and the current stone
     * @return true == the move is possible for this color
     * false == the move is not possible for this color
     */
    private boolean isPossibleField(int x, int y, StoneColor playerColor, int x_Direction, int y_Direction, int counter) {
        x = x + x_Direction;
        y = y + y_Direction;

        if (x >= 0 && y >= 0 && x < this.fields.length && y < this.fields.length) {
            if (this.fields[x][y].isEmpty()) {
                return false;
            }

            if (this.fields[x][y].getStone().getStoneColor() == playerColor && counter > 0) {
                return true;
            }

            if (this.fields[x][y].getStone().getStoneColor() != playerColor) {
                counter++;
                return isPossibleField(x, y, playerColor, x_Direction, y_Direction, counter);
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

package de.htw_berlin.HoboOthello.Core;

/**
 * Created by laura on 24.11.16.
 */
public class IsMoveAllowedRule extends GameRule {

    public IsMoveAllowedRule() {
    }

    public Field[][] getAllPossibleMoves(StoneColor PlayerColor, Field[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j].setPossibleMove(IsMoveAllowed(i, j, PlayerColor, fields));
            }
        }
        return fields;
    }

    public boolean IsMoveAllowed(int x, int y, StoneColor PlayerColor, Field[][] fields) {
        if (fields[x][y].isOccupiedByStone()) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 | j != 0) {
                    if (isPossibleField(x, y, PlayerColor, fields, i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isPossibleField(int x, int y, StoneColor PlayerColor, Field[][] fields, int x_Direction, int y_Direction) {
        int counter = 0;

        x = x + x_Direction;
        y = y + y_Direction;

        while (x >= 0 && y >= 0 && x < fields.length && y < fields.length) {
            if (fields[x][y].isEmpty()) {
                return false;
            }

            if (fields[x][y].getStone().getStoneColor() == PlayerColor && counter > 0) {
                return true;
            }

            if (fields[x][y].getStone().getStoneColor() != PlayerColor) {
                counter++;
            }

            x = +x_Direction;
            y = +y_Direction;
        }

        return false;
    }
}

package de.htw_berlin.HoboOthello.Core;


/**
 * Created by laura on 17.11.16.
 */
public class Field {

    /**
     * A field can be occupied by a stone
     */
    private Stone stone;

    /**
     * Set if this field for the current player a possible move
     */
    //TODO in order for this to work, we need the implemented gamerules! @Steffen
    private boolean possibleMove;


    /**
     * Default constructor for empty field
     */
    public Field() {
    }

    public void setStone(Stone stoneToSet) {
        this.stone = stoneToSet;
    }

    public Stone getStone() {
        return this.stone;
    }

    public void setEmpty() {
        this.stone = null;
    }

    public boolean isEmpty() {
        return stone == null;
    }

    public boolean isOccupiedByStone() {
        if (this.stone != null) {
            return true;
        }
        if (this.stone == null) {
            return false;
        } else {
            throw new IllegalArgumentException("Illegal state of field!");
        }
    }

    @Override
    public String toString() {
        return "Field{" +
                "stone=" + stone +
                '}';
    }

    //TODO in order for this to work, we need the implemented gamerules! @Steffen
    public boolean isPossibleMove() {
        return possibleMove;
    }

    public void setPossibleMove(boolean possibleMove) {
        this.possibleMove = possibleMove;
    }
}

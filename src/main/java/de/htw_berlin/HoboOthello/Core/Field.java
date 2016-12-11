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
     * Int x and y describe the location of the field on the board
     */
    private int x;
    private int y;

    /**
     * Set if this field for the current player a possible move
     */
    private boolean possibleMove;


    /**
     * Default constructor for empty field
     */
    public Field() {

    }

    /**
     * Constructor for field with exact location on the board
     */
    public Field(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setStone(Stone stoneToSet) {
        this.stone = stoneToSet;
    }

    public Stone getStone() {
        return this.stone;
    }

    //TODO: methode schreiben die den spieler den letzten Zug rückgängig machen lässt (achte auch auf stones die wieder umgedreht werden müssen)
    //todo unötige funktion, neues spiel -> neues Board!
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



    public boolean isPossibleMove() {
        return possibleMove;
    }


    /**
     * Set this field to a possible move for the current Player
     * @param possibleMove
     */
    public void setPossibleMove(boolean possibleMove) {
        this.possibleMove = possibleMove;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Field{" +
                "stone=" + stone +
                ", x=" + x +
                ", y=" + y +
                ", possibleMove=" + possibleMove +
                '}';
    }
}

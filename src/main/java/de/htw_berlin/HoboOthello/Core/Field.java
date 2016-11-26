package de.htw_berlin.HoboOthello.Core;


/**
 * Created by laura on 17.11.16.
 */
public class Field {

    private Stone stone;

    /**
     * Default constructor for empty field
     */
    public Field() { }

    public boolean isEmpty() {
        return stone == null;
    }

    public boolean isOccupiedByStone(){
        if (this.stone != null) {
            return true;
        } else {
            throw new IllegalArgumentException("Illegal state of field!");
        }
    }

    public void setStone(Stone stoneToSet){
        this.stone = stoneToSet;
    }

    public void setEmpty() {
        this.stone = null;
    }

    //TODO: getter

    /**
     * Override toString Method to show the current field state as a string
     *
     * @return the current field state "field is empty" or "field is occupied by stone"
     * @throws IllegalArgumentException if the the current field state could not be identified as
     * empty or occupied by a stone
     */
    @Override
    public String toString() {
        if (isEmpty()){
            return "field is empty";
        }
        if (isOccupiedByStone()) {
            return "field is occupied by stone";
        } else {
            throw new IllegalArgumentException("Illegal state of field!");
        }
    }




}

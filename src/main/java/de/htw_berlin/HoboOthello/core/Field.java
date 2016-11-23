package de.htw_berlin.HoboOthello.core;


/**
 * Created by laura on 17.11.16.
 */
public class Field {

    private boolean empty;
    private boolean white;
    private boolean black;
    private boolean possibleForCurrentPlayer;

    /**
     * Default constructor for empty field
     */
    public Field() {
        setEmpty();
    }

    /**
     * Field constructor with three boolean values
     * @param empty if field is empty
     * @param white if field is occupied with white
     * @param black if field is occupied with black
     */
    public Field(boolean empty, boolean white, boolean black) {
        int temp = 0;

        if (empty) {
            temp++;
            setEmpty();
        }

        if (white) {
            temp++;
            setWhite();
        }

        if (black) {
            temp++;
            setBlack();
        }

        if (0 == temp || temp > 1) {
            throw new IllegalArgumentException("Only one true boolean is allowed. No more, no less.");
        }
    }

    /**
     * Constructor of field with state
     * Add an field with just one possible condition
     * b == Black
     * w == White
     * everything else == empty
     *
     * @param FieldState b for Black field
     *                   w for White field
     *                   e for empty field
     */
    public Field(char FieldState) {
        switch (FieldState) {
            case 'b':
                setBlack();
                break;
            case 'w':
                setWhite();
                break;
            case 'e':
                setEmpty();
            default:
                throw new IllegalArgumentException("Only b, w and e are allowed!");
        }
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isWhite() {
        return this.white;
    }

    public boolean isBlack() {
        return this.black;
    }

    /**
     * Method which returns the state of the field
     * @return state of the field
     * <ul>
     *     <li>e == empty</li>
     *     <li>w == while</li>
     *     <li>b == black</li>
     * </ul>
     *
     */
    public char isFieldState() {
        if (this.empty) {
            return 'e';
        } else if (this.white) {
            return 'w';
        } else {
            return 'b';
        }
    }

    /**
     * show the current field state as an String
     *
     * @return the current field state "empty", "white", "black"
     */
    @Override
    public String toString() {
        if (this.empty) {
            return "empty";
        } else if (this.white) {
            return "white";
        } else {
            return "black";
        }
    }

    public void setEmpty() {
        this.empty = true;
        this.white = false;
        this.black = false;
    }

    public void setWhite() {
        this.empty = false;
        this.white = true;
        this.black = false;
    }

    public void setBlack() {
        this.empty = false;
        this.white = false;
        this.black = true;
    }

    public boolean isPossibleForCurrentPlayer() {
        return possibleForCurrentPlayer;
    }

    public void setPossibleForCurrentPlayer(boolean possibleForCurrentPlayer) {
        this.possibleForCurrentPlayer = possibleForCurrentPlayer;
    }
}

package de.htw_berlin.HoboOthello.core;


/**
 * Created by laura on 17.11.16.
 */
public class Field {

    private boolean empty;
    private boolean white;
    private boolean black;

    /**
     * set an empty field
     */
    public Field() {
        setEmpty();
    }

    public Field(boolean empty, boolean white, boolean black) {
        int tmp = 0;

        if (empty) {
            tmp++;
            setEmpty();
        }

        if (white) {
            tmp++;
            setWhite();
        }

        if (black) {
            tmp++;
            setBlack();
        }

        //todo show warning if (0 == tmp || tmp > 1)
    }

    /**
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
            default:
                setEmpty();
        }
    }

    public boolean possibleForActualPlayer() {
        //Methode selbst muss noch geschrieben werden, return true if possible, false if not
        //hierfür brauche wir implentierte Spielregeln
        return true;
    }

    //getter und setter schon mal bereitgestellt, werden noch nicht benutzt!
    public boolean getEmpty() {
        return this.empty;
    }

    public boolean getWhite() {
        return this.white;
    }

    public boolean getBlack() {
        return this.black;
    }

    /**
     * get the current field state as an String
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
}

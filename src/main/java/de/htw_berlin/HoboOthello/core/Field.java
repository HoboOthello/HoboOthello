package de.htw_berlin.HoboOthello.core;


/**
 * Created by laura on 17.11.16.
 */
public class Field {

    private boolean empty;
    private boolean white;
    private boolean black;

    public Field(boolean empty, boolean white, boolean black) {
        this.empty = empty;
        this.white = white;
        this.black = black;
    }

    public boolean possibleForActualPlayer() {
        //Methode selbst muss noch geschrieben werden, return true if possible, false if not
        //hierfür brauche wir implentierte Spielregeln
        return true;
    }

    //getter und setter schon mal bereitgestellt, werden noch nicht benutzt!
    public boolean isEmpty() {
        return empty;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isBlack() {
        return black;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }


}

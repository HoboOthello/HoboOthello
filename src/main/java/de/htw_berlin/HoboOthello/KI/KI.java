package de.htw_berlin.HoboOthello.KI;

import de.htw_berlin.HoboOthello.Core.*;

import java.util.HashMap;


public class KI extends Player{

    private Color kiColor;

    private Board board;
    private Field field;
    private GameRule gameRule;

    public KI(Color kiColor) {
        this.kiColor = kiColor;
    }

    public Field setMove(Board board) {
        //return Field fieldToSetMove;
        return null;
    }

    private HashMap listPossibleMoves(){
        HashMap listOfPossibleMoves = new HashMap();
        int max = gameRule.getPossibleMoves();
        if (gameRule.isMoveAllowed(field, kiColor)) {

            for (int i = 0; i >= max; i++) {
                listOfPossibleMoves.put(i, this.field);
            }
        }
        //return HashMap;
        return null;
    }

}

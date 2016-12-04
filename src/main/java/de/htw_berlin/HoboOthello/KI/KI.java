package de.htw_berlin.HoboOthello.KI;

import de.htw_berlin.HoboOthello.Core.*;

import java.awt.*;
import java.util.HashMap;


public class KI extends Player{


    private Board board;
    private Field field;
    private GameRule gameRule;

    public KI(PlayerColor kiColor) {

    }

    public Field setMove(Board board) {
        //return Field fieldToSetMove;
        return null;
    }

    private HashMap listPossibleMoves(Board board){
        HashMap listOfPossibleMoves = new HashMap();
        int max = gameRule.setAllPossibleMoves();//PossibleMoves();
        gameRule.
        for (int i = 0; i >= max; i++){
            listOfPossibleMoves.put(i, this.field);
        }
        //return HashMap;
        return null;
    }

}

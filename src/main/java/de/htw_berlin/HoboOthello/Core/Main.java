package de.htw_berlin.HoboOthello.Core;

/**
 * Created by Steffen Exler on 16.11.16.
 */
public class Main {

    public static void main(String[] args) {

        Board boardStandard = new Board(8);

        IsMoveAllowedRule move = new IsMoveAllowedRule();

        System.out.println(
                move.IsMoveAllowed(3, 5, StoneColor.BLACK, boardStandard.isFields())
        );

        //boardStandard.setFields(move.getAllPossibleMoves(StoneColor.BLACK, boardStandard.isFields()));
        System.out.println(boardStandard.getBoardOverview());



        Board boardSmall = new Board(6);
        Board boardLarge = new Board(10);

        System.out.println(boardStandard);

    }
}

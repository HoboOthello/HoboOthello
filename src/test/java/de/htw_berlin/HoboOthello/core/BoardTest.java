package de.htw_berlin.HoboOthello.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Steffen Exler on 23.11.16.
 */
public class BoardTest {
    @Test
    public void setPossibleFields() throws Exception {
        Board boardSmall = new Board(6);
        boardSmall.setPossibleFields();
        System.out.println(boardSmall.BoardOverview());
    }

    @Test
    public void boardOverview() throws Exception {
        String temp;

        Board boardSmall = new Board(6);
        Board boardStandard = new Board(8);
        Board boardLarge = new Board(10);

        temp = String.format("   0 1 2 3 4 5%n") +
                String.format("  +------------+%n") +
                String.format(" 0|            |%n") +
                String.format(" 1|            |%n") +
                String.format(" 2|    b w     |%n") +
                String.format(" 3|    w b     |%n") +
                String.format(" 4|            |%n") +
                String.format(" 5|            |%n") +
                String.format("  +------------+");

        assertEquals(temp, boardSmall.BoardOverview());

        temp = String.format("   0 1 2 3 4 5 6 7%n") +
                String.format("  +----------------+%n") +
                String.format(" 0|                |%n") +
                String.format(" 1|                |%n") +
                String.format(" 2|                |%n") +
                String.format(" 3|      b w       |%n") +
                String.format(" 4|      w b       |%n") +
                String.format(" 5|                |%n") +
                String.format(" 6|                |%n") +
                String.format(" 7|                |%n") +
                String.format("  +----------------+");

        assertEquals(temp, boardStandard.BoardOverview());

        temp = String.format("   0 1 2 3 4 5 6 7 8 9%n") +
                String.format("  +--------------------+%n") +
                String.format(" 0|                    |%n") +
                String.format(" 1|                    |%n") +
                String.format(" 2|                    |%n") +
                String.format(" 3|                    |%n") +
                String.format(" 4|        b w         |%n") +
                String.format(" 5|        w b         |%n") +
                String.format(" 6|                    |%n") +
                String.format(" 7|                    |%n") +
                String.format(" 8|                    |%n") +
                String.format(" 9|                    |%n") +
                String.format("  +--------------------+");

        assertEquals(temp, boardLarge.BoardOverview());
    }

}
package de.htw_berlin.HoboOthello.Core;

import java.util.Random;

/**
 * Created by fubu on 15.12.16.
 */
public class HoboMode extends GameRule {

    HobeModeType hobeModeType;

    /**
     * Default Constructor
     *
     * @param fields the gameboard fields
     */
    public HoboMode(Field[][] fields) {
        super(fields);
    }

    public Field actionRandomMode() {
        this.hobeModeType = HobeModeType.randomHobeModeType();

        Field field = null;

        switch (this.hobeModeType) {
            /*
            case BOMBERMAN:
                field = actionBomberman();
                break;
                */
            case TETRIS:
                field = actionTetris();
                break;

        }

        return field;
    }


    /**
     * Destroy random 9 Fields and set them to emtpy.
     *
     *  OOO
     *  OXO
     *  OOO
     *
     * @return Middle Field of the explostion
     */
    private Field actionBomberman() {
        Random ran = new Random();

        int fieldLength = this.getFields().length - 1;

        // generate random field
        Field bombField = new Field(ran.nextInt(fieldLength), ran.nextInt(fieldLength));

        // set every field, included the bomb to empty field
        for (int x_direction = -1; x_direction < 1; x_direction++) {
            for (int y_direction = -1; y_direction < 1; y_direction++) {
                int x = bombField.getX() + x_direction;
                int y = bombField.getY() + y_direction;

                if (x >= 0 && x < fieldLength &&
                        y >= 0 && y < fieldLength) {
                    this.getFields()[x][y].setEmpty();
                }
            }
        }

        return bombField;
    }

    /**
     * Destroy the last line of the gameboard
     *
     * @return a Field with the y-axe of the line of destruktion
     */
    private Field actionTetris() {

        int fieldLength = this.getFields().length;

        for (int x = 0; x < fieldLength; x++) {
            for (int y = fieldLength - 1; y > 1; y--) {
                this.getFields()[x][y].setStone(
                        this.getFields()[x][y-1].getStone()
                );
            }
        }

        for (int x = 0; x < fieldLength; x++) {
            this.getFields()[x][0].setEmpty();
        }

        return new Field(9, fieldLength);
    }

    public HobeModeType getHobeModeType() {
        return hobeModeType;
    }
}

package de.htw_berlin.HoboOthello.Core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by fubu on 15.12.16.
 */
public enum HobeModeType {
    TETRIS;
    // BOMBERMAN, PACMAN, TETRIS;

    private static final List<HobeModeType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static HobeModeType randomHobeModeType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

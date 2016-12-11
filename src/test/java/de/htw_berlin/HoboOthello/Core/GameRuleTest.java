package de.htw_berlin.HoboOthello.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fubu on 11.12.16.
 */
public class GameRuleTest {
    @Test
    public void changeAllPossibleFieldsToTrue() throws Exception {

    }

    @Test
    public void isMoveAllowed() throws Exception {

    }

    @Test
    public void setMove() throws Exception {

    }

    @Test
    public void getPossibleMoves() throws Exception {
        String content;

        Field[][] fields;
        Gson gson = new GsonBuilder().create();

        content = "[[{\"x\":0,\"y\":0,\"possibleMove\":false},{\"x\":0,\"y\":1,\"possibleMove\":false},{\"x\":0,\"y\":2,\"possibleMove\":false},{\"x\":0,\"y\":3,\"possibleMove\":true},{\"x\":0,\"y\":4,\"possibleMove\":true},{\"x\":0,\"y\":5,\"possibleMove\":false},{\"x\":0,\"y\":6,\"possibleMove\":false},{\"x\":0,\"y\":7,\"possibleMove\":false}],[{\"x\":1,\"y\":0,\"possibleMove\":false},{\"x\":1,\"y\":1,\"possibleMove\":false},{\"x\":1,\"y\":2,\"possibleMove\":false},{\"x\":1,\"y\":3,\"possibleMove\":false},{\"stone\":{\"stoneColor\":\"WHITE\"},\"x\":1,\"y\":4,\"possibleMove\":false},{\"x\":1,\"y\":5,\"possibleMove\":true},{\"x\":1,\"y\":6,\"possibleMove\":false},{\"x\":1,\"y\":7,\"possibleMove\":false}],[{\"x\":2,\"y\":0,\"possibleMove\":false},{\"x\":2,\"y\":1,\"possibleMove\":false},{\"x\":2,\"y\":2,\"possibleMove\":false},{\"x\":2,\"y\":3,\"possibleMove\":true},{\"stone\":{\"stoneColor\":\"WHITE\"},\"x\":2,\"y\":4,\"possibleMove\":false},{\"stone\":{\"stoneColor\":\"BLACK\"},\"x\":2,\"y\":5,\"possibleMove\":false},{\"stone\":{\"stoneColor\":\"BLACK\"},\"x\":2,\"y\":6,\"possibleMove\":false},{\"x\":2,\"y\":7,\"possibleMove\":false}],[{\"x\":3,\"y\":0,\"possibleMove\":false},{\"x\":3,\"y\":1,\"possibleMove\":false},{\"x\":3,\"y\":2,\"possibleMove\":false},{\"stone\":{\"stoneColor\":\"BLACK\"},\"x\":3,\"y\":3,\"possibleMove\":false},{\"stone\":{\"stoneColor\":\"WHITE\"},\"x\":3,\"y\":4,\"possibleMove\":false},{\"x\":3,\"y\":5,\"possibleMove\":true},{\"x\":3,\"y\":6,\"possibleMove\":false},{\"x\":3,\"y\":7,\"possibleMove\":false}],[{\"x\":4,\"y\":0,\"possibleMove\":false},{\"x\":4,\"y\":1,\"possibleMove\":false},{\"x\":4,\"y\":2,\"possibleMove\":true},{\"stone\":{\"stoneColor\":\"WHITE\"},\"x\":4,\"y\":3,\"possibleMove\":false},{\"stone\":{\"stoneColor\":\"BLACK\"},\"x\":4,\"y\":4,\"possibleMove\":false},{\"x\":4,\"y\":5,\"possibleMove\":false},{\"x\":4,\"y\":6,\"possibleMove\":false},{\"x\":4,\"y\":7,\"possibleMove\":false}],[{\"x\":5,\"y\":0,\"possibleMove\":false},{\"x\":5,\"y\":1,\"possibleMove\":false},{\"x\":5,\"y\":2,\"possibleMove\":true},{\"x\":5,\"y\":3,\"possibleMove\":true},{\"x\":5,\"y\":4,\"possibleMove\":false},{\"x\":5,\"y\":5,\"possibleMove\":false},{\"x\":5,\"y\":6,\"possibleMove\":false},{\"x\":5,\"y\":7,\"possibleMove\":false}],[{\"x\":6,\"y\":0,\"possibleMove\":false},{\"x\":6,\"y\":1,\"possibleMove\":false},{\"x\":6,\"y\":2,\"possibleMove\":false},{\"x\":6,\"y\":3,\"possibleMove\":false},{\"x\":6,\"y\":4,\"possibleMove\":false},{\"x\":6,\"y\":5,\"possibleMove\":false},{\"x\":6,\"y\":6,\"possibleMove\":false},{\"x\":6,\"y\":7,\"possibleMove\":false}],[{\"x\":7,\"y\":0,\"possibleMove\":false},{\"x\":7,\"y\":1,\"possibleMove\":false},{\"x\":7,\"y\":2,\"possibleMove\":false},{\"x\":7,\"y\":3,\"possibleMove\":false},{\"x\":7,\"y\":4,\"possibleMove\":false},{\"x\":7,\"y\":5,\"possibleMove\":false},{\"x\":7,\"y\":6,\"possibleMove\":false},{\"x\":7,\"y\":7,\"possibleMove\":false}]]";
        fields = gson.fromJson(content, Field[][].class);

        GameRule move = new GameRule(fields);
        assertEquals(8, move.getPossibleMoves());
    }

}
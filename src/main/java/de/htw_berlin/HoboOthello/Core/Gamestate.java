package de.htw_berlin.HoboOthello.Core;

/**
 * Created by Steffen Exler on 29.11.16.
 * <p>
 * Set if the Game is running or finished (stop)
 * RUNNING --> Game is on
 * STOP --> Game is at the end and need to create a new game or close the Program
 */
public enum Gamestate {
    RUNNING, STOP
}
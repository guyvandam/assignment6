package constants;
/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class Constants {
    // I added this file for keeping all the constants and semi-constants (the paddle speed or width for example),
    // instead of passing these values to object as input to the constructor or making them data members.
    // this way I can make my code far more generic, changing the value of each of these values in all of the using
    // classes instantly.
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final int WIDTH_OR_HEIGHT = 20; // corner blocks width or height.

    public static final int PADDLE_SPEED = 7; //default paddle speed.
    public static final int PADDLE_WIDTH = 100; //default paddle width.
    public static final int PADDLE_HEIGHT = 20;

    public static final int BALL_SIZE = 4;
    public static final int PIXELS_BETWEEN_BALL_AND_PADDLE = 5;
}

package constants;

public class Constants {
    // I added this file for keeping all the constants and semi-constants (the paddle speed or width for example),
    // instead of passing these values to object as input to the constructor or making them data members.
    // this way I can make my code far more generic, changing the value of each of these values in all of the using
    // classes instantly.
    public static final int guiWidth = 800;
    public static final int guiHeight = 600;
    public static final int widthOrHeight = 20; // corner blocks width or height.

    public static final int paddleSpeed = 10; //default paddle speed.
    public static final int paddleWidth = 80; //default paddle width.
    public static final int paddleHeight = 20;
}

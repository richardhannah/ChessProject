package com.logicnow.hiring;

/**
 * Represents a position on a Chessboard
 * x represents the column (or file)
 * y represents the row (or rank)
 */
public class Position {

    private final int x;
    private final int y;

    /**
     * Instantiates a new Position.
     *
     * @param x the x
     * @param y the y
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }



}

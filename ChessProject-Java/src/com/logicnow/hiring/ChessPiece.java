package com.logicnow.hiring;

import java.util.Observable;
import java.util.Optional;

/**
 * Abstract base class representing a Chess piece
 */
abstract class ChessPiece extends Observable {

    private final PieceColor pieceColor;
    /**
     * The Position.
     */
    Position position;

    /**
     * Instantiates a new Chess piece.
     *
     * @param pieceColor the piece color
     */
    ChessPiece(PieceColor pieceColor) {

        this.pieceColor = pieceColor;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets position and notifies observers of the ChessPiece that the position has changed
     *
     * @param newPosition the new position
     */
    public void setPosition(Position newPosition) {

        Optional<Position> oldPosition = Optional.ofNullable(position);
        this.position = newPosition;
        setChanged();
        notifyObservers(oldPosition);
    }

    /**
     * Gets piece color.
     *
     * @return the piece color
     */
    public PieceColor getPieceColor() {
        return this.pieceColor;
    }


    /**
     * Moves the chess piece. MovementType will determine whether this is a normal move
     * or a move to capture an enemy piece
     *
     * @param movementType the movement type
     * @param newPosition  the new position
     */
    public void Move(MovementType movementType, Position newPosition) {
        switch (movementType){
            case MOVE:
                doMove(newPosition);
                break;
            case CAPTURE:
                doCapture(newPosition);
                break;
        }
    }

    /**
     * Abstract normal movement method - override to set specific behaviour
     * of ChessPiece Subtypes
     *
     * @param newPosition the new position
     */
    protected abstract void doMove(Position newPosition);

    /**
     * Abstract capture method - override to set specific behaviour
     * of ChessPiece Subtypes
     *
     * @param newPosition the new position
     */
    protected abstract void doCapture(Position newPosition);

    /**
     * Abstract movement rules method - override to set specific behaviour
     * of ChessPiece Subtypes. This method should return true if the new Position
     * is a valid chess move for the specific ChessPiece subtype.
     *
     * @param newPosition the new position
     * @return the boolean
     */
    protected abstract boolean isValidMove(Position newPosition);

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    private String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: %2$d %1$sCurrent Y: %3$d %1$sPiece Color: %3$s", eol, position.getX(), position.getY(), pieceColor);
    }
}

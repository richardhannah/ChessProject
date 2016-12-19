package com.logicnow.hiring;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Implementation of ChessPiece. defines movement rules for a Pawn ChessPiece
 */
public class Pawn extends ChessPiece{

    /**
     * Instantiates a new Pawn.
     *
     * @param pieceColor the piece color
     */
    public Pawn(PieceColor pieceColor){
        super(pieceColor);
    }

    @Override
    protected void doMove(Position newPosition) {
        if(isValidMove(newPosition)) {
            setPosition(newPosition);
        }
    }

    @Override
    protected void doCapture(Position newPosition) {
        throw new NotImplementedException();
    }

    @Override
    protected boolean isValidMove(Position newPosition) {

        boolean sameColumn = newPosition.getX() == position.getX();
        boolean oneSquareMoved = getPieceColor() == PieceColor.WHITE ? newPosition.getY() == position.getY() + 1
                : newPosition.getY() == position.getY() -1;

        return sameColumn && oneSquareMoved;
    }
}

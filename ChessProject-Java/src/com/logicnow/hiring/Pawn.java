package com.logicnow.hiring;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Pawn extends ChessPiece{

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

package com.logicnow.hiring;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Pawn extends ChessPiece{

    public Pawn(PieceColor pieceColor){
        super(pieceColor);
    }

    @Override
    protected void doMove(Position newPosition) {
        if(newPosition.getX() == position.getX()) {
            chessBoard.Update(this,position,newPosition);
            this.position = newPosition;
        }
    }

    @Override
    protected void doCapture(Position newPosition) {
        throw new NotImplementedException();
    }
}

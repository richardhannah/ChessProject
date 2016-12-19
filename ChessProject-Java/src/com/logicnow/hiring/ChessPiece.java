package com.logicnow.hiring;/**
 * Created by highl on 19/12/2016.
 */

/**
 * created by highl
 */
public abstract class ChessPiece {

    protected ChessBoard chessBoard;
    protected final PieceColor pieceColor;
    protected Position position;

    public ChessPiece(PieceColor pieceColor) {

        this.pieceColor = pieceColor;

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }


    public void Move(MovementType movementType, Position newPosition) {
        switch (movementType){
            case MOVE:
                doMove(newPosition);
                break;
            case CAPTURE:
                break;
        }
    }

    protected abstract void doMove(Position newPosition);
    protected abstract void doCapture(Position newPosition);

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, position.getX(), position.getY(), pieceColor);
    }
}

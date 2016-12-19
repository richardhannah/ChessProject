package com.logicnow.hiring;/**
 * Created by highl on 19/12/2016.
 */

/**
 * created by highl
 */
abstract class ChessPiece {

    ChessBoard chessBoard;
    private final PieceColor pieceColor;
    Position position;

    ChessPiece(PieceColor pieceColor) {

        this.pieceColor = pieceColor;

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setChessBoardAsObserver(ChessBoard chessBoard) {
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

    private String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, position.getX(), position.getY(), pieceColor);
    }
}

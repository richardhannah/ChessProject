package com.logicnow.hiring;

public class Pawn {

    private ChessBoard chessBoard;
    private final PieceColor pieceColor;
    private Position position;



    public Pawn(PieceColor pieceColor) {

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


    public void Move(MovementType movementType, int newX, int newY) {
        switch (movementType){
            case MOVE:
                doMove(newX,newY);
                break;
            case CAPTURE:
                break;
        }
    }

    private void doMove(int newX, int newY){
        if(newX == position.getX()) {
            this.position = new Position(newX,newY);
        }
    }



    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, position.getX(), position.getY(), pieceColor);
    }
}

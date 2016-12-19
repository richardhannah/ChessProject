package com.logicnow.hiring;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int MAX_BOARD_WIDTH = 8;
    public static final int MAX_BOARD_HEIGHT = 8;

    private static final int PAWNLIMIT = 8;

    private List<ChessPiece> piecesList = new ArrayList<>();

    private ChessPiece[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(ChessPiece chessPiece, Position position) {
        if(!PositonIsOccupied(position) &&
                IsLegalBoardPosition(position) &&
                !pieceLimitReached(chessPiece)) {
            chessPiece.setChessBoardAsObserver(this);
            chessPiece.setPosition(position);
            piecesList.add(chessPiece);
            pieces[position.getX()][position.getY()] = chessPiece;
        }
        else {
            chessPiece.setPosition(new Position(-1,-1));
        }
    }

    public boolean IsLegalBoardPosition(Position position) {
        if(position.getX() > MAX_BOARD_WIDTH ||
                position.getX() < 0 ||
                position.getY() > MAX_BOARD_HEIGHT ||
                position.getY() < 0){
            return false;
        }
        return true;
    }

    public void Update(ChessPiece chessPiece, Position oldPosition, Position newPosition){
        pieces[oldPosition.getX()][oldPosition.getY()] = null;
        pieces[newPosition.getX()][newPosition.getY()] = chessPiece;
    }

    private boolean pieceLimitReached(ChessPiece chessPiece){
        long count = piecesList.stream().filter(p -> p.getPieceColor().equals(chessPiece.getPieceColor())).count();
        return (count >= PAWNLIMIT );
    }

    private boolean PositonIsOccupied(Position position){
        return pieces[position.getX()][position.getY()] != null;
    }

}

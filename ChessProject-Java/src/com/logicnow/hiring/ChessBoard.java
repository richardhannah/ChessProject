package com.logicnow.hiring;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int MAX_BOARD_WIDTH = 8;
    public static final int MAX_BOARD_HEIGHT = 8;

    private static final int PAWNLIMIT = 8;

    private List<Pawn> piecesList = new ArrayList<>();

    private ChessPiece[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, Position position) {
        if(!PositonIsOccupied(position) &&
                IsLegalBoardPosition(position) &&
                !pieceLimitReached(pawn)) {
            pawn.setPosition(position);
            piecesList.add(pawn);
            pieces[position.getX()][position.getY()] = pawn;
        }
        else {
            pawn.setPosition(new Position(-1,-1));
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

    private boolean pieceLimitReached(Pawn pawn){
        long count = piecesList.stream().filter(p -> p.getPieceColor().equals(pawn.getPieceColor())).count();
        return (count >= PAWNLIMIT );
    }

    private boolean PositonIsOccupied(Position position){
        return pieces[position.getX()][position.getY()] != null;
    }

}

package com.logicnow.hiring;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int MAX_BOARD_WIDTH = 8;
    public static final int MAX_BOARD_HEIGHT = 8;

    private static final int PAWNLIMIT = 8;

    private List<Pawn> piecesList = new ArrayList<>();

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if(!PositonIsOccupied(xCoordinate,yCoordinate) &&
                IsLegalBoardPosition(xCoordinate,yCoordinate) &&
                !pieceLimitReached(pawn)) {
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            piecesList.add(pawn);
            pieces[xCoordinate][yCoordinate] = pawn;
        }
        else {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
        }
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if(xCoordinate > MAX_BOARD_WIDTH ||
                xCoordinate < 0 ||
                yCoordinate > MAX_BOARD_HEIGHT ||
                yCoordinate < 0){
            return false;
        }
        return true;
    }

    private boolean pieceLimitReached(Pawn pawn){
        long count = piecesList.stream().filter(p -> p.getPieceColor().equals(pawn.getPieceColor())).count();
        return (count >= PAWNLIMIT );
    }

    private boolean PositonIsOccupied(int xCoordinate, int yCoordinate){
        return pieces[xCoordinate][yCoordinate] != null;
    }

}

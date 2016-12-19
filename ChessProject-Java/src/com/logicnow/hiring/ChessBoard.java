package com.logicnow.hiring;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * Representation of a physical chessboard to which ChessPieces may be added.
 */
class ChessBoard implements Observer {

    /**
     * The constant MAX_BOARD_WIDTH.
     */
    public static final int MAX_BOARD_WIDTH = 8;
    /**
     * The constant MAX_BOARD_HEIGHT.
     */
    public static final int MAX_BOARD_HEIGHT = 8;

    private static final int PAWNLIMIT = 8;

    private final List<ChessPiece> piecesList = new ArrayList<>();

    private final ChessPiece[][] pieces;

    /**
     * Instantiates a new Chess board.
     */
    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    /**
     * Adds a ChessPiece to the chessboard. Checks if position being added to is occupied or out of bounds
     * and whether the type of piece being added has reached its limit.
     * Once added the board becomes an observer of the chesspiece so it can update its internal state when
     * a ChessPiece's move method is called.
     *
     * @param chessPiece the chess piece
     * @param position   the position
     */
    public void add(ChessPiece chessPiece, Position position) {
        if(!positonIsOccupied(position) &&
                isLegalBoardPosition(position) &&
                !pieceLimitReached(chessPiece)) {
            chessPiece.addObserver(this);
            chessPiece.setPosition(position);
            piecesList.add(chessPiece);
        }
        else {
            chessPiece.setPosition(new Position(-1,-1));
        }
    }

    /**
     * Is legal board position checks if the board position is within the bounds of the board
     * as represented by the internal pieces array
     *
     * @param position the position
     * @return the boolean
     */
    public boolean isLegalBoardPosition(Position position) {
        return !(position.getX() > MAX_BOARD_WIDTH ||
                position.getX() < 0 ||
                position.getY() > MAX_BOARD_HEIGHT ||
                position.getY() < 0);
    }

    //checks if piece limit is reached - only works for Pawns at present
    //TODO: will need to be reworked to accomodate other ChessPiece subtypes
    private boolean pieceLimitReached(ChessPiece chessPiece){
        long count = piecesList.stream().filter(p -> p.getPieceColor().equals(chessPiece.getPieceColor())).count();
        return (count >= PAWNLIMIT );
    }

    private boolean positonIsOccupied(Position position){
        return pieces[position.getX()][position.getY()] != null;
    }

    @Override
    public void update(Observable o, Object arg) {
        ChessPiece chessPiece = (ChessPiece) o;
        Optional<Position> oldPosition = (Optional<Position>) arg;
        oldPosition.ifPresent(p -> pieces[p.getX()][p.getY()] = null);
        pieces[chessPiece.getPosition().getX()][chessPiece.getPosition().getY()] = chessPiece;
    }
}

package com.logicnow.hiring;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.StringJoiner;
import org.mockito.internal.util.reflection.Whitebox;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.add(testSubject, new Position(6,3));
        assertEquals(6, testSubject.getPosition().getX());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.add(testSubject, new Position(6,3));
        assertEquals(3, testSubject.getPosition().getY());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, new Position(7, 3));
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(3, testSubject.getPosition().getY());
        ChessPiece[][] internalPiecesArray = (ChessPiece[][]) Whitebox.getInternalState(chessBoard,"pieces");
        assertEquals(testSubject,internalPiecesArray[6][3]);
        assertEquals(null,internalPiecesArray[7][3]);
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, new Position(4, 3));
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(3, testSubject.getPosition().getY());
        ChessPiece[][] internalPiecesArray = (ChessPiece[][]) Whitebox.getInternalState(chessBoard,"pieces");
        assertEquals(testSubject,internalPiecesArray[6][3]);
        assertEquals(null,internalPiecesArray[4][3]);
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, new Position(6, 2));
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(2, testSubject.getPosition().getY());
        ChessPiece[][] internalPiecesArray = (ChessPiece[][]) Whitebox.getInternalState(chessBoard,"pieces");
        assertEquals(null,internalPiecesArray[6][3]);
        assertEquals(testSubject,internalPiecesArray[6][2]);
    }

    @Test
    public void testPawn_Move_IlLegalCoordinates_Forward_DoesNotMove() {
        chessBoard.add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, new Position(6, 0));
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(3, testSubject.getPosition().getY());
        ChessPiece[][] internalPiecesArray = (ChessPiece[][]) Whitebox.getInternalState(chessBoard,"pieces");
        assertEquals(testSubject,internalPiecesArray[6][3]);
        assertEquals(null,internalPiecesArray[6][0]);
    }

    @Test
    public void testToString(){
        testSubject.setPosition(new Position(0,0));
        String expected ="Current X: 0 \r\n" +
                         "Current Y: 0 \r\n" +
                         "Piece Color: 0";
        String result = testSubject.toString();

        assertEquals(expected,result);


    }

}
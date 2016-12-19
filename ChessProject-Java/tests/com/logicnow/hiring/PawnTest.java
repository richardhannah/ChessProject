package com.logicnow.hiring;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        this.chessBoard.Add(testSubject, new Position(6,3));
        assertEquals(6, testSubject.getPosition().getX());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, new Position(6,3));
        assertEquals(3, testSubject.getPosition().getY());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(3, testSubject.getPosition().getY());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(3, testSubject.getPosition().getY());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubject, new Position(6,3));
        testSubject.Move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getPosition().getX());
        assertEquals(2, testSubject.getPosition().getY());
    }

}
package com.logicnow.hiring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(new Position(0,0));
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(new Position(5,5));
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(new Position(11,5));
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(new Position(0,9));
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(new Position(11,0));
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(new Position(5,-1));
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.Add(firstPawn, new Position(6,3));
        testSubject.Add(secondPawn, new Position(6,3));
        assertEquals(6, firstPawn.getPosition().getX());
        assertEquals(3, firstPawn.getPosition().getY());
        assertEquals(-1, secondPawn.getPosition().getX());
        assertEquals(-1, secondPawn.getPosition().getY());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 16; i++)
        {
            Pawn pawn = null;
            if(i %2 == 0) {
                pawn = new Pawn(PieceColor.BLACK);
            }
            else {
                pawn = new Pawn(PieceColor.WHITE);
            }
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, new Position( 6 + row, i % ChessBoard.MAX_BOARD_WIDTH));

        }
        Pawn blackPawn = new Pawn(PieceColor.BLACK);
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        testSubject.Add(blackPawn,new Position(7,7));
        testSubject.Add(whitePawn,new Position(6,7));
        assertEquals(-1,blackPawn.getPosition().getX());
        assertEquals(-1,blackPawn.getPosition().getY());
        assertEquals(-1,whitePawn.getPosition().getX());
        assertEquals(-1,whitePawn.getPosition().getY());
    }
}
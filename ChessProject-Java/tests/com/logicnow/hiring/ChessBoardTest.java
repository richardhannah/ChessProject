package com.logicnow.hiring;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest extends TestCase {

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
        Assert.assertTrue(isValidPosition);
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
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, new Position( 6 + row, i % ChessBoard.MAX_BOARD_WIDTH));
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getPosition().getX());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getPosition().getY());
            }
            else
            {
                assertEquals(-1, pawn.getPosition().getX());
                Assert.assertEquals(-1, pawn.getPosition().getY());
            }
        }
    }
}
package com.pbezat.domino.chain.processors;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;
import com.pbezat.domino.chain.utils.DominoChainValueCalculator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DominoChainValueCalculatorTest {

    @Test
    public void simpleCalculateValueTest() {
        DominoPiece d1 = new DominoPiece(1, 2);
        DominoPiece d2 = new DominoPiece(2, 5);
        DominoChain chain = new DominoChain();
        chain.getChain().add(d1);
        chain.getChain().add(d2);

        int value = DominoChainValueCalculator.calculateValue(chain);
        assertEquals(2, value);
    }

    @Test
    public void moreChainPiecesCalculateTest() {
        DominoPiece d1 = new DominoPiece(1, 2);
        DominoPiece d2 = new DominoPiece(2, 3);
        DominoPiece d3 = new DominoPiece(3, 1);
        DominoPiece d4 = new DominoPiece(1, 4);
        DominoPiece d5 = new DominoPiece(4, 2);
        DominoPiece d6 = new DominoPiece(2, 9);
        DominoPiece d7 = new DominoPiece(9, 0);

        List<DominoPiece> dominoPieces = new ArrayList<>();
        dominoPieces.add(d1);
        dominoPieces.add(d2);
        dominoPieces.add(d3);
        dominoPieces.add(d4);
        dominoPieces.add(d5);
        dominoPieces.add(d6);
        dominoPieces.add(d7);

        DominoChain chain = new DominoChain();
        chain.getChain().addAll(dominoPieces);

        int value = DominoChainValueCalculator.calculateValue(chain);
        assertEquals(21, value);
    }
}

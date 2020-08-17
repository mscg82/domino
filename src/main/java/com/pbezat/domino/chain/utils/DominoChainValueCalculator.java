package com.pbezat.domino.chain.utils;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;

import java.util.Iterator;

public class DominoChainValueCalculator {
    public static int calculateValue(final DominoChain chain) {
        int value = 0;

        final Iterator<DominoPiece> iterator = chain.getChain().iterator();
        while (iterator.hasNext()) {
            final DominoPiece dominoPiece = iterator.next();

            if (iterator.hasNext()) {
                value += dominoPiece.getRightValue();
            }
        }

        return value;
    }
}

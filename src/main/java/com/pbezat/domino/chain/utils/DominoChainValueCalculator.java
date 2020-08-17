package com.pbezat.domino.chain.utils;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;

import java.util.Iterator;

public class DominoChainValueCalculator {
    public static int calculateValue(DominoChain chain) {
        int value = 0;

        Iterator<DominoPiece> iterator = chain.getChain().iterator();
        while (iterator.hasNext()) {
            DominoPiece dominoPiece = iterator.next();

            if (iterator.hasNext()) {
                value += dominoPiece.getRightValue();
            }
        }

        return value;
    }
}

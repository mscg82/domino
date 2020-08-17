package com.pbezat.domino.chain.processors.impl;

import com.pbezat.domino.chain.processors.IDominoChainConnector;
import com.pbezat.domino.chain.processors.IDominoPieceCopyFactory;
import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;
import org.springframework.stereotype.Component;

@Component
public class DominoChainConnector implements IDominoChainConnector
{
    private final IDominoPieceCopyFactory copyFactory;

    public DominoChainConnector(IDominoPieceCopyFactory copyFactory) {
        this.copyFactory = copyFactory;
    }

    @Override
    public DominoChain connect(DominoChain currentChain, DominoPiece other) {
        DominoChain result = copyFactory.deepCopy(currentChain);

        if (other.getLeftValue().equals(result.getRightValue())) {
            result.getChain().addLast(other);
        }
        else if (other.getRightValue().equals(result.getRightValue())) {
            result.getChain().addLast(other.swap());
        }
        else {
            result = currentChain;
        }

        return result;
    }
}


package com.pbezat.domino.chain.processors.impl;

import com.pbezat.domino.chain.processors.IDominoPieceCopyFactory;
import com.pbezat.domino.chain.tos.DominoChain;
import org.springframework.stereotype.Component;

@Component
public class DominoPieceCopyFactory implements IDominoPieceCopyFactory
{
    @Override
    public DominoChain deepCopy(final DominoChain chainToCopy) {
        final DominoChain result = new DominoChain();
        result.getChain().addAll(chainToCopy.getChain());
        return result;
    }
}

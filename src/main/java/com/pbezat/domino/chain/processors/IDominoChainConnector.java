package com.pbezat.domino.chain.processors;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;

public interface IDominoChainConnector
{
    DominoChain connect(final DominoChain currentChain, final DominoPiece other);
}

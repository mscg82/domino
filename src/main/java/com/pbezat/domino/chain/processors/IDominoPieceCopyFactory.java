package com.pbezat.domino.chain.processors;

import com.pbezat.domino.chain.tos.DominoChain;

public interface IDominoPieceCopyFactory {
    DominoChain deepCopy(DominoChain chainToCopy);
}

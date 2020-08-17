package com.pbezat.domino.chain.processors;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;

import java.util.List;

public interface IDominoPiecesChainProcessor {
    DominoChain findHighestChain(DominoPiece startingPiece, List<DominoPiece> pieces);
}

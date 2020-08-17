package com.pbezat.domino.chain.solvers;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;

import java.util.List;

public interface IDominoPiecesSolver
{
    DominoChain solve(DominoPiece startingPiece, List<DominoPiece> dominoes);
}

package com.pbezat.domino.chain.processors.impl;

import com.pbezat.domino.chain.processors.IDominoPiecesChainProcessor;
import com.pbezat.domino.chain.solvers.IDominoPiecesSolver;
import com.pbezat.domino.chain.tos.DominoPiece;
import com.pbezat.domino.chain.tos.DominoChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DominoPiecesChainProcessor implements IDominoPiecesChainProcessor
{
    private final IDominoPiecesSolver solver;

    @Autowired
    public DominoPiecesChainProcessor(final IDominoPiecesSolver solver) {
        this.solver = solver;
    }

    @Override
    public DominoChain findHighestChain(final DominoPiece startingPiece, final List<DominoPiece> pieces) {
        return solver.solve(startingPiece, pieces);
    }
}

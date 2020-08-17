package com.pbezat.domino.chain.processors;

import com.pbezat.domino.chain.processors.impl.DominoChainConnector;
import com.pbezat.domino.chain.processors.impl.DominoPieceCopyFactory;
import com.pbezat.domino.chain.solvers.impl.DominoPiecesSolver;
import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DominoPiecesSolverTest
{
    //connector and copyfactory should be mocks with mocked objects, TODO
    private final DominoPiecesSolver solver = new DominoPiecesSolver(new DominoChainConnector(new DominoPieceCopyFactory()));

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simple_two_matching_one_not_matching() {

        final DominoPiece d1 = new DominoPiece(1, 2);
        final DominoPiece d2 = new DominoPiece(2, 3);
        final DominoPiece d3 = new DominoPiece(4, 5);

        final List<DominoPiece> dominoPieces = new ArrayList<>();
        dominoPieces.add(d2);
        dominoPieces.add(d3);

        //real execution
        final DominoChain solvedChain = solver.solve(d1, dominoPieces);

        //verification
        assertEquals(2, solvedChain.getChain().size());
    }

    @Test
    public void test_solve_shorter_chain_higher_value() {
        final DominoPiece d1 = new DominoPiece(1, 2);
        final DominoPiece d2 = new DominoPiece(2, 9);
        final DominoPiece d3 = new DominoPiece(9, 0);
        final DominoPiece d4 = new DominoPiece(2, 3);
        final DominoPiece d5 = new DominoPiece(3, 1);
        final DominoPiece d6 = new DominoPiece(1, 4);
        final DominoPiece d7 = new DominoPiece(4, 2);

        final List<DominoPiece> dominoPieces = new ArrayList<>();
        dominoPieces.add(d2);
        dominoPieces.add(d3);
        dominoPieces.add(d4);
        dominoPieces.add(d5);
        dominoPieces.add(d6);
        dominoPieces.add(d7);

        //real execution
        final DominoChain solvedChain = solver.solve(d1, dominoPieces);

        //verification
        assertEquals(solvedChain.getChain().size(), 7);
    }
}

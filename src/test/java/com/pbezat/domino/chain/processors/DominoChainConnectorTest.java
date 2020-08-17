package com.pbezat.domino.chain.processors;

import com.pbezat.domino.chain.processors.impl.DominoChainConnector;
import com.pbezat.domino.chain.processors.impl.DominoPieceCopyFactory;
import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.chain.tos.DominoPiece;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class DominoChainConnectorTest {

    @Test
    public void simpleConnectPiecesTest()
    {
        final DominoPiece d1 = new DominoPiece(1, 2);

        final DominoChain chain = new DominoChain();
        chain.getChain().add(d1);

        final DominoChain deepCopiedChain = new DominoChain();
        deepCopiedChain.getChain().add(d1);

        final DominoPiece d2 = new DominoPiece(2, 3);

        final IDominoPieceCopyFactory copyFactory = Mockito.mock(DominoPieceCopyFactory.class);
        Mockito.when(copyFactory.deepCopy(chain)).thenReturn(deepCopiedChain);

        final IDominoChainConnector chainConnector = new DominoChainConnector(copyFactory);

        final DominoChain connected = chainConnector.connect(chain, d2);
        assertEquals(connected.getChain().size(), 2);
    }
}

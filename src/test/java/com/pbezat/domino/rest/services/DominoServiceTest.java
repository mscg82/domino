package com.pbezat.domino.rest.services;

import com.pbezat.domino.chain.processors.IDominoPiecesChainProcessor;
import com.pbezat.domino.chain.tos.DominoPiece;
import com.pbezat.domino.rest.services.impl.DominoService;
import com.pbezat.domino.rest.tos.requests.DominoCalculateChainRequest;
import com.pbezat.domino.rest.validators.DominoPiecesValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.times;

public class DominoServiceTest {

    @Mock
    private DominoPiecesValidator validator;

    @Mock
    private IDominoPiecesChainProcessor chainProcessor;

    @InjectMocks
    private DominoService dominoService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simpleTest() {
        DominoPiece d1 = new DominoPiece(1, 2);
        DominoPiece d2 = new DominoPiece(2, 4);

        ArrayList<DominoPiece> dominoPieces = new ArrayList<>();
        dominoPieces.add(d1);
        dominoPieces.add(d2);

        DominoCalculateChainRequest request = new DominoCalculateChainRequest();
        request.setPieces(dominoPieces);
        request.setStartingPiece(d1);

        dominoService.calculateChain(request);

        Mockito.verify(validator, times(1)).validatePieces(request);
        Mockito.verify(chainProcessor, times(1)).findHighestChain(d1, dominoPieces);
    }
}

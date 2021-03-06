package com.pbezat.domino.rest.controllers;

import com.pbezat.domino.rest.services.impl.DominoService;
import com.pbezat.domino.rest.tos.requests.DominoCalculateChainRequest;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class DominoControllerTest {
    @Test
    public void testController() {
        final DominoService dominoService = Mockito.mock(DominoService.class);

        final DominoCalculateChainRequest calculateChainRequest = new DominoCalculateChainRequest();

        final DominoController controller = new DominoController(dominoService);
        controller.calculateChain(calculateChainRequest);

        Mockito.verify(dominoService, times(1)).calculateChain(calculateChainRequest);
    }
}

package com.pbezat.domino.rest.services.impl;

import com.pbezat.domino.chain.processors.IDominoPiecesChainProcessor;
import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.rest.services.IDominoService;
import com.pbezat.domino.rest.tos.requests.DominoCalculateChainRequest;
import com.pbezat.domino.rest.validators.DominoPiecesValidator;
import org.springframework.stereotype.Service;

@Service
public class DominoService implements IDominoService
{
    private final DominoPiecesValidator dominoPiecesValidator;
    private final IDominoPiecesChainProcessor IDominoPiecesChainProcessor;

    public DominoService(final DominoPiecesValidator dominoPiecesValidator, final IDominoPiecesChainProcessor IDominoPiecesChainProcessor) {
        this.dominoPiecesValidator = dominoPiecesValidator;
        this.IDominoPiecesChainProcessor = IDominoPiecesChainProcessor;
    }

    @Override
    public DominoChain calculateChain(final DominoCalculateChainRequest calculateChainRequest) {
        dominoPiecesValidator.validatePieces(calculateChainRequest);

        return IDominoPiecesChainProcessor.findHighestChain(calculateChainRequest.getStartingPiece(), calculateChainRequest.getPieces());
    }
}

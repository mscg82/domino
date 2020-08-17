package com.pbezat.domino.rest.services;

import com.pbezat.domino.chain.tos.DominoChain;
import com.pbezat.domino.rest.tos.requests.DominoCalculateChainRequest;

public interface IDominoService {
    DominoChain calculateChain(final DominoCalculateChainRequest calculateChainRequest);
}

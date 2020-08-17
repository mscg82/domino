package com.pbezat.domino.rest.controllers;

import com.pbezat.domino.chain.tos.DominoChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbezat.domino.rest.services.impl.DominoService;
import com.pbezat.domino.rest.tos.requests.DominoCalculateChainRequest;

@RestController
public class DominoController {
    private final DominoService dominoService;

    @Autowired
    public DominoController(DominoService dominoService) {
        this.dominoService = dominoService;
    }

    @PostMapping("/calculate")
    public DominoChain calculateChain(@RequestBody DominoCalculateChainRequest calculateChainRequest) {
        return dominoService.calculateChain(calculateChainRequest);
    }
}

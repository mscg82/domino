package com.pbezat.domino.chain.solvers.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.pbezat.domino.chain.processors.IDominoChainConnector;
import com.pbezat.domino.chain.solvers.IDominoPiecesSolver;
import com.pbezat.domino.chain.tos.DominoPiece;
import com.pbezat.domino.chain.tos.DominoChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.pbezat.domino.chain.utils.DominoChainValueCalculator.calculateValue;

@Component
public class DominoPiecesSolver implements IDominoPiecesSolver
{
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final IDominoChainConnector chainConnector;

    @Autowired
    public DominoPiecesSolver(IDominoChainConnector chainConnector) {
        this.chainConnector = chainConnector;
    }

    public DominoChain solve(DominoPiece startingPiece, List<DominoPiece> dominoes) {
        Map<Integer, List<DominoChain>> chains = initChains(startingPiece);
        List<DominoChain> allChains = new ArrayList<>();

        for (int chainSize = 1; chainSize <= dominoes.size(); chainSize++) {
            List<DominoChain> currentLengthChains = chains.get(chainSize);

            List<DominoChain> nextLengthChains = currentLengthChains.stream()
                .map((chain) -> findNextChains(chain, dominoes))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            allChains.addAll(nextLengthChains);

            chains.remove(chainSize - 1);
            chains.put(chainSize + 1, nextLengthChains);
        }

        DominoChain highestValueChain = findHighestValueChain(allChains);

        logger.info("Solved. Highest value of chain: " + calculateValue(highestValueChain) + ". chain: " + highestValueChain);
        return highestValueChain;
    }

    private Set<DominoChain> findNextChains(DominoChain currentChain, List<DominoPiece> dominoes) {
        Set<DominoChain> nextChainSet = new HashSet<>();

        for (DominoPiece dominoPiece : dominoes) {

            if (!currentChain.getChain().contains(dominoPiece)) {
                DominoChain nextChain = chainConnector.connect(currentChain, dominoPiece);
                nextChainSet.add(nextChain);
            }
        }
        return nextChainSet;
    };

    private DominoChain findHighestValueChain(List<DominoChain> allChains)
    {
        Iterator<DominoChain> iterator = allChains.iterator();
        DominoChain highestChain = iterator.next();

        while (iterator.hasNext()) {
            DominoChain nextChain = iterator.next();

            if (calculateValue(nextChain) > calculateValue(highestChain)) {
                highestChain = nextChain;
            }
        }

        return highestChain;
    }

    private Map<Integer, List<DominoChain>> initChains(DominoPiece startingPiece) {
        Map<Integer, List<DominoChain>> chains = new HashMap<>();

        List<DominoChain> chainsList = new ArrayList<>();
        chainsList.add(new DominoChain(startingPiece));
        chainsList.add(new DominoChain(startingPiece.swap()));

        chains.put(1, chainsList);

        return chains;
    }

}

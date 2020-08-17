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
    public DominoPiecesSolver(final IDominoChainConnector chainConnector) {
        this.chainConnector = chainConnector;
    }

    public DominoChain solve(final DominoPiece startingPiece, final List<DominoPiece> dominoes) {
        final List<DominoChain> initChains = initChains(startingPiece);
        final List<DominoChain> allChains = new ArrayList<>();

        List<DominoChain> workingChains = initChains;

        for (int chainSize = 1; chainSize <= dominoes.size(); chainSize++) {
            workingChains = workingChains.stream()
                .map((chain) -> findNextChains(chain, dominoes))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            allChains.addAll(workingChains);
        }

        final DominoChain highestValueChain = findHighestValueChain(allChains);

        logger.info("Solved. Highest value of chain: " + calculateValue(highestValueChain) + ". chain: " + highestValueChain);
        return highestValueChain;
    }

    private Set<DominoChain> findNextChains(final DominoChain currentChain, final List<DominoPiece> dominoes) {
        final Set<DominoChain> nextChainSet = new HashSet<>();

        for (final DominoPiece dominoPiece : dominoes) {

            if (!currentChain.getChain().contains(dominoPiece)) {
                final DominoChain nextChain = chainConnector.connect(currentChain, dominoPiece);
                nextChainSet.add(nextChain);
            }
        }
        return nextChainSet;
    };

    private DominoChain findHighestValueChain(final List<DominoChain> allChains)
    {
        final Iterator<DominoChain> iterator = allChains.iterator();
        DominoChain highestChain = iterator.next();
        int highestChainValue = calculateValue(highestChain);

        while (iterator.hasNext()) {
            DominoChain nextChain = iterator.next();

            if (calculateValue(nextChain) > highestChainValue) {
                highestChain = nextChain;
                highestChainValue = calculateValue(highestChain);
            }
        }

        return highestChain;
    }

    private List<DominoChain> initChains(final DominoPiece startingPiece) {
        final List<DominoChain> chainsList = new ArrayList<>();
        chainsList.add(new DominoChain(startingPiece));
        chainsList.add(new DominoChain(startingPiece.swap()));

        return chainsList;
    }

}

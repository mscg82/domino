/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbezat.domino.chain.tos;

import lombok.Data;

import java.util.LinkedList;

import static com.pbezat.domino.chain.utils.DominoChainValueCalculator.calculateValue;

/**
 * Chains of dominoes. Immutable.
 * @author denis
 */
@Data
public class DominoChain implements Comparable<DominoChain>
{
    private final LinkedList<DominoPiece> chain;

    public DominoChain() {
        chain = new LinkedList<>();
    }

    public DominoChain(DominoPiece startingPiece) {
        chain = new LinkedList<>();
        chain.add(startingPiece);
    }

    @Override
    public int compareTo(DominoChain other) {
        return calculateValue(this) - calculateValue(other);
    }

    public Integer getRightValue() {
        return this.getChain().isEmpty() ? null : this.getChain().getLast().getRightValue();
    }
}

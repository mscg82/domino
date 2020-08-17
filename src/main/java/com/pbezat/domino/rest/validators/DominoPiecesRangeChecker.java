package com.pbezat.domino.rest.validators;

import com.pbezat.domino.rest.exceptions.IncorrectParameterException;
import org.springframework.stereotype.Component;

import static com.pbezat.domino.chain.tos.DominoPiece.MAX_VALUE;
import static com.pbezat.domino.chain.tos.DominoPiece.MIN_VALUE;

@Component
class DominoPiecesRangeChecker {
    void checkPiecesRange(Integer firstValue, Integer secondValue) {
        if (isPieceInIncorrectRange(firstValue) || isPieceInIncorrectRange(secondValue)) {
            throw new IncorrectParameterException(String.format("Domino piece: value is not in range(%d, %d).", MIN_VALUE, MAX_VALUE));
        }
    }

    private boolean isPieceInIncorrectRange(Integer firstValue) {
        return (firstValue < MIN_VALUE || firstValue > MAX_VALUE);
    }
}

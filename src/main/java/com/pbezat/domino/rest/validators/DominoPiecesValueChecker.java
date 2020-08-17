package com.pbezat.domino.rest.validators;

import com.pbezat.domino.rest.exceptions.IncorrectParameterException;
import org.springframework.stereotype.Component;

@Component
class DominoPiecesValueChecker {
    void checkPieceValue(final Integer leftValue, final Integer rightValue) {
        if (leftValue == null) {
            throw new IncorrectParameterException("Domino piece: first value is empty.");
        }

        if (rightValue == null) {
            throw new IncorrectParameterException("Domino piece: second value is empty.");
        }

        if (leftValue.equals(rightValue)) {
            throw new IncorrectParameterException("Domino piece: leftValue equals rightValue");
        }
    }
}

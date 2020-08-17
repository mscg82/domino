package com.pbezat.domino.rest.validators;

import com.pbezat.domino.rest.exceptions.ParametersMissingException;
import com.pbezat.domino.rest.exceptions.ParameterName;
import com.pbezat.domino.rest.tos.requests.DominoCalculateChainRequest;
import com.pbezat.domino.chain.tos.DominoPiece;
import org.springframework.stereotype.Component;

@Component
public class DominoPiecesValidator {
    private final DominoPiecesRangeChecker rangeChecker;
    private final DominoPiecesValueChecker valueChecker;

    public DominoPiecesValidator(final DominoPiecesRangeChecker rangeChecker, final DominoPiecesValueChecker valueChecker) {
        this.rangeChecker = rangeChecker;
        this.valueChecker = valueChecker;
    }

    public void validatePieces(final DominoCalculateChainRequest calculateChainRequest) {
        if (calculateChainRequest.getPieces() == null) {
            throw new ParametersMissingException(ParameterName.DOMINO_PIECES);
        }

        if (calculateChainRequest.getStartingPiece() == null) {
            throw new ParametersMissingException(ParameterName.DOMINO_STARTING_PIECE);
        }

        for (final DominoPiece piece : calculateChainRequest.getPieces()) {
            final Integer firstValue = piece.getLeftValue();
            final Integer secondValue = piece.getRightValue();

            valueChecker.checkPieceValue(firstValue, secondValue);
            rangeChecker.checkPiecesRange(firstValue, secondValue);
        }
    }
}

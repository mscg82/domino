package com.pbezat.domino.rest.tos.requests;

import com.pbezat.domino.chain.tos.DominoPiece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DominoCalculateChainRequest
{
    private List<DominoPiece> pieces;
    private DominoPiece startingPiece;
}

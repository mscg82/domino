package com.pbezat.domino.chain.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class DominoPiece implements Comparable<DominoPiece>{
    public static final Integer MIN_VALUE = 1;
    public static final Integer MAX_VALUE = 10;

    private Integer leftValue;
    private Integer rightValue;

    public DominoPiece(Integer leftValue, Integer rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public int compareTo(DominoPiece o) {
        return this.leftValue * 10 + this.rightValue - o.getLeftValue() * 10 - o.getRightValue();
    }

    public DominoPiece swap() {
        return new DominoPiece(rightValue, leftValue);
    }
}

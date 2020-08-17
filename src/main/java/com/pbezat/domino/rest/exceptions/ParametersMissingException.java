package com.pbezat.domino.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumSet;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ParametersMissingException extends RuntimeException {
    private final EnumSet<ParameterName> parameterNames;

    public ParametersMissingException(final ParameterName parameterName) {
        this.parameterNames = EnumSet.of(parameterName);
    }
}

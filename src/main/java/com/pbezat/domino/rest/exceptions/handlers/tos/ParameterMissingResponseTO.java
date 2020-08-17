package com.pbezat.domino.rest.exceptions.handlers.tos;

import com.pbezat.domino.rest.exceptions.ParameterName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.EnumSet;
import java.util.List;

@Data
@AllArgsConstructor
public class ParameterMissingResponseTO
{
    private String message;
    private EnumSet<ParameterName> missingParameters;
}

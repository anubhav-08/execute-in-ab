package com.anubhav.abexperiment.dto;

import lombok.Data;
import java.util.Map;

@Data
public class ExecutionDetails {
    long totalHits;
    String nextVariant;
    Map<String, Integer> variantsInfo;
}

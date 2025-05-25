package com.anubhav.abexperiment.service;

import com.anubhav.abexperiment.dto.ExecutionDetails;
import com.anubhav.abexperiment.entity.ExperimentDetails;

public interface VariantComputation {
  String computeVariant(ExperimentDetails experimentDetails, ExecutionDetails executionDetails);
}

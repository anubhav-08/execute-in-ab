package com.anubhav.abexperiment.service;

import com.anubhav.abexperiment.entity.ExperimentDetails;

public interface VariantComputation {
  String computeVariant(ExperimentDetails experimentDetails);
}

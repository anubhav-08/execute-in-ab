package com.anubhav.abexperiment.service.impl;

import com.anubhav.abexperiment.service.VariantComputation;
import com.anubhav.abexperiment.entity.ExperimentDetails;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class VariantComputationImpl implements VariantComputation {
  @Override
  public String computeVariant(ExperimentDetails experimentDetails) {
    int t = LocalDateTime.now().getSecond();
    int s = experimentDetails.getVariants().size();
    var selectedVariant = experimentDetails.getVariants().get(t%s);
    return selectedVariant.name();
  }
}

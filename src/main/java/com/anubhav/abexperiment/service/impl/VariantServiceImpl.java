package com.anubhav.abexperiment.service.impl;

import com.anubhav.abexperiment.service.VariantComputation;
import com.anubhav.abexperiment.service.VariantService;
import com.anubhav.abexperiment.repository.AbExperimentDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {
  private final AbExperimentDetailsRepository abExperimentDetailsRepository;
  private final VariantComputation variantComputation;
  @Override
  public String fetchVariant(String expId) {
    var experimentDetails = abExperimentDetailsRepository.findByExpId(expId);
    return variantComputation.computeVariant(experimentDetails);
  }
}

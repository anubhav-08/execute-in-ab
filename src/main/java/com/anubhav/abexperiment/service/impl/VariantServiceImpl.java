package com.anubhav.abexperiment.service.impl;

import com.anubhav.abexperiment.dto.ExecutionDetails;
import com.anubhav.abexperiment.entity.ExperimentDetails;
import com.anubhav.abexperiment.repository.ExecutionDetailsRepository;
import com.anubhav.abexperiment.service.VariantComputation;
import com.anubhav.abexperiment.service.VariantService;
import com.anubhav.abexperiment.repository.AbExperimentDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {
  private final AbExperimentDetailsRepository abExperimentDetailsRepository;
  private final VariantComputation variantComputation;
  private final ExecutionDetailsRepository executionDetailsRepository;
  @Override
  public String fetchVariant(String expId) {
    var experimentDetails = abExperimentDetailsRepository.findByExpId(expId);
    var executionDetails = executionDetailsRepository.fetchById(experimentDetails.getExpId());
    return evaluateExecutionDetails(experimentDetails, executionDetails);
  }

  private String evaluateExecutionDetails(ExperimentDetails experimentDetails, ExecutionDetails executionDetails) {
    if(Objects.nonNull(executionDetails.getNextVariant()) && !executionDetails.getNextVariant().isBlank()) {
      return executionDetails.getNextVariant();
    }
      return variantComputation.computeVariant(experimentDetails, executionDetails);
  }
}

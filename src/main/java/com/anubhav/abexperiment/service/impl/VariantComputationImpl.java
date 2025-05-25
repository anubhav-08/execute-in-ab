package com.anubhav.abexperiment.service.impl;

import com.anubhav.abexperiment.dto.ExecutionDetails;
import com.anubhav.abexperiment.dto.Variant;
import com.anubhav.abexperiment.repository.ExecutionDetailsRepository;
import com.anubhav.abexperiment.service.VariantComputation;
import com.anubhav.abexperiment.entity.ExperimentDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VariantComputationImpl implements VariantComputation {
  private final ExecutionDetailsRepository executionDetailsRepository;

  @Override
  public String computeVariant(ExperimentDetails experimentDetails, ExecutionDetails executionDetails) {
    double mxDiff = -1;
    String selectedVariant = executionDetails.getNextVariant();
    for (Variant variant : experimentDetails.getVariants()){
      double currDiff = variant.percentage() - calculateCurrValuation(variant.name(),executionDetails);
      if (currDiff > mxDiff){
        mxDiff = currDiff;
        selectedVariant = variant.name();
      }
    }
    updateExecutionDetails(experimentDetails.getExpId(), selectedVariant, executionDetails);
    return selectedVariant;
  }

  private void updateExecutionDetails(String id, String selectedVariant, ExecutionDetails executionDetails) {
    executionDetails.setTotalHits(executionDetails.getTotalHits()+1);
    var variantInfo = executionDetails.getVariantsInfo();
    int prevCount = Optional.ofNullable(variantInfo.get(selectedVariant)).orElse(0);
    variantInfo.put(selectedVariant, prevCount+1);
    executionDetails.setVariantsInfo(variantInfo);
    executionDetailsRepository.save(id, executionDetails);
  }

  private Double calculateCurrValuation(String variantName, ExecutionDetails executionDetails) {
    if(executionDetails.getTotalHits() == 0)return 0d;
    var optionalVariantCount = Optional.ofNullable(executionDetails.getVariantsInfo().get(variantName));
    return optionalVariantCount.map(integer -> integer.doubleValue() / executionDetails.getTotalHits()).orElse(0d);
  }
}

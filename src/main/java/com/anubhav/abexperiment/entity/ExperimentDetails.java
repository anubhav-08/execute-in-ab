package com.anubhav.abexperiment.entity;

import com.anubhav.abexperiment.dto.Variant;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("test_ab_experiment_details")
@Getter
public class ExperimentDetails {
  @Id
  private String id;
  private String expId;
  private List<Variant> variants;
}

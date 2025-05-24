package com.anubhav.abexperiment.repository;

import com.anubhav.abexperiment.entity.ExperimentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbExperimentDetailsRepository extends MongoRepository<ExperimentDetails, String> {
  ExperimentDetails findByExpId(String expId);
}

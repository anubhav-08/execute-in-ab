package com.anubhav.abexperiment.repository;

import com.anubhav.abexperiment.dto.ExecutionDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ExecutionDetailsRepository {
    private static HashMap<String, ExecutionDetails> store = new HashMap<>();
    public ExecutionDetails fetchById(String id){
        return store.get(id);
    }

    public void save(String id, ExecutionDetails executionDetails) {
        store.put(id, executionDetails);
    }
}

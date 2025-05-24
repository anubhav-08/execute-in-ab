package com.anubhav.abexperiment.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.anubhav.abexperiment.repository"}, mongoTemplateRef = "executeInAbMongoTemplate")
@Configuration
@ConfigurationProperties(value = "execute.in.ab.mongo")
@Data
public class MongoConfig {
  private String uri;
  @Bean(name = "executeInAbMongoTemplate")
  public MongoTemplate mongoTemplate() {
    return getMongoTemplate(
        new ConnectionString(uri));
  }

  private MongoTemplate getMongoTemplate(ConnectionString connectionString) {
    String dbName = connectionString.getDatabase();

    MongoClient mongoClient = MongoClients.create(
        MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString.getConnectionString()))
            .build());
    return new MongoTemplate(mongoClient, dbName);
  }
}



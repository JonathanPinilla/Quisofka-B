package com.quisofka.quizzes.infrastructure.drivenAdapters.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClients;
import com.quisofka.quizzes.infrastructure.drivenAdapters.data.StudentData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories("com.mongodb")
//@RequiredArgsConstructor
public class MongoConfig {

    @Bean
    public MongoDBSecret dbSecret(@Value("${spring.data.mongodb.uri}") String uri) {
        return MongoDBSecret.builder()
                .uri(uri)
                .build();
    }

    @Bean
    public ReactiveMongoClientFactory mongoProperties(MongoDBSecret secret) {
        MongoProperties properties = new MongoProperties();
        properties.setUri(secret.getUri());

        List<MongoClientSettingsBuilderCustomizer> list = new ArrayList<>();
        list.add(new MongoPropertiesClientSettingsBuilderCustomizer(properties));
        return new ReactiveMongoClientFactory(list);
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(
            final LocalValidatorFactoryBean factory) {
        return new ValidatingMongoEventListener(factory);
    }
}

package com.quisofka.quizzes.infrastructure.drivenAdapters.config;

import com.quisofka.quizzes.infrastructure.drivenAdapters.data.StudentData;
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
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories("com.mongodb")
public class MongoConfig {

    // to do the email uniqued in the DB
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Index index = new Index();
            index.named("email_unique_index").on("email", Sort.Direction.ASC).unique();
            mongoTemplate.indexOps(StudentData.class).ensureIndex(index);
        };
    }

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

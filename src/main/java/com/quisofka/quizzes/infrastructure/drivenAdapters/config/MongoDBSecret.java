package com.quisofka.quizzes.infrastructure.drivenAdapters.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MongoDBSecret {
    private final String uri;
    private final String host;
    private final int port;
    private final String database;

}

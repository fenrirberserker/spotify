package com.api.spotify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableMongoRepositories(basePackages = "com.api.spotify.repository")
@Import(EmbeddedMongoAutoConfiguration.class)
public class MongoConfig {
}

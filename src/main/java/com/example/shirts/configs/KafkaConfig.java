package com.example.shirts.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class KafkaConfig {
    @Bean
    public NewTopic shirtTopic() {
        return new NewTopic("shirt-events", 1, (short) 1);
    }
}
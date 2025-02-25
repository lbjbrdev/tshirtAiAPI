package com.example.shirts.adapters.messaging.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service 
public class KafkaConsumer {
    @KafkaListener(topics = "shirt-events", groupId = "shirts-group")
    public void consumeShirtEvent(String message) {
        System.out.println("Received Kafka event: " + message);
    }
}
package com.example.shirts.adapters.messaging.kafka;

import org.springframework.kafka.core.kafkaTemplate;
import org.springframework.sterotype.Service;

@Service 
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> KafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendShirtEvent(String message) {
        kafkaTemplate.send("shirt-events", message);
    }
}
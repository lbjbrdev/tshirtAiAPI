package com.example.shirts.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isShirtDuplicate(String description) {
        String prompt = "Check if this soccer shirt description matches an existing one: " + description;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAiApiKey);

        Map<String, Object> requestBody = Map.of(
            "model", model,
            "messages", new Object[]{
                Map.of("role", "system", "content", "You are a soccer shirt catalog expert."),
                Map.of("role", "user", "content", prompt)
            }
        );

        HttpEntity<Map> response = restTemplate.exchange(
            "https://api.openai.com/v1/chat/completions",
            HttpMethod.POST,
            requestEntity,
            Map.class,
        );

        String responseText = (String) ((Map<String, Object>) ((Map<String, Object>) response.getBody()
            .get("choices"))
            .get(0))
            .get("message")
            .get("content");

        return responseText.toLowerCase().contains("yes");
    }
}
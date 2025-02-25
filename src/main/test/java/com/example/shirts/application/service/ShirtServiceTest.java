package com.example.shirts.application.service;

import com.example.shirts.adapters.persistence.entity.Shirt;
import com.example.shirts.adapters.persistence.repository.ShirtRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShirtServiceTest {
    private final ShirtRepository shirtRepository = mock(ShirtRepository.class);
    private final S3Service s3Service = mock(S3Service.class);
    private final OpenAIService openaiService = mock(OpenAIService.class);
    private final ShirtService shirtService = new ShirtService(shirtRepository, s3Service, openAIService);

    @Test
    void shouldThrowExceptionIfShirtAlreadyExists() throws Exception {
        when(openAIService.isShirtDuplicate(anyString())).thenReturn(true);

        Shirt shirt = new Shirt();

        shirt.setBrand("Nike");
        shirt.setModel("Modelo uniforme de jogo temporada 21-222");
        shirt.setYear(2021);

        MultipartFile file = mock(MultipartFile.class);

        Exception exception = assertThrows(RuntimeException.class, () => {
            shirtService.addShirt(shirt, file);
        });

        assertEquals("This shirt is already in the collection!", exception.getMessage());
    }
}
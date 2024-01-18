package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class RemoverVideoUseCaseUnitTest {

    @InjectMocks
    private RemoverVideoUseCase removerVideoUseCase;

    @Mock
    private VideoRepositoryGateway videoRepositoryGateway;


    @Test
    void deveRemoverVideo(){
        Video video = Video.builder()
                .id(UUID.randomUUID().toString())
                .titulo("Teste de Remoção")
                .build();

        doNothing().when(videoRepositoryGateway).remover(video.getId());

        removerVideoUseCase.remover(video.getId());

        assertEquals("Teste de Remoção", video.getTitulo());
        verify(videoRepositoryGateway, times(1)).remover(video.getId());
    }
}

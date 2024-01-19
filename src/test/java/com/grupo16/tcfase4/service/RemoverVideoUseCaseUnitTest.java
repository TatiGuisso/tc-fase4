package com.grupo16.tcfase4.service;

import static org.mockito.Mockito.*;

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
        String videoId = UUID.randomUUID().toString();

        doNothing().when(videoRepositoryGateway).remover(videoId);

        removerVideoUseCase.remover(videoId);

        verify(videoRepositoryGateway, times(1)).remover(videoId);
    }
}

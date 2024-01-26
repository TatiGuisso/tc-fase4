package com.grupo16.tcfase4.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;


@ExtendWith(MockitoExtension.class)
class RemoverVideoUseCaseUnitTest {

    @InjectMocks
    private RemoverVideoUseCase removerVideoUseCase;

    @Mock
    private VideoRepositoryGateway videoRepositoryGateway;

    @Mock
    private FavoritoRepositoryGateway favoritoRepositoryGateway;


    @Test
    void deveRemoverVideo(){
        String videoId = UUID.randomUUID().toString();

        doNothing().when(favoritoRepositoryGateway).removerPorVideoId(videoId);
        doNothing().when(videoRepositoryGateway).remover(videoId);

        removerVideoUseCase.remover(videoId);

        verify(favoritoRepositoryGateway).removerPorVideoId(videoId);
        verify(videoRepositoryGateway).remover(videoId);
    }
}

package com.grupo16.tcfase4.service;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ObterEstatisticaVideoUseCaseUnitTest {

    @InjectMocks
    private ObterEstatisticaVideoUseCase obterEstatisticaVideoUseCase;

    @Mock
    private VideoRepositoryGateway videoRepositoryGateway;

    @Mock
    private FavoritoRepositoryGateway favoritoRepositoryGateway;


    @Test
    void deveObterEstatisticas() {
        List<Video> videosList = List.of(
                Video.builder()
                        .quantidadeVisualizacao(1L)
                        .build(),
                Video.builder()
                        .quantidadeVisualizacao(1L)
                        .build(),
                Video.builder()
                        .quantidadeVisualizacao(1L)
                        .build());

        List<Favorito> favoritosList = List.of(
                Favorito.builder().build(),
                Favorito.builder().build());

        String resultado = """
                Total de videos: 3
                Total de videos favoritados: 2
                Media de visualizacoes: 1.0""";

        when(videoRepositoryGateway.obterTodosList()).thenReturn(videosList);
        when(favoritoRepositoryGateway.obterTodosReferenciandoVideoId()).thenReturn(favoritosList);

        assertEquals(resultado, obterEstatisticaVideoUseCase.obterEstatisticas());
    }

    @Test
    void deveObterTotalVideos() {
        List<Video> videos = List.of(
                Video.builder().build(),
                Video.builder().build(),
                Video.builder().build());

        when(videoRepositoryGateway.obterTodosList()).thenReturn(videos);

        var result = obterEstatisticaVideoUseCase.obterTotalVideos();

        assertEquals(3, result);
        verify(videoRepositoryGateway).obterTodosList();
    }

    @Test
    void deveObterTotalFavoritos() {
        List<Favorito> favoritosList = List.of(
                Favorito.builder().build(),
                Favorito.builder().build());

        when(favoritoRepositoryGateway.obterTodosReferenciandoVideoId()).thenReturn(favoritosList);

        var result = obterEstatisticaVideoUseCase.obterTotalFavoritos();

        assertEquals(2, result);
        verify(favoritoRepositoryGateway).obterTodosReferenciandoVideoId();
    }

    @Test
    void deveObterMediaVisualizacoes() {
    	List<Video> videos = List.of(
    			Video.builder()
                        .quantidadeVisualizacao(2L)
                        .build(),
    			Video.builder()
                        .quantidadeVisualizacao(0L)
                        .build()
    	);

    	when(videoRepositoryGateway.obterTodosList()).thenReturn(videos);

    	var result = obterEstatisticaVideoUseCase.obterMediaVisualizacoes();

    	assertEquals(1.0, result);
    	verify(videoRepositoryGateway).obterTodosList();
    }

    @Test
    void deveRetornarZeroSeNaoExistirVideos() {
        when(videoRepositoryGateway.obterTodosList()).thenReturn(List.of());

        var result = obterEstatisticaVideoUseCase.obterMediaVisualizacoes();

        assertEquals(0.0, result);
        verify(videoRepositoryGateway).obterTodosList();
    }
}

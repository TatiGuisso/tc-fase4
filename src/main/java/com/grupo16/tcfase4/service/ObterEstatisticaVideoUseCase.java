package com.grupo16.tcfase4.service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ObterEstatisticaVideoUseCase {

    private VideoRepositoryGateway videoRepositoryGateway;

    private FavoritoRepositoryGateway favoritoRepositoryGateway;


    public String obterEstatisticas() {

        return "Total de videos: " + obterTotalVideos() + "\n" +
                "Total de videos favoritados: " + obterTotalFavoritos() + "\n" +
                "Media de visualizacoes: " + obterMediaVisualizacoes();
    }

    private int obterTotalVideos() {

        return videoRepositoryGateway.obterTodosList().size();
    }

    private int obterTotalFavoritos() {

        return favoritoRepositoryGateway.obterTodosReferenciandoVideoId().size();
    }

    private Double obterMediaVisualizacoes() {

        List<Video> videos = videoRepositoryGateway.obterTodosList();

        if (!videos.isEmpty()) {
            return videos.stream().mapToDouble(Video::getQuantidadeVisualizacao).sum() / videos.size();
        }

        return 0.0;
    }
}

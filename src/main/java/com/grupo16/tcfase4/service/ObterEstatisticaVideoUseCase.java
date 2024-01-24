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


    public int obterTotalVideos() {

        return videoRepositoryGateway.obterTodosList().size();
    }

    public int obterTotalFavoritos(String usuarioId) {

        return favoritoRepositoryGateway.obterPorUsuarioId(usuarioId).size();
    }

    public Long obterMediaVisualizacoes() {

        List<Video> videos = videoRepositoryGateway.obterTodosList();

        if (!videos.isEmpty()) {
            return videos.stream().mapToLong(Video::getQuantidadeVisualizacao).sum() / videos.size();
        }

        return 0L;
    }
}

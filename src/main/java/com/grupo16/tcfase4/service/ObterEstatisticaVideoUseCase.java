package com.grupo16.tcfase4.service;

import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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
}

package com.grupo16.tcfase4.service;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RemoverVideoUseCase {

    private VideoRepositoryGateway videoRepositoryGateway;
    private FavoritoRepositoryGateway favoritoRepositoryGateway;

    public void remover(String videoId) {
    	favoritoRepositoryGateway.removerPorVideoId(videoId);
        videoRepositoryGateway.remover(videoId);
    }
}

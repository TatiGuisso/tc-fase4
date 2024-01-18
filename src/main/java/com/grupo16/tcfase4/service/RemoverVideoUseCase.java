package com.grupo16.tcfase4.service;

import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class RemoverVideoUseCase {

    private VideoRepositoryGateway videoRepositoryGateway;

    public void remover(String id) {
        videoRepositoryGateway.remover(id);
    }
}

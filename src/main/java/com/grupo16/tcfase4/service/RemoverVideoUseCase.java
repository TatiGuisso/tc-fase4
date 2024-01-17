package com.grupo16.tcfase4.service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 15/01/2024
 * Project Name: tc-fase4
 */

@Service
@AllArgsConstructor
@Slf4j
public class RemoverVideoUseCase {

    private VideoRepositoryGateway videoRepositoryGateway;

    public void remover(String id) {

        Optional<Video> videoOp = videoRepositoryGateway.obterPorId(id);

        if (videoOp.isEmpty()) {
            log.warn("Video não encontrado: id={}", id);
            throw new VideoNaoEncontradoException();
        }

        videoRepositoryGateway.remover(id);
    }
}

package com.grupo16.tcfase4.gateway;

import java.util.Optional;

import com.grupo16.tcfase4.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoRepositoryGateway {

	Page<Video> listarTodos(Pageable pageable);

	String salvar(Video video);

	Optional<Video> obterPorId(String id);

	void remover(String id);
}

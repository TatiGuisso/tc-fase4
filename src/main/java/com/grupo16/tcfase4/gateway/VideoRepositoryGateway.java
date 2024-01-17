package com.grupo16.tcfase4.gateway;

import java.util.Optional;

import com.grupo16.tcfase4.domain.Video;

public interface VideoRepositoryGateway {

	String salvar(Video video);

	Optional<Video> obterPorId(String id);

	void remover(String id);
}

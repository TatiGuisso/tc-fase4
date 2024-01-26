package com.grupo16.tcfase4.gateway;

import java.util.List;
import java.util.Optional;

import com.grupo16.tcfase4.domain.Favorito;

public interface FavoritoRepositoryGateway {
	
	String salvar(Favorito favorito);

	Optional<Favorito> obterPorUsuarioIdEVideoId(String usuarioId, String videoId);

	List<Favorito> obterPorUsuarioId(String usuarioId);

	List<Favorito> obterTodosReferenciandoVideoId();
	
	void removerPorVideoId(String videoId);
}

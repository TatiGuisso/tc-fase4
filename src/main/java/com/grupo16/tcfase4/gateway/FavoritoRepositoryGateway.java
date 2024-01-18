package com.grupo16.tcfase4.gateway;

import java.util.Optional;

import com.grupo16.tcfase4.domain.Favorito;

public interface FavoritoRepositoryGateway {
	
	String salvar(Favorito favorito);

	Optional<Favorito> obterPorUsuarioIdEVideoId(String usuarioId, String videoId);

}

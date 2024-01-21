package com.grupo16.tcfase4.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecomendarVideoUseCase {

	private FavoritoRepositoryGateway favoritoRepositoryGateway;
	
	private ObterVideoUseCase obterVideoUseCase;
	
	public List<Video> recomendar(String usuarioId) {
		
		List<Favorito> favoritos = favoritoRepositoryGateway.obterPorUsuarioId(usuarioId);
		Map<Categoria, Integer> contagemCategorias = new HashMap<>();
		
		for (Favorito favorito : favoritos) {
			Categoria categoria = obterVideoUseCase.obterPorId(favorito.getVideo().getId()).getCategoria();
			
			contagemCategorias.put(categoria, contagemCategorias.getOrDefault(categoria, 0) + 1);
		}
		Categoria categoriaMaisFavorita = null;

		int maxContagem = 0;
		for (Map.Entry<Categoria, Integer> contagem : contagemCategorias.entrySet()) {
			if (contagem.getValue() > maxContagem) {
		        maxContagem = contagem.getValue();
		        categoriaMaisFavorita = contagem.getKey();
		    }
		}
		
		return obterVideoUseCase.obter3PorCategoriaFavorita(categoriaMaisFavorita);
	}

}

package com.grupo16.tcfase4.gateway;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;

public interface VideoRepositoryGateway {

	Page<Video> obterTodosPageable(Pageable pageable);

	String salvar(Video video);

	Optional<Video> obterPorId(String id);

	void remover(String id);

	List<Video> buscaFiltrada(String titulo, LocalDate dataPublicacao, String categoria);

	List<Video> obter3PorCategoriaFavorita(Categoria categoriaMaisFavorita);

	List<Video> obterTodosList();
}

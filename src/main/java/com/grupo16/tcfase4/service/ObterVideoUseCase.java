package com.grupo16.tcfase4.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ObterVideoUseCase {

	private VideoRepositoryGateway videoRepositoryGateway;
	

	public Page<Video> listarTodos(Pageable pagina) {
		return videoRepositoryGateway.listarTodos(pagina);
	}

	public Video obterPorId(String id) {
		Optional<Video> videoOp = videoRepositoryGateway.obterPorId(id);

		if(videoOp.isEmpty()) {
			log.warn("Video não encontrado: id={}", id);
			throw new VideoNaoEncontradoException();
		}
		
		return videoOp.get();
	}

	public List<Video> buscaFiltrada(String titulo, LocalDate dataPublicacao, String categoria) {
		if (titulo == null && dataPublicacao == null && categoria == null) {
			log.warn("Nenhum filtro informado");
			// TODO: criar uma nova exceção?
			throw new VideoNaoEncontradoException();
		}

		return videoRepositoryGateway.buscaFiltrada(titulo, dataPublicacao, categoria);
	}

	public List<Video> obterPorCategoria(Categoria categoriaMaisFavorita) {
		
		return videoRepositoryGateway.obterPorCategoria(categoriaMaisFavorita);
	}
}

package com.grupo16.tcfase4.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.grupo16.tcfase4.domain.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ObterVideoUseCaseUniTest {
	
	@InjectMocks
	private ObterVideoUseCase obterVideoUseCase;
	
	@Mock
	private VideoRepositoryGateway videoRepositoryGateway;


	@Test
	void deveListarTodos() {
		Page<Video> listaDeVideos = new PageImpl<>(Arrays.asList(
				Video.builder().build(),
				Video.builder().build(),
				Video.builder().build()));

		when(videoRepositoryGateway.obterTodosPageable(any(Pageable.class))).thenReturn(listaDeVideos);

		var resultadoObtido = obterVideoUseCase.listarTodos(Pageable.unpaged());

		assertThat(resultadoObtido).hasSize(3);
		assertThat(resultadoObtido.getContent())
				.asList()
				.allSatisfy(video -> assertThat(video).isNotNull().isInstanceOf(Video.class));
		verify(videoRepositoryGateway, times(1)).obterTodosPageable(any(Pageable.class));
	}

	@Test
	void deveObterVideoPorId() {
		String id = UUID.randomUUID().toString();
		Video video = Video.builder().build();
		
		when(videoRepositoryGateway.obterPorId(id)).thenReturn(Optional.of(video));
		
		Video videoObtido = obterVideoUseCase.obterPorId(id);
		
		assertEquals(video, videoObtido);
		
	}
	
	@Test
	void deveRetornarExceptionAoObterVideoPorID() {
		String id = UUID.randomUUID().toString();
		
		Optional<Video> videoOp = Optional.empty();
		
		when(videoRepositoryGateway.obterPorId(id)).thenReturn(videoOp);
		
		assertThrows(VideoNaoEncontradoException.class, 
				() -> obterVideoUseCase.obterPorId(id));
		
		verify(videoRepositoryGateway).obterPorId(id);
	}

	@Test
	void deveRealizarBuscaFiltrada() {
		String titulo = "Teste";
		LocalDate dataPublicacao = LocalDate.now();
		String categoria = "ACAO";

		List<Video> videos = Arrays.asList(
				Video.builder()
						.titulo("Filme 1")
						.dataPublicacao(LocalDate.now())
						.categoria(Categoria.ACAO).build(),
				Video.builder().titulo("Filme 2")
						.dataPublicacao(LocalDate.now())
						.categoria(Categoria.COMEDIA).build());

		when(videoRepositoryGateway.buscaFiltrada(titulo, dataPublicacao, categoria)).thenReturn(videos);

		var result = obterVideoUseCase.buscaFiltrada(titulo, dataPublicacao, categoria);

		assertThat(result).isNotEmpty()
				.allSatisfy(video -> assertThat(video).isNotNull().isInstanceOf(Video.class));
		verify(videoRepositoryGateway).buscaFiltrada(titulo, dataPublicacao, categoria);
	}
	
	@Test
	void deveObter3VideosPorCategoriaFavorita() {
		
		List<Video> videos = Arrays.asList(
				Video.builder().categoria(Categoria.ANIMACAO).build(),
				Video.builder().categoria(Categoria.ANIMACAO).build(),
				Video.builder().categoria(Categoria.ANIMACAO).build());
		
		when(videoRepositoryGateway.obter3PorCategoriaFavorita(Categoria.ANIMACAO)).thenReturn(videos);
		
		List<Video> resultList = obterVideoUseCase.obter3PorCategoriaFavorita(Categoria.ANIMACAO);
		
		verify(videoRepositoryGateway).obter3PorCategoriaFavorita(Categoria.ANIMACAO);
		assertEquals(3, resultList.size());
	}
}

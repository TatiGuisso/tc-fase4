package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

@ExtendWith(MockitoExtension.class)
class CriarAlterarVideoUseCaseUnitTest {
	
	@InjectMocks
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	@Mock
	private VideoRepositoryGateway videoRepositoryGateway;

	@Mock
	private ObterVideoUseCase obterVideoUseCase;
	
	@Test
	void deveSalvar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		
		doReturn(video.getId()).when(videoRepositoryGateway).salvar(any(Video.class));
		
		String result = criarAlterarVideoUseCase.salvar(video);
		
		verify(videoRepositoryGateway, times(1)).salvar(any(Video.class));
		
		assertEquals(video.getId(), result);
		
	}
	
	@Test
	void deveAlterar() {
		String id = UUID.randomUUID().toString();
		
		Video videoNovo = Video.builder()
				.id(id)
				.titulo("Titulo Alterado")
				.descricao("Nova descrição")
				.categoria(Categoria.DRAMA)
				.build();

		Video videoAntigo = Video.builder()
				.id(id)
				.titulo("Teste")
				.descricao("BlaBlaBla")
				.categoria(Categoria.ACAO)
				.dataPublicacao(LocalDate.of(1990, 7, 18))
				.build();
		
		when(obterVideoUseCase.obterPorId(id)).thenReturn(videoAntigo);

		criarAlterarVideoUseCase.alterar(videoNovo);
		
		ArgumentCaptor<Video> videoCaptor = ArgumentCaptor.forClass(Video.class);
		verify(videoRepositoryGateway, times(1)).salvar(videoCaptor.capture());
		
		Video videoSalvo = videoCaptor.getValue();
		
		assertEquals(id, videoSalvo.getId());
		assertEquals(videoNovo.getTitulo(), videoSalvo.getTitulo());
		assertEquals(videoNovo.getDescricao(), videoSalvo.getDescricao());
		assertEquals(videoNovo.getCategoria(), videoSalvo.getCategoria());
		assertEquals(videoAntigo.getDataPublicacao(), videoSalvo.getDataPublicacao());
		
	}

}

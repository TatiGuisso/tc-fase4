package com.grupo16.tcfase4.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;
import com.grupo16.tcfase4.service.CriarFavoritoUseCase;
import com.grupo16.tcfase4.service.ObterVideoUseCase;
import com.grupo16.tcfase4.service.RemoverVideoUseCase;

@ExtendWith(MockitoExtension.class)
class VideoControllerUnitTest {
	
	@InjectMocks
	private VideoController videoController;
	
	@Mock
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;

	@Mock
	private RemoverVideoUseCase removerVideoUseCase;

	@Mock
	private ObterVideoUseCase obterVideoUseCase;
	
	@Mock
	private CriarFavoritoUseCase favoritoUseCase;
	
	@Test
	void deveSalvar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		
		VideoJson videoJsonMock = mock(VideoJson.class); 		
		when(videoJsonMock.mapperJsonToDomain(null)).thenReturn(video);

		when(criarAlterarVideoUseCase.salvar(video)).thenReturn(video.getId());

		String result = videoController.salvar(videoJsonMock);
		
		verify(criarAlterarVideoUseCase).salvar(video);
		assertEquals(video.getId(), result);
		
	}
	
	@Test
	void deveAlterar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		
		VideoJson videoJsonMock = mock(VideoJson.class);
		when(videoJsonMock.mapperJsonToDomain(video.getId())).thenReturn(video);
		
		videoController.alterar(video.getId(), videoJsonMock);
		
		verify(criarAlterarVideoUseCase).alterar(video);
		
	}
	
	@Test
	void deveRemover() {
		String videoId = UUID.randomUUID().toString();
		doNothing().when(removerVideoUseCase).remover(videoId);
		videoController.remover(videoId);		
		verify(removerVideoUseCase).remover(videoId);
	}
	
	@Test
	void deveObterPorId() {
		String id = UUID.randomUUID().toString();
		Video video = Video.builder()
				.id(id)
				.titulo("ABCD")
				.descricao("Novo filme")
				.categoria(Categoria.GERRA)
				.build();
		
		when(obterVideoUseCase.obterPorId(id)).thenReturn(video);
		
		VideoJson videoJson = videoController.obterPorId(id);
		
		verify(obterVideoUseCase).obterPorId(id);
		assertEquals(video.getId(), videoJson.getId());
		assertEquals(video.getTitulo(), videoJson.getTitulo());
		assertEquals(video.getDescricao(), videoJson.getDescricao());
		assertEquals(video.getCategoria().toString(), videoJson.getCategoria());
		
	}
	
	@Test
	void deveSalvarFavorito() {
		String videoId = UUID.randomUUID().toString();
		String usuarioId = UUID.randomUUID().toString();
		String favoritoId = UUID.randomUUID().toString();
		
		when(favoritoUseCase.salvar(videoId, usuarioId)).thenReturn(favoritoId);
				
		String result = videoController.favoritar(videoId, usuarioId);
		
		verify(favoritoUseCase).salvar(videoId, usuarioId);
		assertEquals(favoritoId, result);
	}

}

package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoFavoritadoMaisDeUmaVezPeloUsuarioException;

@ExtendWith(MockitoExtension.class)
class CriarFavoritoUseCaseUnitTest {

	@InjectMocks
	private CriarFavoritoUseCase criarFavoritoUseCase;
	
	@Mock
	private ObterVideoUseCase obterVideoUseCase;
	
	@Mock
	private ObterUsuarioUseCase obterUsuarioUseCase;

	@Mock
	private FavoritoRepositoryGateway favoritoRepositoryGateway;
	
	@Test
	void deveSalvarComSucesso() {
		String favoritoId = UUID.randomUUID().toString();
		String usuarioId = UUID.randomUUID().toString();
		String videoId = UUID.randomUUID().toString();
		Usuario usuario = Usuario.builder().id(usuarioId).build();
		Video video = Video.builder().id(videoId).build();
		
		when(obterUsuarioUseCase.obterPorId(usuarioId)).thenReturn(usuario);
		when(obterVideoUseCase.obterPorId(videoId)).thenReturn(video);
		
		when(favoritoRepositoryGateway.obterPorUsuarioIdEVideoId(usuarioId, videoId)).thenReturn(Optional.empty());
		
		ArgumentCaptor<Favorito> favoritoAC = ArgumentCaptor.forClass(Favorito.class); 
		when(favoritoRepositoryGateway.salvar(any(Favorito.class))).thenReturn(favoritoId);
		
		String favoritoIdResult = criarFavoritoUseCase.salvar(videoId, usuarioId);
		
		verify(obterUsuarioUseCase).obterPorId(usuarioId);
		verify(obterVideoUseCase).obterPorId(videoId);
		verify(favoritoRepositoryGateway).salvar(favoritoAC.capture());
		Favorito favorito = favoritoAC.getValue();
		
		assertEquals(favoritoId, favoritoIdResult);
		assertEquals(usuarioId, favorito.getUsuario().getId());
		assertEquals(videoId, favorito.getVideo().getId());
		
	}

	@Test
	void deveFalharAoSalvar() {
		String usuarioId = UUID.randomUUID().toString();
		String videoId = UUID.randomUUID().toString();
		Usuario usuario = Usuario.builder().id(usuarioId).build();
		Video video = Video.builder().id(videoId).build();
		Favorito favorito = Favorito.builder().id(UUID.randomUUID().toString()).video(video).usuario(usuario).build();
				
		when(obterUsuarioUseCase.obterPorId(usuarioId)).thenReturn(usuario);
		when(obterVideoUseCase.obterPorId(videoId)).thenReturn(video);
		
		when(favoritoRepositoryGateway.obterPorUsuarioIdEVideoId(usuarioId, videoId)).thenReturn(Optional.of(favorito));
		
		assertThrows(VideoFavoritadoMaisDeUmaVezPeloUsuarioException.class, 
				() -> criarFavoritoUseCase.salvar(videoId, usuarioId));
		
		verify(obterUsuarioUseCase).obterPorId(usuarioId);
		verify(obterVideoUseCase).obterPorId(videoId);
		verify(favoritoRepositoryGateway).obterPorUsuarioIdEVideoId(usuarioId, videoId);
		verify(favoritoRepositoryGateway, never()).salvar(favorito);
		
	}
	
}

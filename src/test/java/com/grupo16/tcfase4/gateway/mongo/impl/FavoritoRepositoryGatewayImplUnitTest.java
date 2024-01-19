package com.grupo16.tcfase4.gateway.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.mongo.document.FavoritoDocument;
import com.grupo16.tcfase4.gateway.mongo.document.UsuarioDocument;
import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.FavoritoRepository;

@ExtendWith(MockitoExtension.class)
class FavoritoRepositoryGatewayImplUnitTest {
	
	@InjectMocks
	private FavoritoRepositoryGatewayImpl favoritoRepositoryGatewayImpl;
	
	@Mock
	private FavoritoRepository favoritoRepository;
	
	@Test
	void deveSalvar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		Usuario usuario = Usuario.builder().id(UUID.randomUUID().toString()).build();
		Favorito favorito = Favorito.builder()
				.id(UUID.randomUUID().toString())
				.video(video)
				.usuario(usuario).build();
		
		FavoritoDocument favoritoDocument = FavoritoDocument.builder().id(favorito.getId()).build();
		
		when(favoritoRepository.save(any(FavoritoDocument.class))).thenReturn(favoritoDocument);
		
		String id = favoritoRepositoryGatewayImpl.salvar(favorito);
		ArgumentCaptor<FavoritoDocument> favoritoDocCaptor = ArgumentCaptor.forClass(FavoritoDocument.class);
		verify(favoritoRepository).save(favoritoDocCaptor.capture());
		
		FavoritoDocument favoritoDoc = favoritoDocCaptor.getValue();
		
		assertEquals(favorito.getId(), id);
		assertEquals(favorito.getUsuario().getId(), favoritoDoc.getUsuarioId());
		assertEquals(favorito.getVideo().getId(), favoritoDoc.getVideoId());
	}
	
	@Test
	void deveRetornarExceptionAoSalvar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		Usuario usuario = Usuario.builder().id(UUID.randomUUID().toString()).build();
		Favorito favorito = Favorito.builder()
				.id(UUID.randomUUID().toString())
				.video(video)
				.usuario(usuario).build();
		
		doThrow(new RuntimeException())
			.when(favoritoRepository).save(any(FavoritoDocument.class));
		
		assertThrows(ErroAoAcessarBancoDadosException.class, 
				() -> favoritoRepositoryGatewayImpl.salvar(favorito));
		
		verify(favoritoRepository).save(any(FavoritoDocument.class));		
	}
	
	@Test
	void deveObterPorUsuarioIdeVideoId() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		Usuario usuario = Usuario.builder().id(UUID.randomUUID().toString()).build();
		Favorito favorito = Favorito.builder()
				.id(UUID.randomUUID().toString())
				.video(video)
				.usuario(usuario)
				.build();
		
		FavoritoDocument favoritoDocMock = mock(FavoritoDocument.class);
		
		when(favoritoRepository.findByUsuarioIdAndVideoId(usuario.getId(), video.getId()))
			.thenReturn(Optional.of(favoritoDocMock));
		
		when(favoritoDocMock.mapperDocumentToDomain()).thenReturn(favorito);
		
		Optional<Favorito> favoritoOptional = favoritoRepositoryGatewayImpl.obterPorUsuarioIdEVideoId(usuario.getId(), video.getId());
		Favorito result = favoritoOptional.get();
		
		verify(favoritoRepository).findByUsuarioIdAndVideoId(usuario.getId(), video.getId());
		
		assertEquals(favorito, result);
		assertEquals(favorito.getId(), result.getId());
		assertEquals(favorito.getUsuario(), result.getUsuario());
		assertEquals(favorito.getVideo(), result.getVideo());
		
	}
	
	@Test
	void deveRetornarOptionalVazioAoObterPorUsuarioIdeVideoId() {
		String id = UUID.randomUUID().toString();
		
		when(favoritoRepository.findByUsuarioIdAndVideoId(id, id)).thenReturn(Optional.empty());
		
		Optional<Favorito> favoritoOp = favoritoRepositoryGatewayImpl.obterPorUsuarioIdEVideoId(id, id);
		
		assertTrue(favoritoOp.isEmpty());
		
	}
	
	@Test
	void deveRetornarExceptionAoObterFavoritoPorUsuarioIdEVideoId() {
		
	}

}

package com.grupo16.tcfase4.gateway.mongo.document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Favorito;

@ExtendWith(MockitoExtension.class)
class FavoritoDocumentUnitTest {
	
	@Test
	void deveFazerMapperDeDocumentParaDomain() {
		FavoritoDocument favoritoDocument = FavoritoDocument.builder()
				.id(UUID.randomUUID().toString())
				.videoId(UUID.randomUUID().toString())
				.usuarioId(UUID.randomUUID().toString())
				.build();
		
		Favorito domain = favoritoDocument.mapperDocumentToDomain();
		
		assertEquals(favoritoDocument.getId(), domain.getId());
		assertEquals(favoritoDocument.getVideoId(), domain.getVideo().getId());
		assertEquals(favoritoDocument.getUsuarioId(), domain.getUsuario().getId());
		
	}

}

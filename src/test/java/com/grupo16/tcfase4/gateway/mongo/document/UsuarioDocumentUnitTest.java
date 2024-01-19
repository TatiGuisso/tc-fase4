package com.grupo16.tcfase4.gateway.mongo.document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.grupo16.tcfase4.domain.Usuario;

class UsuarioDocumentUnitTest {
	
	@Test
	void deveFazerMapperDeDocumentParaDomain() {
		UsuarioDocument usuarioDocument = UsuarioDocument.builder()
				.id(UUID.randomUUID().toString())
				.nome("Usuario Teste")
				.build();
		
		Usuario domain = usuarioDocument.mapperDocumentToDomain();
		
		assertEquals(usuarioDocument.getId(), domain.getId());
		assertEquals(usuarioDocument.getNome(), domain.getNome());
	}

}

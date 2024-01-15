package com.grupo16.tcfase4.gateway.mongo.document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;

@ExtendWith(MockitoExtension.class)
class VideoDocumentUnitTest {
	
	@Test
	void deveFazerMapperDeDocumentParaDomain() {
		VideoDocument videoDocument = VideoDocument.builder()
				.id(UUID.randomUUID().toString())
				.titulo("TESTEAAA")
				.descricao("BlaBla")
				.categoria("COMEDIA")
				.url("www.anyvideo.com")
				.dataPublicacao(LocalDate.now())
				.quantidadeVisualizacao(15L)
				.build();
		
		Video video = videoDocument.mapperDocumentToDomain();
		
		assertEquals(videoDocument.getId(), video.getId());
		assertEquals(videoDocument.getTitulo(), video.getTitulo());
		assertEquals(videoDocument.getDescricao(), video.getDescricao());
		assertEquals(videoDocument.getCategoria(), video.getCategoria().toString());
		assertEquals(videoDocument.getUrl(), video.getUrl());
		assertEquals(videoDocument.getDataPublicacao(), video.getDataPublicacao());
		assertEquals(videoDocument.getQuantidadeVisualizacao(), video.getQuantidadeVisualizacao());
	}

}

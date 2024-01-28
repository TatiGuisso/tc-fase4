package com.grupo16.tcfase4.gateway.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;

@ExtendWith(MockitoExtension.class)
class VideoJsonUnitTest {
	
	@Test
	void deveFazerMapperDeJsonParaDomain() {
		
		VideoJson videoJson = VideoJson.builder()
				.id(UUID.randomUUID().toString())
				.titulo("TESTEAAA")
				.descricao("BlaBla")
				.categoria("COMEDIA")
				.quantidadeVisualizacao(0L)
				.build();
		
		Video video = videoJson.mapperJsonToDomain(videoJson.getId());
		
		assertEquals(videoJson.getId(), video.getId());
		assertEquals(videoJson.getTitulo(), video.getTitulo());
		assertEquals(videoJson.getDescricao(), video.getDescricao());
		assertEquals(videoJson.getCategoria(), video.getCategoria().toString());
		assertEquals(0L, video.getQuantidadeVisualizacao());
		
	}
	

}

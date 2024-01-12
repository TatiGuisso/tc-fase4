package com.grupo16.tcfase4.gateway.controller.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;
import com.grupo16.tcfase4.gateway.mongo.impl.VideoRepositoryGatewayImpl;
import com.grupo16.tcfase4.gateway.mongo.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
class VideoRepositoryGatewayImplUnitTest {
	
	@Mock
	private VideoRepository videoRepository;
	
	@InjectMocks
	private VideoRepositoryGatewayImpl videoRepositoryGatewayImpl;
	
	@Test
	void deveSalvar() {
		String id = UUID.randomUUID().toString();
		LocalDate data = LocalDate.of(2022, 10, 8);
		Video video = Video.builder()
				.id(id)
				.titulo("TESTEAAA")
				.descricao("BlaBla")
				.dataPublicacao(data)
				.categoria(Categoria.valueOf("TERROR"))
				.quantidadeVisualizacao(18L)
				.build();

		VideoDocument videoDocument = VideoDocument.builder().id(id).build();

		ArgumentCaptor<VideoDocument> videoDocCaptor = ArgumentCaptor.forClass(VideoDocument.class);
		
		when(videoRepository.save(videoDocCaptor.capture())).thenReturn(videoDocument);
		
		String result = videoRepositoryGatewayImpl.salvar(video);
		
		verify(videoRepository).save(videoDocCaptor.capture());
		
		VideoDocument videoDoc = videoDocCaptor.getValue();
		
		assertEquals(video.getId(), result);
		assertEquals(video.getId(), videoDoc.getId());
		assertEquals(video.getTitulo(), videoDoc.getTitulo());
		assertEquals(video.getDescricao(), videoDoc.getDescricao());
		assertEquals(video.getDataPublicacao(), videoDoc.getDataPublicacao());
		assertEquals(video.getCategoria().toString(), videoDoc.getCategoria());
		assertEquals(video.getQuantidadeVisualizacao(), videoDoc.getQuantidadeVisualizacao());
		
	}

}

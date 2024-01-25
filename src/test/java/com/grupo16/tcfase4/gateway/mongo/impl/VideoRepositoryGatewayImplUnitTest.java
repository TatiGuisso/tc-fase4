package com.grupo16.tcfase4.gateway.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
class VideoRepositoryGatewayImplUnitTest {
	
	@Mock
	private VideoRepository videoRepository;
	
	@InjectMocks
	private VideoRepositoryGatewayImpl videoRepositoryGatewayImpl;
	
	@Test
	void deveSalvarOuAlterar() {
		Video video = Video.builder()
				.id(UUID.randomUUID().toString())
				.titulo("TESTEAAA")
				.descricao("BlaBla")
				.dataPublicacao(LocalDate.of(2022, 10, 8))
				.categoria(Categoria.valueOf("TERROR"))
				.build();

		VideoDocument videoDocument = VideoDocument.builder().id(video.getId()).build();

		ArgumentCaptor<VideoDocument> videoDocCaptor = ArgumentCaptor.forClass(VideoDocument.class);
		
		when(videoRepository.save(any(VideoDocument.class))).thenReturn(videoDocument);

		String result = videoRepositoryGatewayImpl.salvar(video);
		
		verify(videoRepository).save(videoDocCaptor.capture());
		
		VideoDocument videoDoc = videoDocCaptor.getValue();
		
		assertEquals(video.getId(), result);
		assertEquals(video.getId(), videoDoc.getId());
		assertEquals(video.getTitulo(), videoDoc.getTitulo());
		assertEquals(video.getDescricao(), videoDoc.getDescricao());
		assertEquals(video.getDataPublicacao(), videoDoc.getDataPublicacao());
		assertEquals(video.getCategoria().toString(), videoDoc.getCategoria());
		
	}
	
	@Test
	void deveGerarExceptionAoSalvarOuAlterarVideo() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).categoria(Categoria.DRAMA).build();
				
		doThrow(new RuntimeException())
			.when(videoRepository).save(any(VideoDocument.class));
		
		assertThrows(ErroAoAcessarBancoDadosException.class,
				() -> videoRepositoryGatewayImpl.salvar(video));
		
		verify(videoRepository).save(any(VideoDocument.class));
	}
	
	@Test
	void deveObterPorId() {
		String id = UUID.randomUUID().toString();

		Video video = Video.builder().build();
		VideoDocument videoDocMock = Mockito.mock(VideoDocument.class);
		when(videoDocMock.mapperDocumentToDomain()).thenReturn(video);
		Optional<VideoDocument> videoDocumentOp = Optional.of(videoDocMock);
		
		when(videoRepository.findById(id)).thenReturn(videoDocumentOp);

		Optional<Video> videoOptionalResult = videoRepositoryGatewayImpl.obterPorId(id);
		
		verify(videoRepository).findById(id);
		assertEquals(video, videoOptionalResult.get());
		
	}
	
	@Test
	void deveGerarExceptionAoObterPorId() {
		String id = UUID.randomUUID().toString();
		
		doThrow(new RuntimeException()).when(videoRepository).findById(id);
		
		assertThrows(ErroAoAcessarBancoDadosException.class, 
				() -> videoRepositoryGatewayImpl.obterPorId(id));
		
		verify(videoRepository).findById(id);
		
	}
	
	@Test
	void deveObterPorCategoria() {
		VideoDocument video1 = VideoDocument.builder().categoria("ACAO").build();
		VideoDocument video2 = VideoDocument.builder().categoria("ACAO").build();
		List<VideoDocument> videosDoc = Arrays.asList(video1, video2);
		when(videoRepository.findTop3ByCategoria(Categoria.ACAO)).thenReturn(videosDoc);
		
		List<Video> videos = videoRepositoryGatewayImpl.obter3PorCategoriaFavorita(Categoria.ACAO);
		
		assertEquals(2, videos.size());
	}
	
	@Test
	void deveRetornarExceptionAoObterPorCategoria() {
		
		doThrow(new RuntimeException()).when(videoRepository).findTop3ByCategoria(Categoria.ACAO);
		
		assertThrows(ErroAoAcessarBancoDadosException.class, 
				() -> videoRepositoryGatewayImpl.obter3PorCategoriaFavorita(Categoria.ACAO));
	}
	
	@Test
	void deveRemover() {
		String videoId = UUID.randomUUID().toString();
		videoRepositoryGatewayImpl.remover(videoId);
		verify(videoRepository).deleteById(videoId);
	}
	
	@Test
	void deveRetornarExceptionAoRemover() {
		String videoId = UUID.randomUUID().toString();
		doThrow(new RuntimeException()).when(videoRepository).deleteById(videoId);
		
		assertThrows(ErroAoAcessarBancoDadosException.class,
				() -> videoRepositoryGatewayImpl.remover(videoId));
	}
	
}

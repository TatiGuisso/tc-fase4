package com.grupo16.tcfase4.gateway.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@ExtendWith(MockitoExtension.class)
class VideoRepositoryGatewayImplUnitTest {
	
	@Mock
	private VideoRepository videoRepository;
	
	@Mock
	private MongoTemplate mongoTemplate;
	
	@InjectMocks
	private VideoRepositoryGatewayImpl videoRepositoryGatewayImpl;


	@Test
	void deveObterTodosPageable() {
		Page<VideoDocument> videoPage = new PageImpl<>(Collections.singletonList(
				VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.titulo("Teste")
						.descricao("Teste de paginação")
						.categoria("DRAMA")
						.build()));

		when(videoRepository.findAllByOrderByDataPublicacaoDesc(any(Pageable.class))).thenReturn(videoPage);

		videoRepositoryGatewayImpl.obterTodosPageable(Pageable.unpaged());

		verify(videoRepository).findAllByOrderByDataPublicacaoDesc(any(Pageable.class));
	}

	@Test
	void deveGerarExceptionAoObterTodosPageable() {
		var pageable = Pageable.unpaged();

		doThrow(new RuntimeException())
				.when(videoRepository).findAllByOrderByDataPublicacaoDesc(any(Pageable.class));

		assertThrows(ErroAoAcessarBancoDadosException.class,
				() -> videoRepositoryGatewayImpl.obterTodosPageable(pageable));

		verify(videoRepository).findAllByOrderByDataPublicacaoDesc(any(Pageable.class));
	}

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

	@Test
	void deveObterTodosList() {
		List<VideoDocument> videoDocumentList = List.of(
				VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.categoria("COMEDIA")
						.build(),
				VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.categoria("ACAO")
						.build(),
				VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.categoria("DRAMA")
						.build());

		when(videoRepository.findAll()).thenReturn(videoDocumentList);

		var result = videoRepositoryGatewayImpl.obterTodosList();

		verify(videoRepository).findAll();
		assertEquals(3, result.size());
	}

	@Test
	void deveRetornarExceptionAoObterTodosList() {
		doThrow(new RuntimeException()).when(videoRepository).findAll();

		assertThrows(ErroAoAcessarBancoDadosException.class,
				() -> videoRepositoryGatewayImpl.obterTodosList());
	}
	
	@Test
	void deveObterPorFiltroTitulo() {
		String titulo = "Titulo";
		List<VideoDocument> videos = Arrays.asList(VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.titulo(titulo)
						.categoria("COMEDIA")
						.build());
		
		when(mongoTemplate.find(any(Query.class), eq(VideoDocument.class)))
			.thenReturn(videos);
		
		List<Video> result = videoRepositoryGatewayImpl.buscaFiltrada(titulo, null, null);
		
		verify(mongoTemplate).find(any(Query.class), eq(VideoDocument.class));
		
		assertEquals(titulo, result.get(0).getTitulo());
		assertEquals("COMEDIA", result.get(0).getCategoria().toString());
		assertEquals(1, result.size());
	}

	@Test
	void deveObterPorFiltroDataPublicacao() {
		LocalDate dataTeste = LocalDate.of(2024, 1, 25);
		List<VideoDocument> videoDocumentList = Arrays.asList(
				VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.dataPublicacao(LocalDate.of(2024, 1, 25))
						.categoria("ACAO")
						.build());

		when(mongoTemplate.find(any(Query.class), eq(VideoDocument.class))).thenReturn(videoDocumentList);

		var result = videoRepositoryGatewayImpl.buscaFiltrada(null, dataTeste, null);

		verify(mongoTemplate).find(any(Query.class), eq(VideoDocument.class));

		assertEquals(dataTeste, result.get(0).getDataPublicacao());
		assertEquals(1, result.size());
	}

	@Test
	void deveObterPorFiltroCategoria() {
		String categoria = "ACAO";
		List<VideoDocument> videoDocumentList = Arrays.asList(
				VideoDocument.builder()
						.id(UUID.randomUUID().toString())
						.categoria("ACAO")
						.build());

		when(mongoTemplate.find(any(Query.class), eq(VideoDocument.class))).thenReturn(videoDocumentList);

		var result = videoRepositoryGatewayImpl.buscaFiltrada(null, null, categoria);

		verify(mongoTemplate).find(any(Query.class), eq(VideoDocument.class));

		assertEquals(categoria, result.get(0).getCategoria().toString());
		assertEquals(1, result.size());
	}
}

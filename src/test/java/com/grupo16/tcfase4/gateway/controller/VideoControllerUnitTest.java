package com.grupo16.tcfase4.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.grupo16.tcfase4.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;

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
	private ObterUrlVideoUseCase obterUrlVideoUseCase;
	
	@Mock
	private CriarFavoritoUseCase favoritoUseCase;
	
	@Mock
	private RecomendarVideoUseCase recomendarVideoUseCase;

	@Mock
	private ObterEstatisticaVideoUseCase obterEstatisticaVideoUseCase;


	@Test
	void deveListar() {
		Page<Video> videoPage = new PageImpl<>(Collections.singletonList(
				Video.builder()
						.id(UUID.randomUUID().toString())
						.titulo("Teste")
						.descricao("Teste de paginação")
						.categoria(Categoria.FICCAO)
						.build()));

		when(obterVideoUseCase.listarTodos(any(Pageable.class))).thenReturn(videoPage);

		List<VideoJson> videosJson = videoController.listar(0, 5);

		verify(obterVideoUseCase).listarTodos(any(Pageable.class));
		assertEquals(1, videosJson.size());
	}

	@Test
	void devePesquisar() {
		String titulo = "Teste";
		LocalDate dataPublicacao = LocalDate.of(2024, 1, 22);
		String categoria = "ACAO";

		List<Video> videos = Arrays.asList(
				Video.builder()
						.titulo("Filme 1")
						.dataPublicacao(LocalDate.now())
						.categoria(Categoria.ACAO).build(),
				Video.builder().titulo("Filme 2")
						.dataPublicacao(LocalDate.now())
						.categoria(Categoria.COMEDIA).build(),
				Video.builder().titulo("Filme 3")
						.dataPublicacao(LocalDate.now())
						.categoria(Categoria.ACAO).build()
		);

		when(obterVideoUseCase.buscaFiltrada(titulo, dataPublicacao, categoria))
				.thenReturn(videos.stream()
						.filter(video -> video.getCategoria().equals(Categoria.ACAO))
						.toList());

		List<VideoJson> result = videoController.pesquisar(titulo, dataPublicacao, categoria);

		verify(obterVideoUseCase).buscaFiltrada(titulo, dataPublicacao, categoria);
		assertEquals(2, result.size());
	}

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
				.categoria(Categoria.GUERRA)
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
	
	@Test
	void deveRecomendarVideo() {
		String usuarioId = UUID.randomUUID().toString();
		
		Video video1 = Video.builder()
				.id(UUID.randomUUID().toString())
				.titulo("Platoon")
				.descricao("Baseado em fatos reais.")
				.categoria(Categoria.GUERRA)
				.build();
		Video video2 = Video.builder()
				.id(UUID.randomUUID().toString())
				.titulo("Soldado")
				.descricao("Novo filme")
				.categoria(Categoria.GUERRA)
				.build();
		
		List<Video> videos = Arrays.asList(video1, video2);
		
		when(recomendarVideoUseCase.recomendar(usuarioId)).thenReturn(videos);
		
		List<VideoJson> videosJson = videoController.recomendar(usuarioId);
		
		verify(recomendarVideoUseCase).recomendar(usuarioId);
		assertEquals(2, videosJson.size());
		assertEquals(video1.getId(), videos.get(0).getId());
		assertEquals(video2.getId(), videos.get(1).getId());
		
	}
	
	@Test
	void deveFazerUpload() throws IOException {
		String videoId = UUID.randomUUID().toString();
		String contentType = "QVR37Hb9xm";
		MultipartFile multipartFile = new MockMultipartFile("AC6RIMpusn","yoBSqO69GA","VNMZyotcIp",contentType.getBytes());
		
		doNothing().when(criarAlterarVideoUseCase).upload(videoId, multipartFile.getBytes());
		
		videoController.upload(videoId, multipartFile);
		
		verify(criarAlterarVideoUseCase).upload(videoId, multipartFile.getBytes());
	}
	
	@Test
	void deveObterUrl() {
		String videoId = UUID.randomUUID().toString();
		String url = "www.video.com.br";
		when(obterUrlVideoUseCase.obterUrl(videoId)).thenReturn(url);

		String result = videoController.obterUrl(videoId);

		verify(obterUrlVideoUseCase).obterUrl(videoId);
		assertEquals(url, result);
	}

	@Test
	void deveObterEstatisticas() {
		when(obterEstatisticaVideoUseCase.obterEstatisticas()).thenReturn("Estatisticas");

		String result = videoController.obterEstatisticas();

		verify(obterEstatisticaVideoUseCase).obterEstatisticas();
		assertEquals("Estatisticas", result);
	}
}

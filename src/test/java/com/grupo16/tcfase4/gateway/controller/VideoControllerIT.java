package com.grupo16.tcfase4.gateway.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;
import com.grupo16.tcfase4.service.CriarFavoritoUseCase;
import com.grupo16.tcfase4.service.ObterEstatisticaVideoUseCase;
import com.grupo16.tcfase4.service.ObterUrlVideoUseCase;
import com.grupo16.tcfase4.service.ObterVideoUseCase;
import com.grupo16.tcfase4.service.RecomendarVideoUseCase;
import com.grupo16.tcfase4.service.RemoverVideoUseCase;

@WebMvcTest(VideoController.class)
class VideoControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase; 

	@MockBean   
	private CriarFavoritoUseCase favoritoUseCase;

	@MockBean
	private RemoverVideoUseCase removerVideoUseCase;

	@MockBean
	private ObterVideoUseCase obterVideoUseCase;

	@MockBean
	private ObterUrlVideoUseCase obterUrlVideoUseCase;

	@MockBean
	private RecomendarVideoUseCase recomendarVideoUseCase;

	@MockBean
	private ObterEstatisticaVideoUseCase obterEstatisticaVideoUseCase;

	private VideoJson videoJson = VideoJson.builder()
			.id(UUID.randomUUID().toString())
			.titulo("Teste")
			.categoria("ACAO")
			.build();

	private Video video = Video.builder()
			.id(UUID.randomUUID().toString())
			.titulo("Teste1")
			.categoria(Categoria.ACAO)
			.dataPublicacao(LocalDate.now())
			.build();

	private List<Video> videos = Arrays.asList(
			Video.builder()
			.id(UUID.randomUUID().toString())
			.titulo("Teste1")
			.categoria(Categoria.ACAO)
			.build(),
			Video.builder()
			.id(UUID.randomUUID().toString())
			.titulo("Teste2")
			.categoria(Categoria.ACAO)
			.build());

	@Test
	void deveSalvar()throws Exception{
		when(criarAlterarVideoUseCase.salvar(any(Video.class))).thenReturn(videoJson.getId());

		mockMvc.perform(MockMvcRequestBuilders.post("/videos")
				.content(objectMapper.writeValueAsString(videoJson))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());

	}

	@Test
	void deveObterUrl()throws Exception{
		String videoId = videoJson.getId(); 
		when(obterUrlVideoUseCase.obterUrl(anyString())).thenReturn("www.urlvideo.com");

		mockMvc.perform(MockMvcRequestBuilders.get("/videos/{id}/url", videoId))
		.andExpect(status().isOk());

	}


	@Test
	void deveListar() throws Exception {
		Page<Video> page = new PageImpl<>(videos);

		when(obterVideoUseCase.listarTodos(any(Pageable.class))).thenReturn(page);

		mockMvc.perform(MockMvcRequestBuilders.get("/videos")
				.param("pagina", "0")
				.param("tamanho", "10"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(page.getContent().size()))); 
	}

	@Test
	void deveObterPorId() throws Exception {
		String videoId = videoJson.getId(); 
		when(obterVideoUseCase.obterPorId(videoId)).thenReturn(video);

		mockMvc.perform(MockMvcRequestBuilders.get("/videos/{id}", videoId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(video.getId()));

	}

	@Test
	void devePesquisarPorFiltros() throws Exception {
		
		when(obterVideoUseCase.buscaFiltrada(anyString(), any(LocalDate.class), anyString())).thenReturn(videos);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/videos/filtros")
				.param("titulo", "Teste")
				.param("dataPublicacao", LocalDate.now().toString())
				.param("categoria", "ACAO"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(videos.size()))); 
	}

}
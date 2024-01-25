package com.grupo16.tcfase4.gateway.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.grupo16.tcfase4.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("videos")
@RestController
@AllArgsConstructor
public class VideoController {
	
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	private CriarFavoritoUseCase favoritoUseCase;

	private RemoverVideoUseCase removerVideoUseCase;

	private ObterVideoUseCase obterVideoUseCase;

	private ObterUrlVideoUseCase obterUrlVideoUseCase;
	
	private RecomendarVideoUseCase recomendarVideoUseCase;

	private ObterEstatisticaVideoUseCase obterEstatisticaVideoUseCase;


	@GetMapping
	public Page<VideoJson> listar(
			@RequestParam(required = true, value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(required = true, value = "tamanho", defaultValue = "10") Integer tamanho
	) {
		log.trace("Start pagina={}, tamanho={}", pagina, tamanho);
		PageRequest pageRequest = PageRequest.of(pagina, tamanho);

		log.trace("End");
		return obterVideoUseCase.listarTodos(pageRequest).map(VideoJson::new);
	}
	
	@GetMapping("{id}")
	public VideoJson obterPorId(
			@PathVariable(required = true, name = "id") String id) {
		log.trace("Start id={}", id);
		
		Video video = obterVideoUseCase.obterPorId(id);
		VideoJson videoJson = new VideoJson(video);

		log.trace("End videoJson={}", videoJson);
		return videoJson;
	}

	@GetMapping("filtros")
	public List<VideoJson> pesquisar(
		@RequestParam(required = false, name = "titulo") String titulo,
		@RequestParam(required = false, name = "dataPublicacao") LocalDate dataPublicacao,
		@RequestParam(required = false, name = "categoria") String categoria
	) {
		log.trace("Start titulo={}, dataPublicacao={}, categoria={}", titulo, dataPublicacao, categoria);

		List<Video> videos = obterVideoUseCase.buscaFiltrada(titulo, dataPublicacao, categoria);

		log.trace("End");
		return videos.stream().map(VideoJson::new).toList();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String salvar(
			@Valid
			@RequestBody(required = true) VideoJson videoJson){
		log.trace("Start videoJson={}", videoJson);
		Video video = videoJson.mapperJsonToDomain(null);

		String videoId = criarAlterarVideoUseCase.salvar(video);
		
		log.trace("End videoId={}", videoId);
		return videoId;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}")
	public void alterar(
			@PathVariable(required = true, name = "id") String videoId,
			@RequestBody(required = true) VideoJson videoJson) {
		log.trace("Start videoId={}, videoJson={}", videoId, videoJson);
		
		Video video = videoJson.mapperJsonToDomain(videoId);
		
		criarAlterarVideoUseCase.alterar(video);
		log.trace("End");
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("{id}/favoritos/{usuarioId}")
	public String favoritar(
			@PathVariable(required = true, name = "id") String videoId,
			@PathVariable(required = true, name = "usuarioId") String usuarioId) {
		log.trace("Start videoId={}, usuarioId={}", videoId, usuarioId);

		String idFavorito = favoritoUseCase.salvar(videoId, usuarioId);
		
		log.trace("End idFavorito={}", idFavorito);
		return idFavorito;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void remover(
			@PathVariable(required = true, name = "id") String id) {
		log.trace("Start id={}", id);

		removerVideoUseCase.remover(id);
		log.trace("End");
	}
	
	@GetMapping("recomendacoes/{usuarioId}")
	public List<VideoJson> recomendar(
			@PathVariable(required = true, name = "usuarioId") String usuarioId){
		log.trace("Start usuarioId={}", usuarioId);
		
		List<Video> videos = recomendarVideoUseCase.recomendar(usuarioId);
		
		List<VideoJson> videosJson = videos.stream().map(VideoJson::new).toList();
		
		log.trace("End videosJson={}", videosJson);
		return videosJson;
	}
	
	@PostMapping("{id}/upload")
	public void upload(
			@PathVariable(required = true, name = "id") String videoId,
			@RequestParam(name = "file") MultipartFile file) throws IOException {
		log.trace("Start videoId={}, file={}",videoId, file);
		
		byte[] fileBytes = file.getBytes();
		
		criarAlterarVideoUseCase.upload(videoId, fileBytes);
		
		log.trace("End");
	}
	
	@GetMapping("{id}/url")
	public String obterUrl(
			@PathVariable(required=true, name="id") String videoId) {
		log.trace("Start videoId={}",videoId);
		
		String url = obterUrlVideoUseCase.obterUrl(videoId);
		
		log.trace("End url={}",url);
		return url;		
	}

	@GetMapping("estatisticas")
	public String obterEstatisticas() {

		return obterEstatisticaVideoUseCase.obterEstatisticas();
	}
}

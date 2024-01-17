package com.grupo16.tcfase4.gateway.controller;

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

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;
import com.grupo16.tcfase4.service.FavoritoUseCase;
import com.grupo16.tcfase4.service.ObterVideoUseCase;
import com.grupo16.tcfase4.service.RemoverVideoUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("videos")
@RestController
@AllArgsConstructor
public class VideoController {
	
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	private FavoritoUseCase favoritoUseCase;

	private RemoverVideoUseCase removerVideoUseCase;

	private ObterVideoUseCase obterVideoUseCase;


	@GetMapping
	public Page<VideoJson> listar(
			@RequestParam(required = true, value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(required = true, value = "tamanho", defaultValue = "10") Integer tamanho,
			@RequestParam(required = true, value = "dataPublicacao", defaultValue = "true") Boolean dataPublicacao
	) {
		log.trace("Start pagina={}, tamanho={}, dataPublicacao={}", pagina, tamanho, dataPublicacao);
		PageRequest pageRequest = PageRequest.of(pagina, tamanho);

		log.trace("End");
		return obterVideoUseCase.listarTodos(pageRequest, dataPublicacao);
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
	@PostMapping("{id}/{usuarioId}")
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
}

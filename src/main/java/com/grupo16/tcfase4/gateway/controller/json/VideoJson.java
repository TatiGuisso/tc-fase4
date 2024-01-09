package com.grupo16.tcfase4.gateway.controller.json;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class VideoJson {
	
	private String id;
	
	@NotBlank
	private String titulo;
	private String descricao;
	private String url;
	private LocalDate dataPublicacao;
	private Long quantidadeVisualizacao;
	
	@NotBlank
	private String categoria;
	private List<FavoritoJson> favoritos;

}

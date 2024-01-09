package com.grupo16.tcfase4.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Video {
	
	private String id;
	private String titulo;
	private String descricao;
	private String url;
	private LocalDate dataPublicacao;
	private Long quantidadeVisualizacao;
	private List<Favorito> favoritos;
	private Categoria categoria; 

}

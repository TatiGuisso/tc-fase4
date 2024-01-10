package com.grupo16.tcfase4.gateway.controller.reactivemongo.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VideoDocument {
	
	@Id
	private String id;	
	private String titulo;
	private String descricao;
	private String url;
	private LocalDate dataPublicacao;
	private Long quantidadeVisualizacao;

}

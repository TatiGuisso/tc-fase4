package com.grupo16.tcfase4.gateway.mongo.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;

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
	private String categoria;
	
	public VideoDocument(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.dataPublicacao = video.getDataPublicacao();
		this.quantidadeVisualizacao = video.getQuantidadeVisualizacao();
		this.categoria = video.getCategoria().toString();
	}
	
	public Video mapperDocumentToDomain() {
		return Video.builder()
				.id(id)
				.titulo(titulo)
				.descricao(descricao)
				.url(url)
				.dataPublicacao(dataPublicacao)
				.quantidadeVisualizacao(quantidadeVisualizacao)
				.categoria(Categoria.valueOf(categoria))
				.build();
	}

}
package com.grupo16.tcfase4.gateway.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.tcfase4.domain.Favorito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "favorito")
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoDocument {
	
	@Id
	private String id;
	private String videoId;
	private String usuarioId;

	public FavoritoDocument(Favorito favorito) {
		this.id = favorito.getId();
		this.videoId = favorito.getVideo().getId();
		this.usuarioId = favorito.getUsuario().getId();
	}
}

package com.grupo16.tcfase4.gateway.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.domain.Video;

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
	
	public Favorito mapperDocumentToDomain() {
		return Favorito.builder()
				.id(id)
				.video(Video.builder().id(videoId).build())
				.usuario(Usuario.builder().id(usuarioId).build())
				.build();
	}
}

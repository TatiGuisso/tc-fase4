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
	
	//FIXME alterar atributos para id apenas.
	
	@Id
	private String id;
	private VideoDocument video;
	private UsuarioDocument usuario;

	public FavoritoDocument(Favorito favorito) {
		VideoDocument videoDocument = new VideoDocument(favorito.getVideo());
		UsuarioDocument usuarioDocument = new UsuarioDocument(favorito.getUsuario());
		
		this.id = favorito.getId();
		this.video = videoDocument;
		this.usuario = usuarioDocument;
	}
}

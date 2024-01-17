package com.grupo16.tcfase4.gateway.mongo.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.tcfase4.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "usuario")
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDocument {
	
	@Id
	private String id;
	private String nome;
	private List<FavoritoDocument> favoritos;

	public UsuarioDocument(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}
}

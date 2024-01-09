package com.grupo16.tcfase4.gateway.controller.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UsuarioJson {
	
	private String id;
	private String nome;
	private List<FavoritoJson> favoritos;
}

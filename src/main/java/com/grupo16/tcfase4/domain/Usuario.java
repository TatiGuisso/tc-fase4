package com.grupo16.tcfase4.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Usuario {
	
	private String id;
	private String nome;
	private List<Favorito> favoritos;

}

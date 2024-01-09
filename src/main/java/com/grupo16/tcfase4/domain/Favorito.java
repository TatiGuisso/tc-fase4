package com.grupo16.tcfase4.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Favorito {

	private String id;
	private Video video;
	private Usuario usuario;
}

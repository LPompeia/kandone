package br.com.kandone.controller.dto;

import br.com.kandone.model.Board;

public class BoardDTO {
	
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BoardDTO(Board boardFormat) {
		this.id = boardFormat.getId();
		this.name = boardFormat.getName();
	}
}

package br.com.kandone.controller.form;

public class BoardFormCardDTO {
	
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
	
	public BoardFormCardDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BoardFormCardDTO() {
		
	}
}

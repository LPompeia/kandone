package br.com.kandone.controller.form;

import br.com.kandone.model.Card;
import br.com.kandone.model.statusCard;

public class CardFormHome {
	
	private Long id;
	private String description;
	private statusCard status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public statusCard getStatus() {
		return status;
	}
	public void setStatus(statusCard status) {
		this.status = status;
	}
	
	public CardFormHome(Long id, String description, statusCard status) {
		this.id = id;
		this.description = description;
		this.status = status;
	}
	
	public CardFormHome(Card cardFormatter) {
		this.id = cardFormatter.getId();
		this.description = cardFormatter.getDescription();
		this.status = cardFormatter.getStatus();
	}
	
}

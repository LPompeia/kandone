package br.com.kandone.controller.dto;

import br.com.kandone.controller.form.BoardFormCardDTO;
import br.com.kandone.model.Card;
import br.com.kandone.model.statusCard;

public class CardDTO {
	
	private Long id;
	private String description;
	private statusCard status;
	private BoardFormCardDTO board;
	
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
	
	public statusCard getStatusCard() {
		return status;
	}
	
	public void setStatusCard(statusCard statusCard) {
		this.status = statusCard;
	}
	
	public BoardFormCardDTO getBoard() {
		return board;
	}
	
	public void setBoard(BoardFormCardDTO board) {
		this.board = board;
	}
	
	public CardDTO(Card cardFormatter) {
		this.id = cardFormatter.getId();
		this.description = cardFormatter.getDescription();
		this.status = cardFormatter.getStatus();
		this.board = new BoardFormCardDTO(cardFormatter.getBoard().getId(), cardFormatter.getBoard().getName());
	}

	@Override
	public String toString() {
		return "CardDTO [id=" + id + ", description=" + description + ", status=" + status + ", board=" + board + "]";
	}
	
}

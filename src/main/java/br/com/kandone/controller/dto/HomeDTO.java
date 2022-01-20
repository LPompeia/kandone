package br.com.kandone.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.kandone.controller.form.CardFormHome;
import br.com.kandone.model.Board;
import br.com.kandone.model.Card;
import br.com.kandone.model.statusCard;

public class HomeDTO {
	
	private BoardDTO board;
	
	private List<CardFormHome> cards_backlog;
	private List<CardFormHome> cards_todo;
	private List<CardFormHome> cards_inprogress;
	private List<CardFormHome> cards_done;
	

	public List<CardFormHome> getCards_backlog() {
		return cards_backlog;
	}

	public void setCards_backlog(List<CardFormHome> cards_backlog) {
		this.cards_backlog = cards_backlog;
	}

	public List<CardFormHome> getCards_inprogress() {
		return cards_inprogress;
	}

	public void setCards_inprogress(List<CardFormHome> cards_inprogress) {
		this.cards_inprogress = cards_inprogress;
	}

	public List<CardFormHome> getCards_done() {
		return cards_done;
	}

	public void setCards_done(List<CardFormHome> cards_done) {
		this.cards_done = cards_done;
	}

	public List<CardFormHome> getCards_todo() {
		return cards_todo;
	}

	public void setCards_todo(List<CardFormHome> cards_todo) {
		this.cards_todo = cards_todo;
	}

	public BoardDTO getBoard() {
		return board;
	}

	public void setBoard(BoardDTO board) {
		this.board = board;
	}

	public HomeDTO(List<Card> card, Board board) {
		this.board = new BoardDTO(board);
		
		this.cards_backlog = new ArrayList<>();
		this.cards_backlog.addAll(card.stream()
				.filter(c -> c.getStatus() == statusCard.BACKLOG)
				.map(CardFormHome::new).collect(Collectors.toList()));
		
		this.cards_todo = new ArrayList<>();
		this.cards_todo.addAll(card.stream()
				.filter(c -> c.getStatus() == statusCard.TO_DO)
				.map(CardFormHome::new).collect(Collectors.toList()));
		
		this.cards_inprogress = new ArrayList<>();
		this.cards_inprogress.addAll(card.stream()
				.filter(c -> c.getStatus() == statusCard.IN_PROGRESS)
				.map(CardFormHome::new).collect(Collectors.toList()));
		
		this.cards_done = new ArrayList<>();
		this.cards_done.addAll(card.stream()
				.filter(c -> c.getStatus() == statusCard.DONE)
				.map(CardFormHome::new).collect(Collectors.toList()));
	}

}

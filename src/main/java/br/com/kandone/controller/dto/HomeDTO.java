package br.com.kandone.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.kandone.controller.form.CardFormHome;
import br.com.kandone.model.Board;
import br.com.kandone.model.Card;

public class HomeDTO {
	
	private BoardDTO board;
	private List<CardFormHome> cards;
	
	public List<CardFormHome> getCards() {
		return cards;
	}
	
	public void setCards(List<CardFormHome> cards) {
		this.cards = cards;
	}
	

	public BoardDTO getBoard() {
		return board;
	}

	public void setBoard(BoardDTO board) {
		this.board = board;
	}

	public HomeDTO(List<Card> card, Board board) {
		this.board = new BoardDTO(board);
		this.cards = new ArrayList<>();
		this.cards.addAll(card.stream().map(CardFormHome::new).collect(Collectors.toList()));
	}

}

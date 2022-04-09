package br.com.kandone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kandone.config.security.AuthenticationService;
import br.com.kandone.controller.dto.BoardDTO;
import br.com.kandone.controller.dto.HomeDTO;
import br.com.kandone.model.Board;
import br.com.kandone.model.Card;
import br.com.kandone.model.User;
import br.com.kandone.repository.BoardRepository;
import br.com.kandone.repository.CardRepository;
import br.com.kandone.service.exception.ResourceNotFoundException;

@Service
public class BoardService {
	
	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	public Board createBoardDefault(User user) { 
		
		Board boardDefaultCreate = new Board();
		boardDefaultCreate.setUser(user);
		boardDefaultCreate.setName("Welcome " + user.getName());
		
		return boardRepository.save(boardDefaultCreate);
	}
		
	public Board findById(Long id) { 
		Optional<Board> boardSearch = this.boardRepository.findById(id);
		
		if(boardSearch.isEmpty()) { 
			throw new ResourceNotFoundException();
		}
		return boardSearch.get();
	}
	
	public BoardDTO edit(Board boardEdit) { 
		Board boardSearch = this.findById(boardEdit.getId());
		
		if(boardSearch.getId() == null) { 
			throw new ResourceNotFoundException();
		}
		
		Board boardSaved = this.boardRepository.save(boardEdit);
		
		BoardDTO boardResponse = new BoardDTO(boardSaved);
		return boardResponse;
		
	}
	
	public HomeDTO findBoardWithAllCardsByEmail() { 
		
		String emailAuthenticated = this.authenticationService.recoveryEmailAuthentication();
		
		Optional<Board> boardSearchByEmail = this.boardRepository.findByUserEmail(emailAuthenticated);
		
		if(boardSearchByEmail.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		Board AllCardsOnBoard = boardSearchByEmail.get();
		
		List<Card> AllCardsOfUser = this.cardRepository.findAllByBoardId(AllCardsOnBoard.getId());
		
		HomeDTO homeResponse = new HomeDTO(AllCardsOfUser, boardSearchByEmail.get());
		return homeResponse;
	}
}

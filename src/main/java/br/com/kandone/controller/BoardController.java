package br.com.kandone.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.kandone.controller.dto.BoardDTO;
import br.com.kandone.controller.dto.HomeDTO;
import br.com.kandone.model.Board;
import br.com.kandone.service.BoardService;
import br.com.kandone.service.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("boards")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@PutMapping
	public ResponseEntity<BoardDTO> editBoard(@RequestBody Board board) { 
		try {
			BoardDTO boardEdited = boardService.edit(board);
			return new ResponseEntity<BoardDTO>(boardEdited, HttpStatus.OK);
		} catch(ResourceNotFoundException exception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found");
		}
	}
	
	@GetMapping
	public ResponseEntity<HomeDTO> findHomeByEmail(@RequestParam(value = "email") String email) {
		try {
			HomeDTO home = this.boardService.findBoardWithAllCardsByEmail(email);
			return new ResponseEntity<HomeDTO>(home, HttpStatus.OK);
		} catch(ResourceNotFoundException exception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found on card");
		}
	}
	
}

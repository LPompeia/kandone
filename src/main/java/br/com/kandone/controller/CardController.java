package br.com.kandone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.kandone.controller.dto.CardDTO;
import br.com.kandone.controller.form.CardStatusForm;
import br.com.kandone.model.Card;
import br.com.kandone.service.CardService;
import br.com.kandone.service.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cards")
public class CardController {
	
	@Autowired
	CardService cardService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CardDTO> searchById(@PathVariable Long id) {
		try {
			CardDTO cardSearchById = cardService.findById(id);
			return new ResponseEntity<CardDTO>(cardSearchById, HttpStatus.OK);
		} catch(ResourceNotFoundException exception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
		}
	}
	
	@PostMapping
	public ResponseEntity<CardDTO> save(@RequestBody Card cardReceived) { 
		CardDTO cardSaved = cardService.save(cardReceived);
		return new ResponseEntity<CardDTO>(cardSaved, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CardDTO> edit(@RequestBody Card cardReceived) { 
		try {
			CardDTO cardEdited = cardService.edit(cardReceived);
			return new ResponseEntity<CardDTO>(cardEdited, HttpStatus.OK);
		} catch (ResourceNotFoundException exception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
		}
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<CardDTO> statusCardDraw(@PathVariable Long id, @RequestBody CardStatusForm cardStatusForm) { 
		try { 
			CardDTO cardReceveid = cardService.updateStatusCard(id, cardStatusForm);
			return new ResponseEntity<CardDTO>(cardReceveid, HttpStatus.OK);
		} catch (ResourceNotFoundException exception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<CardDTO> delete(@PathVariable Long id) {
		try {
			CardDTO cardDeleted = cardService.delete(id);
			return new ResponseEntity<CardDTO>(cardDeleted, HttpStatus.OK);
		} catch (ResourceNotFoundException exeception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
		}
	}

}

package br.com.kandone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kandone.repository.CardRepository;
import br.com.kandone.service.exception.ResourceNotFoundException;
import br.com.kandone.controller.dto.CardDTO;
import br.com.kandone.controller.form.CardStatusForm;
import br.com.kandone.model.Card;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepository;
	
	public List<Card> findAllByBoardId(Long id) { 
		return this.cardRepository.findAllByBoardId(id);
	}
	
	public CardDTO findById(Long id) {
		
		Optional<Card> cardSearch = this.cardRepository.findById(id);
		
		if(cardSearch.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		CardDTO cardResponse = new CardDTO(cardSearch.get());
		
		return cardResponse;
	}
	
	public CardDTO save(Card cardSave) { 
		Card cardSaved = this.cardRepository.save(cardSave);
		CardDTO cardResponse = new CardDTO(cardSaved);
		return cardResponse;
	}
	
	public CardDTO edit(Card cardEdit) {
		
		CardDTO cardSearch = this.findById(cardEdit.getId());
		if(cardSearch.getId() == null) {
			throw new ResourceNotFoundException();
		}
		Card cardEdited = this.cardRepository.save(cardEdit);
		
		CardDTO cardResponse = new CardDTO(cardEdited);
		return cardResponse;
	}
		
	 public CardDTO delete(Long id) {
		Optional<Card> cardSearch = this.cardRepository.findById(id);
		
		if(cardSearch.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		this.cardRepository.delete(cardSearch.get());
		
		CardDTO cardResponse = new CardDTO(cardSearch.get());
		return cardResponse;
	 }

	public CardDTO updateStatusCard(Long id, CardStatusForm cardStatusForm) {
		
		Optional<Card> cardUpdate = this.cardRepository.findById(id); 
		
		if(cardUpdate.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		Card cardPatch = cardUpdate.get();
		cardPatch.setStatus(cardStatusForm.getStatus());
		
		this.cardRepository.save(cardPatch);
		
		CardDTO cardResponse = new CardDTO(cardPatch);
		return cardResponse;
	}
}

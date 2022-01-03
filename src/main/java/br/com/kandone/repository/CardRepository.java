package br.com.kandone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kandone.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findAllByBoardId(Long id);
	
}

package br.com.kandone.repository;
import br.com.kandone.model.Board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	Optional<Board> findByUserEmail(String email);
	
}

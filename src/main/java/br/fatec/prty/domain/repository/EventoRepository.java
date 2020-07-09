package br.fatec.prty.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.prty.domain.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	List<Evento> findByUsuarioId(Long id);
}

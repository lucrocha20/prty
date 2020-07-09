package br.fatec.prty.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.prty.domain.model.Evento;
import br.fatec.prty.domain.repository.EventoRepository;

@Service
public class EventoService implements ServiceInterface<Evento> {

	@Autowired
	private EventoRepository eventoRepo;
	
	public EventoService() {}
	
	@Override
	public Evento create(Evento obj) {
		eventoRepo.save(obj);
		return obj;
	}

	@Override
	public Evento findById(Long id) {
		Optional<Evento> _evento = eventoRepo.findById(id);
		return _evento.orElse(null);
	}

	@Override
	public List<Evento> findAll() {
		return eventoRepo.findAll();
	}

	@Override
	public boolean update(Evento obj) {
		if (eventoRepo.existsById(obj.getId())) {
			eventoRepo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Evento> _evento = eventoRepo.findById(id);
		if (_evento.isPresent()) {
			eventoRepo.delete(_evento.get());
			return true;
		}
		return false;
	}

}

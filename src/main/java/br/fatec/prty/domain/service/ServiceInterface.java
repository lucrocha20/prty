package br.fatec.prty.domain.service;

import java.util.List;

public interface ServiceInterface<T> {
	T create(T obj);
	T findById(long id);
	List<T> findAll();
	boolean update(T obj);
	boolean delete(Long id);
}

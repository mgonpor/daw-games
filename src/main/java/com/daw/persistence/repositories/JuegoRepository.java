package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Juego;
import com.daw.persistence.entities.enums.Tipo;

public interface JuegoRepository extends ListCrudRepository<Juego, Long> {

	List<Juego> findByTipo(Tipo tipo);
	
	List<Juego> findByGeneroContaining(String genero);
	
	List<Juego> findByNombreContaining(String nombre);
	
	List<Juego> findByPlataformasContaining(String plataforma);
	
	List<Juego> findByPrecioBetween(double min, double max);
	
	List<Juego> findByDescargasGreaterThanEqual(long num);
}

package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Juego;
import com.daw.persistence.entities.enums.Tipo;
import com.daw.persistence.repositories.JuegoRepository;
import com.daw.services.exceptions.JuegoException;
import com.daw.services.exceptions.JuegoNotFoundException;

@Service
public class JuegoService {

	@Autowired
	private JuegoRepository juegoRepository;
	
	public List<Juego> findAll(){
		return juegoRepository.findAll();
	}
	
	public Juego findById (long idJuego) {
		if(!this.juegoRepository.existsById(idJuego)) {
			throw new JuegoNotFoundException("El juego con id " + idJuego + " no existe");
		}
		return this.juegoRepository.findById(idJuego).get();
	}
	
	public Juego create (Juego juego) {
		if(juego.getFechaLanzamiento() == null) {
			juego.setFechaLanzamiento(LocalDate.now());
		}
		return this.juegoRepository.save(juego);
	}
	
	public Juego update(long idJuego, Juego juego) {
		if(idJuego != juego.getId()) {
			throw new JuegoException("El id del Path ("+ idJuego +") es distinto del id del body ("+juego.getId()+")");
		}
		if(!this.juegoRepository.existsById(idJuego)) {
			throw new JuegoNotFoundException("No se ha encontrado el juego con id "+ idJuego);
		}
		
		Juego juegoDB = this.findById(idJuego);
		if(juego.isCompletado() != juegoDB.isCompletado()) {
			throw new JuegoException("No se puede modificar el atributo 'completado' desde este endpoint");
		}
		return this.juegoRepository.save(juego);
	}
	
	public void deleteById(long idJuego) {
		if(!this.juegoRepository.existsById(idJuego)) {
			throw new JuegoException("El juego con id " + idJuego + " no existe");
		}
		this.juegoRepository.deleteById(idJuego);
	}
	
	public Juego cambiarCompletado(long idJuego) {
		if(!this.juegoRepository.existsById(idJuego)) {
			throw new JuegoException("El juego con id " + idJuego + " no existe");
		}
		Juego juegoDB = this.findById(idJuego);
		juegoDB.setCompletado(!juegoDB.isCompletado());
		return this.juegoRepository.save(juegoDB);
	}
	
	public List<Juego> findByGenero(String genero){
		return this.juegoRepository.findByGeneroContaining(genero);
	}
	
	public List<Juego> findByNombre(String nombre){
		return this.juegoRepository.findByNombreContaining(nombre);
	}
	public List<Juego> findByPlataforma(String plataforma){
		return this.juegoRepository.findByPlataformasContaining(plataforma);
	}
	
	public List<Juego> findJuegosBase(){
		return this.juegoRepository.findByTipo(Tipo.BASE);
	}
	public List<Juego> findJuegosExpansion(){
		return this.juegoRepository.findByTipo(Tipo.EXPANSION);
	}
	public List<Juego> findJuegosDLC(){
		return this.juegoRepository.findByTipo(Tipo.DLC);
	}
	
	public List<Juego> findByRangoPrecio(double min, double max){
		return this.juegoRepository.findByPrecioBetween(min, max);
	}
	
	public List<Juego> findByDescargasMayoresQue(long num){
		return this.juegoRepository.findByDescargasGreaterThanEqual(num);
	}
	
}

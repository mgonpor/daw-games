package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Juego;
import com.daw.services.JuegoService;
import com.daw.services.exceptions.JuegoException;
import com.daw.services.exceptions.JuegoNotFoundException;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

	@Autowired
	private JuegoService juegoService;
	
	@GetMapping
	public ResponseEntity<List<Juego>> list(){
		return ResponseEntity.ok(this.juegoService.findAll());
	}
	
	@GetMapping("/{idJuego}")
	public ResponseEntity<?> findById(@PathVariable long idJuego){
		try {
			return ResponseEntity.ok(this.juegoService.findById(idJuego));
		}catch(JuegoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Juego> create(@RequestBody Juego juego) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.juegoService.create(juego));	
	}

	@PutMapping("/{idJuego}")
	public ResponseEntity<?> update(@PathVariable long idJuego,@RequestBody Juego juego){
		try {
			return ResponseEntity.ok(this.juegoService.update(idJuego, juego));
		}catch(JuegoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(JuegoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{idJuego}")
	public ResponseEntity<?> deleteById(@PathVariable long idJuego){
		try {
			this.juegoService.deleteById(idJuego);
			return ResponseEntity.ok("Juego eliminado con éxito");
		}catch(JuegoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(JuegoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idJuego}/completado")
	public ResponseEntity<?> cambiarCompletado(@PathVariable long idJuego){
		try {
			return ResponseEntity.ok(this.juegoService.cambiarCompletado(idJuego));
		}catch(JuegoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@GetMapping("/buscar/genero")
	public ResponseEntity<List<Juego>> findByGenero(@RequestParam String genero){
		return ResponseEntity.ok(this.juegoService.findByGenero(genero));
	}
	@GetMapping("/buscar/nombre")
	public ResponseEntity<List<Juego>> findByNombre(@RequestParam String nombre){
		return ResponseEntity.ok(this.juegoService.findByNombre(nombre));
	}
	@GetMapping("/buscar/plataformas")
	public ResponseEntity<List<Juego>> findByPlataforma(@RequestParam String plataforma){
		return ResponseEntity.ok(this.juegoService.findByPlataforma(plataforma));
	}
	
	@GetMapping("/base")
	public ResponseEntity<List<Juego>> findJuegosBase(){
		return ResponseEntity.ok(this.juegoService.findJuegosBase());
	}
	@GetMapping("/expansion")
	public ResponseEntity<List<Juego>> findJuegosExpansion(){
		return ResponseEntity.ok(this.juegoService.findJuegosExpansion());
	}
	@GetMapping("/dlc")
	public ResponseEntity<List<Juego>> findJuegosDLC(){
		return ResponseEntity.ok(this.juegoService.findJuegosDLC());
	}
	
	@GetMapping("/rango")
	public ResponseEntity<List<Juego>> findByRango(@RequestParam double min, @RequestParam double max){
		return ResponseEntity.ok(this.juegoService.findByRangoPrecio(min, max));
	}
	
	@GetMapping("/descargas/{num}")
	public ResponseEntity<List<Juego>> findByDescargas(@PathVariable long num){
		return ResponseEntity.ok(this.juegoService.findByDescargasMayoresQue(num));
	}
	
}

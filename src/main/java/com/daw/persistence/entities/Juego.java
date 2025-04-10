package com.daw.persistence.entities;

import java.time.LocalDate;

import com.daw.persistence.entities.enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "juego")
public class Juego {

	@Id						
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String genero;
	private String plataforma;
	
	@Column(columnDefinition = "DECIMAL(5,2)")
	private double precio;
	private long descargas;
	
	@Column(name = "fecha_lanzamiento")
	private LocalDate fechaLanzamiento;
	
	@Enumerated
	private Tipo tipo;
	
	@Column(columnDefinition = "TINYINT")
	private boolean completado;
	
	public Juego() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public long getDescargas() {
		return descargas;
	}
	public void setDescargas(long descargas) {
		this.descargas = descargas;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public boolean isCompletado() {
		return completado;
	}
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	
	
	
}

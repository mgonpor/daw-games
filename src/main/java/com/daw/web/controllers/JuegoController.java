package com.daw.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.services.JuegoService;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

	@Autowired
	private JuegoService juegoService;
	
}

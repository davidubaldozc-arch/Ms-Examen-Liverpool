package com.liverpool.exame.order_managment.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.exame.order_managment.application.UsuarioUseCase;
import com.liverpool.exame.order_managment.domain.model.Usuario;

/*
 * Entrada (REST Controller)
 * Recibe la peticion HTTP y la pasa al caso de uso
 * (capa de aplicacion)
 */

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	
	private final UsuarioUseCase usuarioUseCase;
	
	//Inyeccion de dependencias
	public UsuarioController (UsuarioUseCase usuarioUseCase) {
		this.usuarioUseCase = usuarioUseCase;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioUseCase.crearUsuaior(usuario);
		return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obtnerTodos(){
		return ResponseEntity.ok(usuarioUseCase.obtenerTodos());
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario> obtenerPorId(@PathVariable String id){
		return ResponseEntity.ok(usuarioUseCase.obtenerPorId(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Usuario> actualizar(@PathVariable String id, @RequestBody Usuario usuario){
		return ResponseEntity.ok(usuarioUseCase.actualizarUsuario(id, usuario));
	}

}

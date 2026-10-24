package com.liverpool.exame.order_managment.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.liverpool.exame.order_managment.domain.model.Usuario;
import com.liverpool.exame.order_managment.infrastructure.client.MockApiClient;
import com.liverpool.exame.order_managment.infrastructure.client.dto.PedidoDTO;
import com.liverpool.exame.order_managment.infrastructure.repository.UsuarioRepository;

/*
 * Caso se Uso para la gestion de Usuario
 * Representa la capa de Aplicacion en la Arquitectura Limpia
 */

@Service
public class UsuarioUseCase {
	
	private final UsuarioRepository usuarioRepository;
	private final MockApiClient mockApiClient;
	
	//Inyeccion de dependencias por constructor
	public UsuarioUseCase(UsuarioRepository usuarioRepository, MockApiClient  mockApiClient) {
		
		this.usuarioRepository = usuarioRepository;
		this.mockApiClient = mockApiClient;
	}
	
	/*
	 * Crear usuario
	 * Busca sus pedidos y los guara en MongoDB
	 * En el video tuve en error aqui ya que no tenia instalado Lombok 
	 * Pero lo solucione 
	 */
	
	public Usuario crearUsuaior(Usuario usuario) {
		
		//1- Obtenemos la lista completa de pedidos desde el MockApi
		List<PedidoDTO> todosLosPedidos = mockApiClient.obtenerPedidos();
		
		//2- Filtrajos usando java stream para encontrar los que coicidan con el userID
		List<String> userOrders = todosLosPedidos.stream()
				.filter(pedido -> pedido.getUserId().equals(usuario.getUserId()))
				.map(PedidoDTO::getOrderRef)
				.collect(Collectors.toList());
		
		//3- Asignamos la lista de orderRegs al usuario
		usuario.setOrders(userOrders);
		
		//4- Guardamos en la base de datos
		return usuarioRepository.save(usuario);
	}
	
	
	/*
	 * Consultar todos los usuarios
	 */
	
	public List<Usuario> obtenerTodos(){
		return usuarioRepository.findAll();
	}
	
	
	/*
	 * Consultar un susuario por su ID 
	 */
	
	public Usuario obtenerPorId(String id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuaior no encontrado"));
	}
	
	/*
	 * Actuliza un usuario existente
	 */
	
	public Usuario actualizarUsuario(String id, Usuario usuarioActualizado) {
		Usuario usuarioExistente = obtenerPorId(id);
		
		usuarioExistente.setNombre(usuarioActualizado.getNombre());
		usuarioExistente.setApellidoPaterno(usuarioActualizado.getApellidoPaterno());
		usuarioExistente.setApellidoMaterno(usuarioActualizado.getApellidoMaterno());
		usuarioExistente.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
		//El userId no se modifica porque es la llave de negocio
		
		return usuarioRepository.save(usuarioExistente);
		



	}

}

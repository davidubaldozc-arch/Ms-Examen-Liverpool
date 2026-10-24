package com.liverpool.exame.order_managment.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.exame.order_managment.application.BusquedaUseCase;
import com.liverpool.exame.order_managment.infrastructure.client.dto.PedidoDetalleDTO;

@RestController
@RequestMapping("/api/v1/pedidos")
public class BusquedaController {
	
	private final BusquedaUseCase busquedaUseCase;
	
	//Inyeccion de dependencias
	public BusquedaController(BusquedaUseCase busquedaUseCase) {
		this.busquedaUseCase = busquedaUseCase;
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<PedidoDetalleDTO>> buscarPedidos(@RequestParam String keyword){
		List<PedidoDetalleDTO> resultados = busquedaUseCase.buscarPedidos(keyword);
		return ResponseEntity.ok(resultados);
	}

}

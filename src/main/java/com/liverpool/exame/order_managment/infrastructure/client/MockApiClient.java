package com.liverpool.exame.order_managment.infrastructure.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.liverpool.exame.order_managment.infrastructure.client.dto.ItemDTO;
import com.liverpool.exame.order_managment.infrastructure.client.dto.PedidoDTO;

//Usamos la URL base que proporcionaron para los mock

@FeignClient(name = "mockApiClient", url = "https://6994a4eab081bc23e9c0f61e.mockapi.io/api/v1")
public interface MockApiClient {
	
	@GetMapping("/pedidos")
	List<PedidoDTO> obtenerPedidos();
	
	@GetMapping("/items")
	List<ItemDTO> obtenerItems();

}

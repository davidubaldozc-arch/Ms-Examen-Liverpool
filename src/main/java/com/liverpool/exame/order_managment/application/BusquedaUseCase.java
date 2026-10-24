package com.liverpool.exame.order_managment.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.liverpool.exame.order_managment.infrastructure.client.MockApiClient;
import com.liverpool.exame.order_managment.infrastructure.client.dto.ItemDTO;
import com.liverpool.exame.order_managment.infrastructure.client.dto.PedidoDTO;
import com.liverpool.exame.order_managment.infrastructure.client.dto.PedidoDetalleDTO;
import com.liverpool.exame.order_managment.application.util.FuzzySearchUtil;

/*
 * Caso de uso de la busqueda flexible (Type ahead) de pedidos
 */
@Service
public class BusquedaUseCase {
	
	private final MockApiClient mockApiClient;
	private static final int TOLERANCIA_ERRORES = 2; //Podra permitir 2 errores
	
	
	//Inyeccion de dependencias
	public BusquedaUseCase(MockApiClient mockApiClient) {
		this.mockApiClient = mockApiClient;
	}
	
	public List<PedidoDetalleDTO> buscarPedidos(String keyword){
		//1- Consumimos los datos externos
		List<PedidoDTO> todosLosPedidos = mockApiClient.obtenerPedidos();
		List<ItemDTO> todosLosItems = mockApiClient.obtenerItems();
		
		//2- Filtramos la info y damos una respuesta
		return todosLosPedidos.stream()
				.filter(pedido -> coincidePedidoOItem(pedido, todosLosItems, keyword))
				.map(pedido -> contruirPedidoDTO(pedido, todosLosItems))
				.collect(Collectors.toList());
	}
	
	/*
	 * Valisa si la palabra clave coincide con los campos del pedido o con el nombre del item
	 */
	private boolean coincidePedidoOItem(PedidoDTO pedido, List<ItemDTO> todosLosItems, String keyword) {
		//Busca la concidnecia en los atritubos que pidieron
		if(FuzzySearchUtil.isFlexibleMatch(pedido.getOrderRef(), keyword, TOLERANCIA_ERRORES)||
			FuzzySearchUtil.isFlexibleMatch(pedido.getOrderStatus(), keyword, TOLERANCIA_ERRORES)||
			FuzzySearchUtil.isFlexibleMatch(pedido.getStoreName(), keyword, TOLERANCIA_ERRORES)) {
			
			return true;
		}
		
		List<ItemDTO> itemsDelPedido = obtenerItemsDelPedido(pedido, todosLosItems);
		return itemsDelPedido.stream()
				.anyMatch(item -> FuzzySearchUtil.isFlexibleMatch(item.getDisplayName(), keyword,
						TOLERANCIA_ERRORES));
	}
	
	/*
	 * Respuesta cambiando los datos del pedido
	 * y la lsita real del objeto ItemDTO
	 */
	private PedidoDetalleDTO contruirPedidoDTO(PedidoDTO pedido, List<ItemDTO> todosLosItems) {
		PedidoDetalleDTO pedidoDetalleDTO = new PedidoDetalleDTO();
		pedidoDetalleDTO.setOrderRef(pedido.getOrderRef());
		pedidoDetalleDTO.setOrderStatus(pedido.getOrderStatus());
		pedidoDetalleDTO.setStoreName(pedido.getStoreName());
		pedidoDetalleDTO.setCanal(pedido.getCanal());
		pedidoDetalleDTO.setProductosAsociados(obtenerItemsDelPedido(pedido, todosLosItems));
		return pedidoDetalleDTO;
	}
	
	/*
	 * Extrae los imtes completos 
	 * cruzando el array de IDs del pedido con la lista de Items
	 */
	private List<ItemDTO> obtenerItemsDelPedido(PedidoDTO pedido, List<ItemDTO> todosLosItems){
		return todosLosItems.stream()
				.filter(item -> pedido.getItems().contains(item.getItemId()))
				.collect(Collectors.toList());
	}
}
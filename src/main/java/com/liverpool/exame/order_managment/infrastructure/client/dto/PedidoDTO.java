package com.liverpool.exame.order_managment.infrastructure.client.dto;

import java.util.List;

import lombok.Data;


/*
 * Necesitamos mapear o traducir los JSON que nos regresen las URLs 
 * 
 */
@Data
public class PedidoDTO {

	private String orderRef;
	private String userId;
	private String canal;
	private String orderStatus;
	private String storeName;
	private List<String> items;
}

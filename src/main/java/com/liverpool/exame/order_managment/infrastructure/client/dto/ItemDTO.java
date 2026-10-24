package com.liverpool.exame.order_managment.infrastructure.client.dto;

import lombok.Data;

@Data
public class ItemDTO {
	
	private String itemId;
	private String skuId;
	private int quantity;
	private String displayName;
	private String deliveryStatus;

}

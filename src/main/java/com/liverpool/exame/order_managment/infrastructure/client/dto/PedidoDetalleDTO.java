package com.liverpool.exame.order_managment.infrastructure.client.dto;

import lombok.Data;
import java.util.List;

/*
 * PedidoDetalleDTO es la respuesta final donde reemplazo esos IDs sueltos
 * por los objetos completos (ItemDTO) para que el cliente final vea toda la información cruzada.
 */
@Data
public class PedidoDetalleDTO {
    private String orderRef;
    private String orderStatus;
    private String storeName;
    private String canal;
    
    //Diferencia principal
    private List<ItemDTO> productosAsociados;
}
package com.liverpool.exame.order_managment.domain.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/*
 * Entidad de domino que representa la coleccion de usuarios en MongoDB
 * Usare Lombok para mantener el codigo limpio 
 */

@Data
@Builder
@Document(collection = "usuarios")
public class Usuario {
	
	@Id
	private String id;
	private String userId;//Para vincular la respuesta externa de /pedidos
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correoElectronico;
	
	private List<String> orders; //Inyectamos los orderRef

}

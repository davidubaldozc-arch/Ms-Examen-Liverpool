package com.liverpool.exame.order_managment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

/*
 * Implementare CommandLineRunner de forma temporal para verificar la conexion a la BD 
 * antes de construir el repo y seguir desarollando
 */

@SpringBootApplication
@EnableFeignClients //Agregamos esta anotacion para decirle a Sprign que busque el cliente HTTP
public class OrderManagmentApplication implements CommandLineRunner{
	
	//Inyecto MongoTemplate para interactuar con la base de datos 
	//En el video lo hice con autowired pero para mejores practicas lo hice con constructor
	
	private final MongoTemplate mongoTemplate;

	OrderManagmentApplication(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OrderManagmentApplication.class, args);
	}
	
	/*
	 * El metodo run se ejecuta automaticamente despues de que spring levanet el cotexto
	 */
	
	@Override
	public void run(String...args) throws Exception{
		
		System.out.println("Comprobando conexion a MongoDB alojado en Docker");
		
		long count = mongoTemplate.count(new Query(), "usuarios");
		
		System.out.println("Conexion exitosa! La coleccion 'usuarios' tiene: ");
		
	}
	
		
}

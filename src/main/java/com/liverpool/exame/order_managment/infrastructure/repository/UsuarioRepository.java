package com.liverpool.exame.order_managment.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.exame.order_managment.domain.model.Usuario;

/*
 * Este es el adaptador de salida para la persistencia en MongoDB
 * 
 */

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}

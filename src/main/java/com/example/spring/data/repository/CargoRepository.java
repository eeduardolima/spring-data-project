package com.example.spring.data.repository;

import com.example.spring.data.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// quando uma classe ou interface implementa outra, ela pode usar os métodos da classe extendida
// por exemplo foi extendido a classe crudrepository onde tem os principais métodos do JPA (salvar, buscar)
@Repository
public interface CargoRepository extends CrudRepository<Cargo,Integer> {

}

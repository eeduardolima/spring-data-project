package com.example.spring.data.repository;

import com.example.spring.data.orm.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    // Derived Querys
    List<Funcionario> findByNomeContains(String nome);

    // JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario > :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioDataContratacao(String nome, Double salario, LocalDate data);

    // Native Query
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao > :data", nativeQuery = true)
    List<Funcionario> findSalarioMaior(LocalDate data);

    @Query("SELECT f FROM Funcionario f ")
    List<Funcionario> findSalarioFuncionario();
}

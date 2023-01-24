package com.example.spring.data.service;

import com.example.spring.data.orm.Cargo;
import com.example.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    private Boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void iniciar(Scanner scanner){
        while(system){
            System.out.println("Qual função de cargo você deseja executar?");
            System.out.println("0 - Voltar ao menu inicial");
            System.out.println("1 - Adicionar cargo");
            System.out.println("2 - Atualizar cargo");
            System.out.println("3 - Visualizar cargo");
            System.out.println("4 - Excluir cargo");

            int action = scanner.nextInt();
            switch (action) {
                case 1 -> salvar(scanner);
                case 2 -> atualizar(scanner);
                case 3 -> visualizar();
                case 4 -> deletar(scanner);
                default -> system = false;
            }
        }
    }

    private void salvar(Scanner scanner){
        System.out.println("Inserir descrição do cargo:");
        scanner.nextLine();
        String descricao = scanner.nextLine().toUpperCase();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo " + descricao + " adicionado com sucesso.");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Informe o ID do cargo que você deseja atualizar:");
        int id = scanner.nextInt();
        System.out.println("Informe o novo nome do cargo: ");
        String descricaoCargoAtualizado = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricaoCargoAtualizado);
        cargoRepository.save(cargo);
        System.out.println("Cargo atualizado com sucesso.");
    }

    private void visualizar(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner){
        System.out.println("Informe o ID do cargo que você deseja excluir:");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Cargo excluído com sucesso.");
    }
}

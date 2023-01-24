package com.example.spring.data.service;

import com.example.spring.data.orm.UnidadeTrabalho;
import com.example.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    private Boolean system = true;

    public void iniciar(Scanner scanner){
        while(system){
            System.out.println("Qual função de unidade de trabalho você deseja executar?");
            System.out.println(" 0 - Voltar ao menu inicial");
            System.out.println(" 1 - Adicionar unidade de trabalho");
            System.out.println(" 2 - Editar unidade de trabalho");
            System.out.println(" 3 - Excluir unidade de trabalho");
            System.out.println(" 4 - Visualizar unidades de trabalho");

            int action = scanner.nextInt();
            switch (action) {
                case 1 -> adicionar(scanner);
                case 2 -> editar(scanner);
                case 3 -> deletar(scanner);
                case 4 -> visualizar();
                default -> system = false;
            }
        }
    }

    private void adicionar(Scanner scanner){
        System.out.println("Digite a descricao da unidade de trabalho:");
        scanner.nextLine();
        String descricao = scanner.nextLine();
        System.out.println("Digite o endereço:");
        String endereco = scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de trabalho adicionado com sucesso.");
    }

    private void editar(Scanner scanner){
        System.out.println("Digite o id da unidade de trabalho que você deseja editar");
        int id = scanner.nextInt();
        System.out.println("Digite a descricao da unidade de trabalho:");
        scanner.nextLine();
        String descricao = scanner.nextLine();
        System.out.println("Digite o endereço:");
        String endereco = scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de trabalho editada com sucesso.");
    }

    private void visualizar(){
        Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o ID que você deseja excluir");
        int id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Unidade de trabalho excluído com sucesso.");
    }
}
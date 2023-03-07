package com.example.spring.data.service;

import com.example.spring.data.orm.Funcionario;
import com.example.spring.data.repository.FuncionarioRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void iniciar(Scanner scanner) {
        while(system){
            System.out.println("Qual função de relatórios você deseja acessar?");
            System.out.println("0 - Voltar ao menu inicial");
            System.out.println("1 - Buscar pelo nome do funcionário");
            System.out.println("2 - Buscar pelo nome, data de contratacao e salario maior");
            System.out.println("3 - Buscar pelos funcionários contratados após determinada data");

            int action = scanner.nextInt();

            switch (action) {
                case 1 -> buscarFuncionarioNome(scanner);
                case 2 -> buscarFuncionarioNomeDataContratacaoSalarioMaior(scanner);
                case 3 -> buscarDataContratacaoMaior(scanner);
                default -> system = false;
            }
        }
    }

    private void buscarFuncionarioNomeDataContratacaoSalarioMaior(Scanner scanner) {
        System.out.println("Digite o nome do funcionário que você deseja buscar:");
        scanner.nextLine();
        String nome = scanner.nextLine();

        System.out.println("Digite a data de contratação do funcionário:");
        String data = scanner.nextLine();
        LocalDate dataFormatada = LocalDate.parse(data,formatter);

        System.out.println("Digite o valor do salário:");
        double salario = scanner.nextDouble();

        List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioDataContratacao(nome, salario, dataFormatada);
        funcionarios.forEach(System.out::println);
    }

    private void buscarFuncionarioNome(Scanner scanner) {
        System.out.println("Digite o nome do funcionário que você deseja buscar:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeContains(nome);
        funcionarios.forEach(System.out::println);
    }

    private void buscarDataContratacaoMaior(Scanner scanner){
        System.out.println("Digite a data de contratação, com isso irá retornar funcionários que foram contratados após a data informada:");
        scanner.nextLine();
        String data = scanner.nextLine();
        LocalDate dataContratacao = LocalDate.parse(data,formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findSalarioMaior(dataContratacao);
        funcionarios.forEach(System.out::println);
    }
}
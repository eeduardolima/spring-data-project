package com.example.spring.data.service;

import com.example.spring.data.orm.Funcionario;
import com.example.spring.data.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public void iniciar(Scanner scanner) {
        while(system){
            System.out.println("Qual função de relatórios você deseja acessar?");
            System.out.println("0 - Voltar ao menu inicial");
            System.out.println("1 - Buscar pelo nome do funcionário");

            int action = scanner.nextInt();

            switch (action) {
                case 1 -> buscarFuncionarioNome(scanner);
                default -> system = false;
            }
        }
    }

    private void buscarFuncionarioNome(Scanner scanner) {
        System.out.println("Digite o nome do funcionário que você deseja buscar:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeContains(nome);
        funcionarios.forEach(System.out::println);
    }
}
package com.example.spring.data.service;

import com.example.spring.data.orm.Cargo;
import com.example.spring.data.orm.Funcionario;
import com.example.spring.data.orm.UnidadeTrabalho;
import com.example.spring.data.repository.CargoRepository;
import com.example.spring.data.repository.FuncionarioRepository;
import com.example.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private final CargoRepository cargoRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.cargoRepository = cargoRepository;
    }

    public void iniciar(Scanner scanner) throws ParseException {
        while(system){
            System.out.println("Qual função de funcionário você deseja selecionar");
            System.out.println("0 - Voltar ao menu inicial");
            System.out.println("1 - Adicionar funcionário");
            System.out.println("2 - Editar funcionário");
            System.out.println("3 - Excluir funcionário");
            System.out.println("4 - Visualizar funcionário");
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

    private void adicionar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();
        System.out.println("Informe o nome do funcionário:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        funcionario.setNome(nome);

        System.out.println("Informe o CPF:");
        String cpf = scanner.nextLine();
        funcionario.setCpf(cpf);

        System.out.println("Informe o salário:");
        double salario = scanner.nextDouble();
        funcionario.setSalario(salario);

        System.out.println("Informe a data de contratação:");
        scanner.nextLine();
        String dataContratacao = scanner.nextLine();
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

        System.out.println("Digite o cargoID:");
        int cargoId = scanner.nextInt();
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.orElseThrow(RuntimeException::new));

        List<UnidadeTrabalho> unidades = unidade(scanner);
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario adicionado com sucesso.");
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o id que você deseja excluir:");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Funcionario excluído com sucesso.");
    }

    private void visualizar(){
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(System.out::println);
    }

    private void editar(Scanner scanner) throws ParseException {
        System.out.println("Digite o id que você deseja editar:");
        int id = scanner.nextInt();
        System.out.println("Informe o novo nome do funcionário:");
        String nome = scanner.nextLine();
        System.out.println("Informe o CPF:");
        String cpf = scanner.nextLine();
        System.out.println("Informe o salário:");
        double salario = scanner.nextDouble();
        System.out.println("Informe a data de contratação:");
        String dataContratacao = scanner.nextLine();
        System.out.println("Digite o cargoID:");
        int cargoId = scanner.nextInt();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);
        funcionarioRepository.save(funcionario);

        System.out.println("Funcionario foi atualizado com sucesso.");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner){
        boolean istrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while(istrue){
            System.out.println("Digite a unidadeID: (Para sair digite 0)");
            int unidadeId = scanner.nextInt();

            if (unidadeId != 0){
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else{
                istrue = false;
            }
        }
        return unidades;
    }
}

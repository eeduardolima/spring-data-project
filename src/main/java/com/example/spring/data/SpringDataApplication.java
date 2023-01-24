package com.example.spring.data;

import com.example.spring.data.service.CrudCargoService;
import com.example.spring.data.service.CrudFuncionarioService;
import com.example.spring.data.service.CrudUnidadeTrabalhoService;
import com.example.spring.data.service.RelatoriosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

//	@Autowired -
	private final CrudCargoService crudCargoService;

	private final CrudFuncionarioService crudFuncionarioService;

	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

	private final RelatoriosService relatoriosService;

	private Boolean system = true;

	// construtor da classe que substitui o @Autowired, ele injeta a dependencia da repository
	public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, RelatoriosService relatoriosService){
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	// classe main que roda o projeto
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// OBJETIVO: Inserir a descricao de cargo no banco de dados via SPRING DATA:

		Scanner scanner = new Scanner(System.in);

		while (system){
			System.out.println("Digite uma opção para prosseguir:");
			System.out.println("0 - Sair");
			System.out.println("1 - Funções de cargo");
			System.out.println("2 - Funções de funcionário");
			System.out.println("3 - Funções de unidade de trabalho");
			System.out.println("4 - Relatórios");

			int action = scanner.nextInt();

			switch (action) {
				case 1 -> crudCargoService.iniciar(scanner);
				case 2 -> crudFuncionarioService.iniciar(scanner);
				case 3 -> crudUnidadeTrabalhoService.iniciar(scanner);
				case 4 -> relatoriosService.iniciar(scanner);
				default -> system = false;
			}
		}
	}
}

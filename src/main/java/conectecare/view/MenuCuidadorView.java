package conectecare.view;

import conectecare.controller.CuidadorController;
import conectecare.model.vo.CuidadorVo;

import java.util.List;
import java.util.Scanner;

public class MenuCuidadorView {
    private CuidadorController cuidadorController;
    private Scanner sc;

    public MenuCuidadorView() {
        this.cuidadorController = new CuidadorController();
        this.sc =new Scanner(System.in);
    }

    public void menuCuidador(){
        int opcao;
        do {
            System.out.println("\n=== CADASTRO DE CUIDADOR ===");
            System.out.println("1. Cadastrar cuidador para este paciente");
            System.out.println("2. Atualizar Cuidador ");
            System.out.println("3. Lista de cuidadores ");
            System.out.println("4. Excluir Cuidador");
            System.out.println("5. Sair...");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1 -> criarNovoCuidador();
                case 2 -> atualizaCuidador();
                case 3 -> listaCuidadores();
                case 4 -> deletarCuidador();
                case 5 ->{
                    System.out.println("Saindo....");
                    return;}
                default -> System.out.println("Opção invalida, tente novamente");
            }
        }while (opcao != 4);
    }

    public void criarNovoCuidador(){
        System.out.println("\n--- CADASTRAR CUIDADOR VINCULADO ---");

        System.out.println("CPF do Paciente: ");
        String cpfPaciente = sc.nextLine();

        System.out.println("Nome do Cuidador: ");
        String nome = sc.nextLine();

        System.out.println("CPF do Cuidador: ");
        String cpf = sc.nextLine();

        System.out.println("Idade do Cuidador: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.println("Email do Cuidador: ");
        String email = sc.nextLine();

        System.out.println("Telefone do Cuidador: ");
        String telefoneContato = sc.nextLine();

        System.out.println("Qual é o parentesco/vinculo com o paciente? ");
        String correlacaoPaciente = sc.nextLine();

        cuidadorController.adicionarCuidador(nome, cpf, idade, email, telefoneContato, correlacaoPaciente, cpfPaciente);
    }

    public void atualizaCuidador(){
        List<CuidadorVo> cuidadores = cuidadorController.listarCuidadores();

        if (cuidadores.isEmpty()) {
            System.out.println("Nenhum Cuidador cadastrado.");
            return;
        }

        System.out.print("Digite o número do Cuidador a atualizar: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > cuidadores.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        CuidadorVo cuidadorAntigo = cuidadores.get(escolha - 1);
        String cpfInicial = cuidadorAntigo.getCpf();

        System.out.println(" Ola, vamos atualizar um Cuidador");

        System.out.println(" Insira o nome ");
        String nome =sc.nextLine();

        String cpf = cpfInicial;

        System.out.println(" Insira a idade");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.println(" Insira o email ");
        String email = sc.nextLine();

        System.out.println(" Insira o telefone ");
        String telefoneContato = sc.nextLine();

        System.out.println(" Insira o correlacaoPaciente ");
        String correlacaoPaciente = sc.nextLine();

        cuidadorController.atualizaCuidador(nome, cpf, idade, email, telefoneContato, correlacaoPaciente, cpfInicial);
    }

    private void listaCuidadores() {
        List<CuidadorVo> listaDeCuidadores = cuidadorController.listarCuidadores();

        if (listaDeCuidadores.isEmpty()) {
            System.out.println("\nNenhum Cuidador encontrado no sistema.");
        } else {
            System.out.println("\n=== Lista de Cuidador Cadastrados ===");
        }
    }

    public void deletarCuidador() {
        System.out.println("CPf do cliente a ser deletado: ");
        String cpf = sc.nextLine();
        cuidadorController.excluirCuidador(cpf);
    }
}

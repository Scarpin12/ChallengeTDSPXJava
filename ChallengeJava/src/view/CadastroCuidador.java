package view;
import controler.CuidadorController;
import controler.PacienteController;
import model.vo.Cuidador;
import java.util.List;
import java.util.Scanner;

public class CadastroCuidador {
    private CuidadorController cuidadorController;
    private Scanner sc;

    public CadastroCuidador() {
        this.cuidadorController = new CuidadorController();
        this.sc =new Scanner(System.in);
    }

    public void menuCuidador(){
        int opcao;
        do {
            System.out.println("| Seje bem vindo!! escolha a uma das opções |");
            System.out.println("1. Cadastro Cuidador ");
            System.out.println("2. Listar Cuidadores ");
            System.out.println("3. Atualizar Cuidador ");
            System.out.println("4. Excluir Cuidador ");
            System.out.println("5. Sair...");
            opcao = sc.nextInt();

            switch (opcao){
                case 1 -> criarNovoCuidador();
                case 2 -> listarCuidador();
                case 3 -> atualizaCuidador();
                case 4 -> deletarCuidador();
                case 5 -> System.out.println("Saindo....");
                default -> System.out.println("Opção invalida, tente novamente");
            }
        }while (opcao != 5);
    }

    public void criarNovoCuidador(){
        System.out.println(" Ola, vamos criar um novo Cuidador");

        System.out.println(" Insira o seu nome ");
        String nome =sc.nextLine();

        System.out.println(" Insira o seu cpf  ");
        String cpf = sc.nextLine();

        System.out.println(" Insira a sua idade");
        int idade = sc.nextInt();

        System.out.println(" Insira o seu email ");
        String email = sc.nextLine();

        System.out.println(" Insira o seu telefone ");
        String telefoneContato = sc.nextLine();

        System.out.println(" Insira o seu correlacaoPaciente ");
        String correlacaoPaciente = sc.nextLine();
/// arrumar
        //cuidadorController.adicionarCuidador(new Cuidador(nome, cpf, idade, email, telefoneContato, correlacaoPaciente));
    }

    private void listarCuidador(){
        List<Cuidador> listarCuidadores = cuidadorController.listarCuidadors();

        if (listarCuidadores.isEmpty()){
            System.out.println(" Nenhum cuidador encontrado ");
        }else{
            System.out.println("=== Lista de cuidadores ===");
            for (Cuidador cuidador : listarCuidadores) {
                System.out.println(cuidador);
            }
        }
    }

    public void atualizaCuidador(){

        System.out.println(" Ola, vamos atualizar um Cuidador");

        System.out.println(" Insira o nome ");
        String nome =sc.nextLine();

        System.out.println(" Insira o cpf  ");
        String cpf = sc.nextLine();

        System.out.println(" Insira a idade");
        int idade = sc.nextInt();

        System.out.println(" Insira o email ");
        String email = sc.nextLine();

        System.out.println(" Insira o telefone ");
        String telefoneContato = sc.nextLine();

        System.out.println(" Insira o correlacaoPaciente ");
        String correlacaoPaciente = sc.nextLine();
/// arrumar
        //cuidadorController.atualizarCuidador(new Cuidador(nome, cpf, idade, email, telefoneContato, correlacaoPaciente));
    }

    public void deletarCuidador(){
        System.out.println("ID do cliente a ser deletado: ");
        int id = sc.nextInt();
        cuidadorController.excluirCuidador(id);
    }

}


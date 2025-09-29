package view;
import controler.PacienteController;
import model.vo.Paciente;
import model.vo.Patologia;

import java.util.List;
import java.util.Scanner;

public class CadastroPaciente {
    private PacienteController pacienteController;
    private  Scanner  sc;

    public CadastroPaciente() {
        this.pacienteController = new PacienteController();
        this.sc =new Scanner(System.in);
    }

    public void menuPaciente() {
        int opcao;
        // CORREÇÃO: O loop agora vai até a opção 6 (Sair).
        do {
            System.out.println("\n| Seja bem-vindo(a)! Escolha uma das opções |");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Atualizar Paciente");
            System.out.println("4. Excluir Paciente");
            System.out.println("5. Ver Próximas Consultas");
            System.out.println("6. Sair...");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> criarNovoPaciente();
                case 2 -> listaPacientes();
                case 3 -> atualizaPaciente();
                case 4 -> deletarPacientes();
                case 5 -> verproximasConsultas();
                case 6 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 6);
    }

    public void criarNovoPaciente() {
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        System.out.println("Olá, vamos Cadastrar um novo paciente");

        System.out.println("\nSelecione a patologia do paciente:");
        List<Patologia> patologiasDisponiveis = pacienteController.listarPatologias();
        for (int i = 0; i < patologiasDisponiveis.size(); i++) {
           System.out.println((i + 1) + patologiasDisponiveis.get(i).getnomePatologia());
       }
       System.out.print("Digite o número da opção: ");
       int escolhaPatologia = sc.nextInt();
       sc.nextLine();

        patologiasDisponiveis.get(escolhaPatologia).getnomePatologia();
       if (escolhaPatologia < 1 || escolhaPatologia > patologiasDisponiveis.size()) {
            System.out.println("Opção inválida.");
        }
        Patologia patologiaEscolhida = patologiasDisponiveis.get(escolhaPatologia - 1);
        System.out.println("Diagnóstico selecionado: " + patologiaEscolhida.getnomePatologia());



        // --- Coleta de Dados do Paciente ---
        System.out.println("\nAgora, os dados do paciente.");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone de Contato: ");
        String telefoneContato = sc.nextLine();


        pacienteController.adicionarPaciente(nome, cpf, idade, email, telefoneContato, patologiaEscolhida);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private void listaPacientes() {
        List<Paciente> listaDePacientes = pacienteController.listarPacientes();
        if (listaDePacientes.isEmpty()) {
            System.out.println("\nNenhum Paciente encontrado.");
        } else {
            System.out.println("\n=== Lista de Pacientes Cadastrados ===");
            for (Paciente paciente : listaDePacientes) {
                System.out.println("Nome: " + paciente.getNome());

                if (paciente.getPatologia() != null && paciente.getPatologia().getnomePatologia() != null) {
                    System.out.println("Patologia: " + paciente.getPatologia().getnomePatologia());
                } else {
                    System.out.println("Patologia: (Não informada)");
                }
                System.out.println("-------------------------");
            }
        }
    }

    public void atualizaPaciente() {

        System.out.println(" Ola, vamos atualizar um paciente");

        System.out.println(" Insira o nome ");
        String nome = sc.nextLine();

        System.out.println(" Insira o cpf  ");
        String cpf = sc.nextLine();

        System.out.println(" Insira a idade");
        int idade = sc.nextInt();

        System.out.println(" Insira o email ");
        String email = sc.nextLine();

        System.out.println(" Insira o telefone ");
        String telefoneContato = sc.nextLine();

        System.out.println("Agora vamos cadastrar o seu endereço");
        System.out.println(" Insira o seu cep ");
        String cep = sc.nextLine();
        System.out.println("Insira o seu logradouro");
        String logradouro = sc.nextLine();
        System.out.println("Insira o numero");
        String numero = sc.nextLine();
        System.out.println("Insira a sua cidade");
        String cidade = sc.nextLine();
        System.out.println("Insira o seu estado");
        String estado = sc.nextLine();

        System.out.println("Agora Insira a sua Patologia");
        String patologiaNome = sc.nextLine();
/// arrumar depois
//        pacienteController.atualizarPaciente(new Paciente(nome, cpf, idade, email, telefoneContato, patologiaEscolhida), new Endereco(cep,logradouro,numero,cidade,estado), new Patologia(patologiaNome));
    }
    public void deletarPacientes(){
        System.out.println("CPD do cliente a ser deletado: ");
         String  cpf = sc.nextLine();
        pacienteController.excluirPaciente(cpf);
        }


    public void  verproximasConsultas(){
        System.out.println("ola: " );
    }

    }




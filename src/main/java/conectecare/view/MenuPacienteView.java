package conectecare.view;

import conectecare.controller.ConsultaController;
import conectecare.controller.PacienteController;
import conectecare.model.dao.PatologiaDao;
import conectecare.model.vo.ConsultaVo;
import conectecare.model.vo.PacienteVo;
import conectecare.model.vo.PatologiaVo;

import java.util.List;
import java.util.Scanner;

public class MenuPacienteView {
    private PacienteController pacienteController;

    private Scanner sc;

    public MenuPacienteView() {
        this.pacienteController = new PacienteController();

        this.sc = new Scanner(System.in);
    }

    public void menuPaciente() {
        int opcao;
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
                case 3 -> atualizarPaciente();
                case 4 -> deletarPacientes();
                case 5 -> verproximasConsultas();
                case 6 -> {
                    System.out.println("Saindo do sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 6);
    }

    public void criarNovoPaciente() {
        System.out.println("\n--- Cadastro de Novo Paciente ---");

        PatologiaDao patologiaDAO = new PatologiaDao();
        List<PatologiaVo> patologiasDisponiveis = patologiaDAO.listarPatologia();

        System.out.println("\nSelecione a patologia:");
        for (int i = 0; i < patologiasDisponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + patologiasDisponiveis.get(i).getnomePatologia());
        }
        System.out.print("Digite o número da opção: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > patologiasDisponiveis.size()) {
            System.out.println("Opção inválida. Cadastro cancelado.");
            return;
        }
        PatologiaVo patologiaEscolhida = patologiasDisponiveis.get(escolha - 1);
        System.out.println("Diagnóstico selecionado: " + patologiaEscolhida.getnomePatologia());


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

    }

    private void listaPacientes() {
        List<PacienteVo> listaDePacientes = pacienteController.listarPacientes();

        if (listaDePacientes.isEmpty()) {
            System.out.println("\nNenhum Paciente encontrado no sistema.");
        } else {
            System.out.println("\n=== Lista de Pacientes Cadastrados ===");
        }
    }


    public void atualizarPaciente() {
        System.out.println("\n--- ATUALIZAR PACIENTE ---");

        List<PacienteVo> pacientes = pacienteController.listarPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        System.out.print("Digite o número do paciente a atualizar: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > pacientes.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        PacienteVo pacienteAntigo = pacientes.get(escolha - 1);
        String cpfInicial = pacienteAntigo.getCpf();

        PatologiaDao patologiaDAO = new PatologiaDao();
        List<PatologiaVo> patologiasDisponiveis = patologiaDAO.listarPatologia();

        System.out.println("\nSelecione a patologia:");
        for (int i = 0; i < patologiasDisponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + patologiasDisponiveis.get(i).getnomePatologia() + "\n");
        }
        System.out.print("Digite o número da opção: ");
        int escolhap = sc.nextInt();
        sc.nextLine(); // Limpa o buffer

        if (escolhap < 1 || escolha > patologiasDisponiveis.size()) {
            System.out.println("Opção inválida. Cadastro cancelado.");
            return;
        }
        PatologiaVo patologiaEscolhida = patologiasDisponiveis.get(escolhap - 1);
        System.out.println("Diagnóstico selecionado: " + patologiaEscolhida.getnomePatologia());


        System.out.println("\nAgora, os dados do paciente.");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        /// cpf continua o mesmo
        String cpf = cpfInicial;

        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Telefone de Contato: ");
        String telefoneContato = sc.nextLine();

        pacienteController.atualizarPaciente(nome, cpf, idade, email, telefoneContato, patologiaEscolhida, cpfInicial);
    }

    public void deletarPacientes() {
        System.out.println("CPf do cliente a ser deletado: ");
        sc.nextLine();
        String cpf = sc.nextLine();
        pacienteController.excluirPaciente(cpf);
    }


    public void verproximasConsultas() {
        System.out.println("\n--- PRÓXIMAS CONSULTAS ---");

        ConsultaController consultaController = new ConsultaController();
        List<ConsultaVo> consultas = consultaController.listarProximasConsultas();

        if (consultas.isEmpty()) {
            System.out.println("Não há consultas agendadas.");
            return;
        }

        System.out.println(" Proximas Consultas: \n");

        for (int i = 0; i < consultas.size(); i++) {
            ConsultaVo c = consultas.get(i);
            System.out.println("   Consulta " + (i + 1) + ":");
            System.out.println("   Paciente: " + c.getNomePaciente());
            System.out.println("   Patologia: " + c.getPatologiaPaciente());
            System.out.println("   Médico: Dr. " + c.getNomeMedico());
            System.out.println("   Especialidade: " + c.getEspecialidadeMedico());
            System.out.println("   Data/Hora: " + (c.getDataHora()));
            System.out.println("   Status: " + c.getStatus());

            if (c.getLinkTelemedicina() != null && !c.getLinkTelemedicina().isEmpty()) {
                System.out.println(" Link: " + c.getLinkTelemedicina());
            }
            System.out.println("   ─────────────────────────");
        }
    }
}

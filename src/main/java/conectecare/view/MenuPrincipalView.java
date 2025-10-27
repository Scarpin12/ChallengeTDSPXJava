package conectecare.view;

import java.util.Scanner;

public class MenuPrincipalView {
    private MenuPacienteView menuPaciente;
    private MenuCuidadorView menuCuidador;
    private Scanner sc;


    public MenuPrincipalView() {
        this.menuPaciente = new MenuPacienteView();
        this.menuCuidador = new MenuCuidadorView();
        this.sc =new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== SISTEMA ConectaCare ===");
            System.out.println("1. Menu Paciente");
            System.out.println("2. Menu Cuidador");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuPaciente.menuPaciente();
                    break;
                case 2:
                    menuCuidador.menuCuidador();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}

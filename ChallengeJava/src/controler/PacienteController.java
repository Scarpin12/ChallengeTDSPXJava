package controler;
import model.bo.PacienteBO;
import model.dao.PacienteDAO;
import model.vo.Paciente;
import model.vo.Patologia;
import java.util.List;

public class PacienteController {
    private PacienteDAO pacienteDAO;
    private PacienteBO pacienteBO;


    public PacienteController() {
        this.pacienteDAO = new PacienteDAO();
        this.pacienteBO = new PacienteBO();
    }

    public void adicionarPaciente(String nome, String cpf, int idade, String email, String telefone, Patologia patologiaEscolhida) { ///String cep, String logradouro, String numero, String cidade, String estado,Patologia patologiaEscolhida
        try {
            pacienteBO.cadastrarNovoPaciente (nome, cpf, idade, email, telefone, patologiaEscolhida);
            System.out.println("Paciente cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("FALHA AO CADASTRAR: " + e.getMessage());
        }
    }


    public List<Paciente> listarPacientes() {
        return pacienteBO.listarTodosPacientes();
    }

    public void atualizarPaciente(String nome, String cpf, int idade, String email, String telefone, Patologia patologiaEscolhida, String cpfValidacao) {
        pacienteBO.atualizacaoCadastro(nome, cpf, idade, email, telefone, patologiaEscolhida, cpfValidacao);
    }

    public void excluirPaciente(String cpf ) {
        pacienteDAO.excluirPaciente(cpf);
    }

}
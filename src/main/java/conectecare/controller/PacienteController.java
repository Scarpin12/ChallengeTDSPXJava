package conectecare.controller;

import conectecare.model.bo.PacienteBo;
import conectecare.model.dao.PacienteDao;
import conectecare.model.vo.PacienteVo;
import conectecare.model.vo.PatologiaVo;

import java.util.List;

public class PacienteController {
    private PacienteDao pacienteDAO;
    private PacienteBo pacienteBO;


    public PacienteController() {
        this.pacienteDAO = new PacienteDao();
        this.pacienteBO = new PacienteBo();
    }

    public void adicionarPaciente(String nome, String cpf, int idade, String email, String telefone, PatologiaVo patologiaEscolhida) { ///String cep, String logradouro, String numero, String cidade, String estado,Patologia patologiaEscolhida
        try {
            pacienteBO.cadastrarNovoPaciente (nome, cpf, idade, email, telefone, patologiaEscolhida);
            System.out.println("Paciente cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("FALHA AO CADASTRAR: " + e.getMessage());
        }
    }


    public List<PacienteVo> listarPacientes() {
        return pacienteBO.listarTodosPacientes();
    }

    public void atualizarPaciente(String nome, String cpf, int idade, String email, String telefone, PatologiaVo patologiaEscolhida, String cpfValidacao) {
        pacienteBO.atualizacaoCadastro(nome, cpf, idade, email, telefone, patologiaEscolhida, cpfValidacao);
    }

    public void excluirPaciente(String cpf ) {
        pacienteDAO.excluirPaciente(cpf);
    }


}

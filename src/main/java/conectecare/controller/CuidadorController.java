package conectecare.controller;

import conectecare.model.bo.CuidadorBo;
import conectecare.model.dao.CuidadorDao;
import conectecare.model.vo.CuidadorVo;

import java.util.List;

public class CuidadorController {
    private CuidadorBo cuidadorBO;
    private CuidadorDao cuidadorDAO;

    public CuidadorController() {
        this.cuidadorBO = new CuidadorBo();
        this.cuidadorDAO = new CuidadorDao();
    }

    public void adicionarCuidador(String nome, String cpfCuidador, int idade, String email, String telefoneContato, String correlacaoPaciente,String cpfPaciente) {
        try {
            cuidadorBO.cadastrarEVincularCuidador(nome, cpfCuidador, idade, email, telefoneContato, correlacaoPaciente,cpfPaciente);
        } catch (Exception e) {
            System.err.println("FALHA AO CADASTRAR CUIDADOR VINCULADO: " + e.getMessage());
        }
    }

    public List<CuidadorVo> listarCuidadores() {
        return cuidadorBO.listarTodosCuidadores();
    }

    public void atualizaCuidador(String nome, String cpf, int idade, String email, String telefone, String correlacaoPaciente,  String cpfValidacao) {
        cuidadorBO.atualizacaoCadastro(nome, cpf, idade, email, telefone, correlacaoPaciente,  cpfValidacao);
    }

    public void excluirCuidador(String cpf){
        cuidadorDAO.excluirCuidador(cpf);
    }
}

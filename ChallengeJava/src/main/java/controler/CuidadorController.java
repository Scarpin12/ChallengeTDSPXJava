package controler;
import model.bo.CuidadorBO;
import model.dao.CuidadorDAO;
import model.vo.Cuidador;
import java.util.List;

public class CuidadorController {
    private CuidadorBO cuidadorBO;
    private CuidadorDAO cuidadorDAO;

    public CuidadorController() {
        this.cuidadorBO = new CuidadorBO();
        this.cuidadorDAO = new CuidadorDAO();
    }

    public void adicionarCuidador(String nome, String cpfCuidador, int idade, String email, String telefoneContato, String correlacaoPaciente,String cpfPaciente) {
        try {
            cuidadorBO.cadastrarEVincularCuidador(nome, cpfCuidador, idade, email, telefoneContato, correlacaoPaciente,cpfPaciente);
        } catch (Exception e) {
            System.err.println("FALHA AO CADASTRAR CUIDADOR VINCULADO: " + e.getMessage());
        }
    }

    public List<Cuidador> listarCuidadores() {
        return cuidadorBO.listarTodosCuidadores();
    }

    public void atualizaCuidador(String nome, String cpf, int idade, String email, String telefone, String correlacaoPaciente,  String cpfValidacao) {
        cuidadorBO.atualizacaoCadastro(nome, cpf, idade, email, telefone, correlacaoPaciente,  cpfValidacao);
    }

    public void excluirCuidador(String cpf){
        cuidadorDAO.excluirCuidador(cpf);
    }

}

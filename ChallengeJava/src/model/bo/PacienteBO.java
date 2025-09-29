package model.bo;
import model.dao.Conexao;
import model.dao.PacienteDAO;
import model.dao.PatologiaDAO;
import model.vo.Paciente;
import model.vo.Patologia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PacienteBO {
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private PatologiaDAO patologiaDAO = new PatologiaDAO();

    public void cadastrarNovoPaciente(String nome, String cpf, int idade, String email, String telefone, Patologia patologiaEscolhida ) throws Exception {

        Connection conn = null;
        try {
            conn = Conexao.getConnection();
            conn.setAutoCommit(false);

            Paciente novoPaciente = new Paciente(nome, cpf, idade, email, telefone, patologiaEscolhida);
            novoPaciente.setPatologia(patologiaEscolhida);

            pacienteDAO.inserirPaciente(novoPaciente, conn );

            conn.commit();

        } catch (Exception e) {
            System.err.println(" Erro ao cadastrar paciente. Desfazendo alterações (rollback)...");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println(" Erro crítico ao tentar executar o rollback: " + ex.getMessage());
                }
            }

            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("BO: Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    public List<Paciente> listarTodosPacientes() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.listarPacientes();
    }

    public List<Patologia> listarTodasPatologias() throws SQLException {
        return patologiaDAO.listarPatologia();
    }
}
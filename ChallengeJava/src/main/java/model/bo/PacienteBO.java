package model.bo;
import Service.Conexao;
import model.dao.PacienteDAO;
import model.vo.Paciente;
import model.vo.Patologia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PacienteBO {
    private PacienteDAO pacienteDAO = new PacienteDAO();

    public void cadastrarNovoPaciente(String nome, String cpf, int idade, String email,
                                      String telefone, Patologia patologiaEscolhida) throws Exception {

        Connection conn = null;
        try {
            conn = Conexao.getConnection();
            conn.setAutoCommit(false);

            Paciente novoPaciente = new Paciente(nome, cpf, idade, email, telefone, patologiaEscolhida);
            novoPaciente.setPatologiaEscolhida(patologiaEscolhida);

            Paciente idPaciente = pacienteDAO.inserirPaciente(novoPaciente, conn);

            ConsultaBO consultaBO = new ConsultaBO();
            consultaBO.cadastrarConsultaAutomatica(idPaciente, patologiaEscolhida, conn);

            conn.commit();
            System.out.println("Paciente cadastrado com sucesso!\n");
            System.out.println("Consulta já agendado com sucesso!\n");

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar paciente. Desfazendo alterações (rollback)...");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erro crítico ao tentar executar o rollback: " + ex.getMessage());
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


    public void atualizacaoCadastro(String nome, String cpf, int idade, String email, String telefone, Patologia patologiaEscolhida, String cpfValidacao) {
        Connection conn = null;
        try {
            conn = Conexao.getConnection();
            conn.setAutoCommit(false);
            Paciente novoAtualizaPaciente = new Paciente(nome, cpf, idade, email, telefone, patologiaEscolhida);
            novoAtualizaPaciente.setPatologiaEscolhida(patologiaEscolhida);

            pacienteDAO.atualizaPaciente(novoAtualizaPaciente, cpfValidacao);

            conn.commit();
            System.out.println("Paciente Atualizado com sucesso!");

        }catch (Exception e) {
            System.err.println("Erro ao atualizar paciente. Desfazendo alterações (rollback)...");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erro crítico ao tentar executar o rollback: " + ex.getMessage());
                }
            }
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



    public List<Paciente> listarTodosPacientes () {
        return pacienteDAO.listarTodosPacientes();
    }
}
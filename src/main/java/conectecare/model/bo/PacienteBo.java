package conectecare.model.bo;

import conectecare.model.dao.PacienteDao;
import conectecare.model.vo.PacienteVo;
import conectecare.model.vo.PatologiaVo;
import conectecare.service.ConexaoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PacienteBo {
    private PacienteDao pacienteDAO = new PacienteDao();

    public void cadastrarNovoPaciente(String nome, String cpf, int idade, String email,
                                      String telefone, PatologiaVo patologiaEscolhida) throws Exception {

        Connection conn = null;
        try {
            conn = ConexaoService.getConnection();
            conn.setAutoCommit(false);

            PacienteVo novoPaciente = new PacienteVo(nome, cpf, idade, email, telefone, patologiaEscolhida);
            novoPaciente.setPatologiaEscolhida(patologiaEscolhida);

            PacienteVo idPaciente = pacienteDAO.inserirPaciente(novoPaciente, conn);

            ConsultaBo consultaBO = new ConsultaBo();
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


    public void atualizacaoCadastro(String nome, String cpf, int idade, String email, String telefone, PatologiaVo patologiaEscolhida, String cpfValidacao) {
        Connection conn = null;
        try {
            conn = ConexaoService.getConnection();
            conn.setAutoCommit(false);
            PacienteVo novoAtualizaPaciente = new PacienteVo(nome, cpf, idade, email, telefone, patologiaEscolhida);
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



    public List<PacienteVo> listarTodosPacientes () {
        return pacienteDAO.listarTodosPacientes();
    }
}

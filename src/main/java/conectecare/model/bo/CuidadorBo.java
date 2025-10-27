package conectecare.model.bo;

import conectecare.model.dao.CuidadorDao;
import conectecare.model.vo.CuidadorVo;
import conectecare.service.ConexaoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CuidadorBo {
    private CuidadorDao cuidadorDAO = new CuidadorDao();

    public void cadastrarEVincularCuidador(String nome, String cpfCuidador, int idade, String email,
                                           String telefoneContato, String correlacaoPaciente, String cpfPaciente) throws Exception {

        Connection conn = null;
        try {
            conn = ConexaoService.getConnection();
            conn.setAutoCommit(false);

            CuidadorVo novoCuidador = new CuidadorVo(nome, cpfCuidador, idade, email, telefoneContato, correlacaoPaciente);
            CuidadorVo cuidadorInserido = cuidadorDAO.inserirCuidador(novoCuidador, conn);

            cuidadorDAO.vincularCuidador(cuidadorInserido.getId(), cpfPaciente, conn);

            conn.commit();
            System.out.println("Cuidador cadastrado e vinculado com sucesso!\n");

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar cuidador. Desfazendo alterações (rollback)...");
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
    public void atualizacaoCadastro(String nome, String cpf, int idade, String email, String telefone, String correlacaoPaciente,  String cpfValidacao) {
        Connection conn = null;
        try {
            conn = ConexaoService.getConnection();
            conn.setAutoCommit(false);
            CuidadorVo novoAtualizaCuidador = new CuidadorVo(nome, cpf, idade, email, telefone, correlacaoPaciente);


            cuidadorDAO.atualizaCuidador(novoAtualizaCuidador, cpfValidacao);

            conn.commit();
            System.out.println("Cuidador Atualizado com sucesso!");

        }catch (Exception e) {
            System.err.println("Erro ao atualizar Cuidador. Desfazendo alterações (rollback)...");
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
    public List<CuidadorVo> listarTodosCuidadores () {
        return cuidadorDAO.listarCuidadoresPacientes();
    }


}


package model.bo;

import model.dao.Conexao;
import model.dao.CuidadorDAO;
import model.vo.Cuidador;
import java.sql.Connection;
import java.sql.SQLException;

public class CuidadorBO {
    private CuidadorDAO cuidadorDAO = new CuidadorDAO();

    public void cadastrarCuidadorAvulso(String nome, String cpf, int idade, String email,
                                        String telefone, String correlacaoPaciente) throws Exception {

        Connection conn = null;
        try {
            conn = Conexao.getConnection();
            conn.setAutoCommit(false);

            Cuidador novoCuidador = new Cuidador(nome, cpf, idade, email, telefone, correlacaoPaciente);

            Cuidador cuidadorInserido = cuidadorDAO.inserirCuidador(novoCuidador, conn);

            conn.commit();
            System.out.println("Cuidador cadastrado com sucesso!\n");

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
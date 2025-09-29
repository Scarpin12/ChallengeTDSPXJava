package model.dao;

import model.vo.Cuidador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuidadorDAO {
    private Connection conn;

    public boolean inserirCuidador(Cuidador cuidador) {
        String sql = "INSERT INTO pessoas VALUES nome, cpf, Idade, email, telefoneContato, correlacaoPaciente) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getCpf());
            ps.setInt(3, cuidador.getIdade());
            ps.setString(4, cuidador.getEmail());
            ps.setString(5, cuidador.getTelefoneContato());
            ps.setString(6, cuidador.getCorrelacaoPaciente());
            ps.execute();
        } catch (SQLException e) {
            if (conn == null) {
                System.err.println("conecção nula ");
            } else {
                System.err.println("erro no preparamed station ");
            }
            e.printStackTrace();
        } finally {
            System.out.println("Fechando conecção com o banco");
        }
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("erro ao fechar conecxão");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean excluirCuidador(int id) {
        /// fazer busca por id para exluir
        String sql = "DELETE FROM PERSONS WHERE id = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("fechando conecxão");
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("não foi pooossivel fechar a conecção");
                e.printStackTrace();
            }

        }
        return true;
    }

    public void atualizaCuidador(Cuidador cuidador) {
        System.out.printf("----- atualizando Cuidador " + cuidador.getNome() + "---");
        String sql = "UPEDATE PERSONS" + "SET nome = ?, idade = ? email = ?, telefoneContato = ?, correlacaoPaciente = ? " + "WHERE cuidadorid = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getCpf());
            ps.setInt(3, cuidador.getIdade());
            ps.setString(4, cuidador.getEmail());
            ps.setString(5, cuidador.getTelefoneContato());
            ps.setString(6, cuidador.getCorrelacaoPaciente());
            ps.execute();
            System.out.print("Cuidador atualizado com sucesso");
        } catch (SQLException e) {
            if (conn == null) {
                System.out.println("conexão nula");
            } else {
                System.out.println(" erro na instrução preparedStatment");
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    System.out.println("fechando conexão");
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Cuidador> listarCuidador() {
        List<Cuidador> cuidadores = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, idade, email, telefoneContato, correlacaoPaciente FROM cuidador";
        try {
            Connection conn = Conexao.getConnection();
            var pstmt = conn.prepareStatement(sql);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = (rs.getInt("id"));
                String nome = (rs.getString("nome"));
                String cpf = (rs.getString("cpf"));
                int idade = (rs.getInt("idade"));
                String email = (rs.getString("email"));
                String telefoneContato = (rs.getString("telefone"));
                String correlacaoPaciente = (rs.getString("correlacaoPaciente"));
                Cuidador cuidador = new Cuidador(id, nome, cpf, idade, email, telefoneContato, correlacaoPaciente);
                cuidadores.add(cuidador);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return cuidadores;
    }



}

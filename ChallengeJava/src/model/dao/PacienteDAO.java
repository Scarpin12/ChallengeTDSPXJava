package model.dao;
import java.sql.Connection;
import model.vo.Paciente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {
    private Connection conn;

    public boolean inserir(Paciente paciente) {
        String sql = "INSERT INTO PERSONS VALUES nome, cpf, Idade, email, telefoneContato, id_patologia, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setInt(3, paciente.getIdade());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getTelefoneContato());
            //ps.setString(6, Patologia.());
            ps.setString(7, paciente.getEndereco());
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

    public boolean excluir(int id) {
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

    public void atualizar(Paciente paciente) {
        System.out.printf("----- atualizando cliente " + paciente.getNome() + "---");
        String sql = "UPEDATE PERSONS" + "SET nome = ?, idade = ? email = ?, telefoneContato = ?, id_patologia, endereco = ?" + "WHERE paciente_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setInt(3, paciente.getIdade());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getTelefoneContato());
            //ps.setString(6, Patologia.());
            ps.setString(7, paciente.getEndereco());
            ps.execute();
            System.out.print("Cliente atualizado com sucesso");
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
}

package model.dao;
import java.sql.*;

import model.vo.Paciente;
import model.vo.Patologia;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

        public Paciente inserirPaciente(Paciente paciente,  Connection conn) throws SQLException {
            String sql = "INSERT INTO pessoas (nome, cpf, idade, email, telefoneContato,  id_patologia, tipo_pessoa) VALUES (?,?, ?, ?, ?,  ?,?)"; ///id_endereco, id_patologia,

            // O try-with-resources gerencia o PreparedStatement.
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, paciente.getNome());
                ps.setString(2, paciente.getCpf());
                ps.setInt(3, paciente.getIdade());
                ps.setString(4, paciente.getEmail());
                ps.setString(5, paciente.getTelefoneContato());
                ps.setInt(6, paciente.getPatologia().getidPatologia());
                ps.setString(7, "PACIENTE");
                ps.executeUpdate();

            }

            return paciente;
        }

    public boolean excluirPaciente(String cpf) {
        /// fazer busca por id para exluir
        String sql = "DELETE FROM PERSONS WHERE cpf = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        try  {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
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

    public void atualizaPaciente(Paciente paciente, Patologia patologia) {
        System.out.printf("----- atualizando Paciente " + paciente.getNome() + "---");
        String sql = "UPEDATE PERSONS" + "SET nome = ?, idade = ? email = ?, telefoneContato = ?,  id_patologia = ?, id_endereco = ?" + "WHERE pacienteid = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try  {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setInt(3, paciente.getIdade());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getTelefoneContato());
            ps.setInt(6, patologia.getidPatologia());

            ps.execute();
            System.out.print("Paciente atualizado com sucesso");
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

    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.cpf, p.idade, p.email, p.telefoneContato, " +
                "e.cidade, " +
                "pat.nomePatologia, pat.id_patologia, e.id_endereco " + // Buscando também os IDs
                "FROM pessoas p " +
                "LEFT JOIN enderecos e ON p.id_endereco = e.id_endereco " +
                "LEFT JOIN patologia pat ON p.id_patologia = pat.id_patologia " +
                "WHERE p.tipo_pessoa = 'PACIENTE'";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Paciente paciente = new Paciente();
                Patologia patologia = new Patologia();

                patologia.setIdPatologia(rs.getInt("id_patologia"));
                patologia.setnomePatologia(rs.getString("nomePatologia"));

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setIdade(rs.getInt("idade")); // Sem espaço no nome da coluna
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefoneContato(rs.getString("telefoneContato"));

                paciente.setPatologia(patologia);

                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            e.printStackTrace();
        }

        return pacientes;
    }

}

package conectecare.model.dao;

import conectecare.model.vo.PacienteVo;
import conectecare.model.vo.PatologiaVo;
import conectecare.service.ConexaoService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    public PacienteVo inserirPaciente(PacienteVo paciente, Connection conn) throws SQLException {
        String sql = "INSERT INTO pessoas (nome, cpf, idade, email, telefoneContato, id_patologia, tipo_pessoa) VALUES (?,?, ?, ?, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setInt(3, paciente.getIdade());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getTelefoneContato());
            ps.setInt(6, paciente.getPatologiaEscolhida().getidPatologia());
            ps.setString(7, "PACIENTE");
            ps.executeUpdate();

            String sqlBuscaId = "SELECT id FROM pessoas WHERE cpf = ? AND tipo_pessoa = 'PACIENTE'";
            try (PreparedStatement psId = conn.prepareStatement(sqlBuscaId)) {
                psId.setString(1, paciente.getCpf());

                try (ResultSet rs = psId.executeQuery()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt("id");
                        paciente.setId(idGerado);
                    } else {
                        new SQLException("Não foi possível encontrar o paciente após inserção");
                    }
                }
            }

            return paciente;
        }
    }

    public boolean excluirPaciente(String cpf) {
        String sqlExcluirConsulta = "DELETE FROM consultas WHERE id_paciente IN (SELECT id FROM pessoas WHERE cpf = ? AND tipo_pessoa = 'PACIENTE')";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConexaoService.getConnection();
            ps = conn.prepareStatement(sqlExcluirConsulta);
            ps.setString(1, cpf);
            int cadastrosExcluidos = ps.executeUpdate();

            if (cadastrosExcluidos > 0){
                System.out.println("Consultas excluidas: " + cadastrosExcluidos);
            }

            String sqlExcluirPaciente = "DELETE FROM pessoas WHERE cpf = ? AND tipo_pessoa = 'PACIENTE'";
            ps = conn.prepareStatement(sqlExcluirPaciente);
            ps.setString(1, cpf);
            int pacienteExcluido = ps.executeUpdate();

            if (pacienteExcluido > 0) {
                System.out.println("Paciente excluído com sucesso!");
                return true;
            } else {
                System.out.println(" Paciente não encontrado");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("fechando conexão");
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("não foi posível fechar a conecção");
                e.printStackTrace();
            }

        }
    }

    public void atualizaPaciente(PacienteVo paciente, String cpfValidacao) {
        System.out.printf("----- atualizando Paciente " + paciente.getNome() + "---");
        String sql = "UPDATE pessoas SET nome = ?, idade = ?, email = ?, telefoneContato = ?, id_patologia = ? WHERE cpf = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConexaoService.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, paciente.getNome());
            ps.setInt(2, paciente.getIdade());
            ps.setString(3, paciente.getEmail());
            ps.setString(4, paciente.getTelefoneContato());
            ps.setInt(5, paciente.getPatologiaEscolhida().getidPatologia());
            ps.setString(6, cpfValidacao);

            int linhasAlteradas = ps.executeUpdate();
            System.out.println(linhasAlteradas + "Paciente atualizado com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<PacienteVo> listarTodosPacientes() {
        List<PacienteVo> pacientes = new ArrayList<>();

        String sql = "SELECT p.id, p.nome, p.cpf, p.idade, p.email, p.telefoneContato, " +
                "pat.id_patologia, pat.nomePatologia " +
                "FROM pessoas p " +
                "LEFT JOIN patologia pat ON p.id_patologia = pat.id_patologia " +
                "WHERE p.tipo_pessoa = 'PACIENTE'";

        try (Connection conn = ConexaoService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            int count = 0;
            while (rs.next()) {
                count++;

                PatologiaVo patologia = new PatologiaVo(
                        rs.getInt("id_patologia"),
                        rs.getString("nomePatologia"));

                PacienteVo paciente = new PacienteVo(

                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"),
                        rs.getString("email"),
                        rs.getString("telefoneContato"),
                        patologia
                );

                pacientes.add(paciente);

                System.out.println( "=================\n" +"Paciente " + ": " + rs.getString("nome") + "\n - Patologia: " +
                        (rs.getString("nomePatologia")  != null ? rs.getString("nomePatologia") : "NULL"));
            }

            System.out.println("Total de pacientes encontrados: " + count);

        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            e.printStackTrace();
        }

        return pacientes;
    }


}


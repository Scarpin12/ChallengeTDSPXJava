package conectecare.model.dao;
import conectecare.model.vo.CuidadorVo;
import conectecare.service.ConexaoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuidadorDao {
    public CuidadorVo inserirCuidador(CuidadorVo cuidador, Connection conn) throws SQLException {
        String sql = "INSERT INTO PESSOAS (NOME, CPF, IDADE, EMAIL, TELEFONECONTATO, " +
                "CORRELACAOPACIENTE, TIPO_PESSOA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getCpf());
            ps.setInt(3, cuidador.getIdade());
            ps.setString(4, cuidador.getEmail());
            ps.setString(5, cuidador.getTelefoneContato());
            ps.setString(6, cuidador.getCorrelacaoPaciente());
            ps.setString(7, "CUIDADOR");

            ps.executeUpdate();

            String sqlBuscaId = "SELECT id FROM pessoas WHERE cpf = ? AND tipo_pessoa = 'CUIDADOR' ";
            try (PreparedStatement psId = conn.prepareStatement(sqlBuscaId)) {
                psId.setString(1, cuidador.getCpf());

                try (ResultSet rs = psId.executeQuery()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt("id");
                        cuidador.setId(idGerado);
                    } else {
                        new SQLException("Não foi possível encontrar o paciente após inserção");
                    }
                }
            }

            return cuidador;
        }
    }

    public boolean vincularCuidador(int idCuidador, String cpfPaciente, Connection conn) throws SQLException {
        String sql = "UPDATE PESSOAS SET ID_CUIDADOR_ASSOCIADO = ? WHERE CPF = ? AND TIPO_PESSOA = 'PACIENTE'";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCuidador);
            ps.setString(2, cpfPaciente);
            ps.executeUpdate();
            return true;
        }
    }



    public boolean excluirCuidador(String cpf) {
        String sqlRemoverVinculos = "UPDATE pessoas SET id_cuidador_associado = NULL WHERE id_cuidador_associado IN (SELECT id FROM pessoas WHERE cpf = ? AND tipo_pessoa = 'CUIDADOR')";

        String sqlExcluirCuidador = "DELETE FROM pessoas WHERE cpf = ? AND tipo_pessoa = 'CUIDADOR'";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoService.getConnection();

            ps = conn.prepareStatement(sqlRemoverVinculos);
            ps.setString(1, cpf);
            int vinculosRemovidos = ps.executeUpdate();

            if (vinculosRemovidos > 0) {
                System.out.println("Vínculos com pacientes removidos: " + vinculosRemovidos);
            }
            ps = conn.prepareStatement(sqlExcluirCuidador);
            ps.setString(1, cpf);
            int cuidadorExcluido = ps.executeUpdate();

            if (cuidadorExcluido > 0) {
                System.out.println("Cuidador excluído com sucesso!");
                return true;
            } else {
                System.out.println("Cuidador não encontrado");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("Fechando conexão");
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Não foi possível fechar a conexão");
                e.printStackTrace();
            }
        }
    }

    public void atualizaCuidador(CuidadorVo cuidador, String cpfValidacao) {
        System.out.printf("----- atualizando Paciente " + cuidador.getNome() + "---");
        String sql = "UPDATE pessoas SET nome = ?, idade = ?, email = ?, telefoneContato = ? WHERE cpf = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConexaoService.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cuidador.getNome());
            ps.setInt(2, cuidador.getIdade());
            ps.setString(3, cuidador.getEmail());
            ps.setString(4, cuidador.getTelefoneContato());
            ps.setString(5, cpfValidacao);

            int linhasAlteradas = ps.executeUpdate();
            System.out.println(linhasAlteradas + "Cuidador atualizado com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Cuidador: " + e.getMessage());
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

    public List<CuidadorVo> listarCuidadoresPacientes() {
        List<CuidadorVo> cuidadores = new ArrayList<>();

        String sql = "SELECT c.id, c.nome, c.cpf, c.idade, c.email, c.telefoneContato, c.CORRELACAOPACIENTE, " +
                "p.nome as nome_paciente, p.cpf as cpf_paciente " +
                "FROM pessoas c " +
                "LEFT JOIN pessoas p ON c.id = p.id_cuidador_associado " +
                "WHERE c.tipo_pessoa = 'CUIDADOR'";

        try (Connection conn = ConexaoService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            int count = 0;
            while (rs.next()) {
                count++;

                CuidadorVo cuidador = new CuidadorVo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"),
                        rs.getString("email"),
                        rs.getString("telefoneContato"),
                        rs.getString("CORRELACAOPACIENTE")
                );

                cuidadores.add(cuidador);

                String pacienteInfo = (rs.getString("nome_paciente") != null) ?
                        "Paciente: " + rs.getString("nome_paciente")  :
                        "Sem paciente vinculado";

                System.out.println("=================\n" +
                        "Cuidador " + count + ": " + rs.getString("nome") +
                        "\n -parentesco: " + rs.getString("CORRELACAOPACIENTE") +
                        "\n - " + pacienteInfo);
            }

            System.out.println("Total de cuidadores encontrados: " + count);

        } catch (SQLException e) {
            System.err.println("Erro ao listar cuidadores: " + e.getMessage());
            e.printStackTrace();
        }

        return cuidadores;
    }

}

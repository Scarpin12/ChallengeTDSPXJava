package conectecare.model.dao;
import conectecare.model.vo.ConsultaVo;
import conectecare.service.ConexaoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao {
    public List<ConsultaVo> listarProximasConsultas() {
        List<ConsultaVo> consultas = new ArrayList<>();

        String sql = "SELECT " +
                "p_paciente.nome AS nome_paciente, " +
                "pat.nomePatologia AS patologia_do_paciente, " +
                "p_medico.nome AS nome_do_medico, " +
                "esp.especialidadeMedico AS especialidade_do_medico, " +
                "c.data_hora AS data_da_consulta, " +
                "c.status AS status_da_consulta, " +
                "c.link_telemedicina " +
                "FROM consultas c " +
                "JOIN pessoas p_paciente ON c.id_paciente = p_paciente.id " +
                "JOIN pessoas p_medico ON c.id_medico = p_medico.id " +
                "JOIN especialidades esp ON p_medico.id_especialidade = esp.id_especialidade " +
                "JOIN patologia pat ON p_paciente.id_patologia = pat.id_patologia " +
                "ORDER BY c.data_hora ASC";

        try (Connection conn = ConexaoService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ConsultaVo consulta = new ConsultaVo(
                        rs.getString("nome_paciente"),
                        rs.getString("patologia_do_paciente"),
                        rs.getString("nome_do_medico"),
                        rs.getString("especialidade_do_medico"),
                        rs.getString("data_da_consulta"),
                        rs.getString("status_da_consulta"),
                        rs.getString("link_telemedicina")
                );
                consultas.add(consulta);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
            e.printStackTrace();
        }
        return consultas;
    }

}

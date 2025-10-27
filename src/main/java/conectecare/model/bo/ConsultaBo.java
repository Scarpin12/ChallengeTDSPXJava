package conectecare.model.bo;

import conectecare.model.dao.ConsultaDao;
import conectecare.model.vo.ConsultaVo;
import conectecare.model.vo.PacienteVo;
import conectecare.model.vo.PatologiaVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConsultaBo {
    private ConsultaDao consultaDAO = new ConsultaDao();

    public List<ConsultaVo> listarProximasConsultas() {
        return consultaDAO.listarProximasConsultas();
    }

    public void cadastrarConsultaAutomatica(PacienteVo paciente, PatologiaVo patologia, Connection conn) throws SQLException {
        int idMedico = MedicoPatologia(patologia.getidPatologia(), conn);

        if (idMedico != -1) {
            String sql = "INSERT INTO consultas (id_paciente, id_medico, data_hora, status, link_telemedicina) " +
                    "VALUES (?, ?, SYSDATE + 7, 'AGENDADA', 'https://meet.google.com/abc-defg-hij')";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, paciente.getId());
                ps.setInt(2, idMedico);
                ps.executeUpdate();
                System.out.println("Consulta  agendada para o paciente!");
            } catch (SQLException e) {
                System.err.println("Erro ao agendar consulta" + e.getMessage());
            }
        }
    }

    private int MedicoPatologia(int idPatologia, Connection conn) throws SQLException {
        String sql = "SELECT pm.id FROM pessoas pm " +
                "JOIN patologias_especialidades pe ON pm.id_especialidade = pe.id_especialidade " +
                "WHERE pe.id_patologia = ? AND pm.tipo_pessoa = 'MEDICO' AND ROWNUM = 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPatologia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int medicoId = rs.getInt("id");
                System.out.println("Médico encontrado: " + " para patologia " + idPatologia);
                return medicoId;
            } else {
                System.out.println(" Nenhum médico encontrado para patologia  " + idPatologia);
                return -1;
            }
        }
    }

}


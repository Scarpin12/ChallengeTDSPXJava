package conectecare.model.dao;

import conectecare.model.vo.PacienteVo;
import conectecare.model.vo.PatologiaVo;
import conectecare.service.ConexaoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatologiaDao {
    private Connection conn;

    public List<PatologiaVo> listarPatologia() {
        List<PatologiaVo> patologias = new ArrayList<>();
        String sql = "SELECT * FROM patologia";
        try {
            Connection conn = ConexaoService.getConnection();
            var pstmt = conn.prepareStatement(sql);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                int idPatologia = (rs.getInt("id_patologia"));
                String nomePatologia = (rs.getString("nomePatologia"));
                String cid10 = (rs.getString("cid10"));
                PatologiaVo patologia = new PatologiaVo(idPatologia, nomePatologia);
                patologias.add(patologia);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar patologias: " + e.getMessage());
            e.printStackTrace();
        }
        return patologias;
    }

}

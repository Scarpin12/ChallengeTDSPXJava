package model.dao;
import Service.Conexao;
import model.vo.Patologia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatologiaDAO {
    private Connection conn;

    public List<Patologia> listarPatologia() {
        List<Patologia> patologias = new ArrayList<>();
        String sql = "SELECT * FROM patologia";
        try {
            Connection conn = Conexao.getConnection();
            var pstmt = conn.prepareStatement(sql);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                int idPatologia = (rs.getInt("id_patologia"));
                String nomePatologia = (rs.getString("nomePatologia"));
                String cid10 = (rs.getString("cid10"));
                Patologia patologia = new Patologia(idPatologia, nomePatologia);
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


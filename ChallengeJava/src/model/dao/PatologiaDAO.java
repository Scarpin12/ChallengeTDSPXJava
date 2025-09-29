package model.dao;
import model.vo.Paciente;
import model.vo.Patologia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                Patologia patologia = new Patologia(idPatologia, nomePatologia, cid10);
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

    public List<Patologia> buscarPorId(int  id)  {
        List<Patologia> patologiasEncontradas = new ArrayList<>();

        // Use o nome exato da sua tabela e da sua chave primária
        // No seu CREATE TABLE, o nome da tabela é 'patologia' (singular).
        String sql = "SELECT * FROM patologia WHERE id_patologia = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // O método agora recebe e usa o ID (um Long)
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patologia patologia = new Patologia();

                    // Use os nomes corretos dos seus métodos Setters
                    patologia.setIdPatologia(rs.getInt("id_patologia"));
                    patologia.setnomePatologia(rs.getString("nomePatologia"));
                    patologia.setCid10(rs.getString("CID10"));

                    patologiasEncontradas.add(patologia);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Não precisa de catch se o método já declara "throws SQLException".

        return patologiasEncontradas;
    }

}


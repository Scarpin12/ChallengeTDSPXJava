package model.dao;
import model.vo.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.vo.Especialidades;

public class MedicoDAO {


    public boolean inserirMedico(Medico medico, Especialidades especialidades, Connection conn) {
        String sql = "INSERT INTO pessoas  VALUES (nome, cpf, Idade, email, telefoneContato, crm, id, especialidadeMedico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getCpf());
            ps.setInt(3, medico.getIdade());
            ps.setString(4, medico.getEmail());
            ps.setString(5, medico.getTelefoneContato());
            ps.setString(6, medico.getCrm());
            ps.setInt(7,especialidades.getId()  );
            ps.setString(8, especialidades.getEspecialidade() );
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

    public List<Medico> listarMedicos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, idade, email, telefoneContato, crm, especialidade  FROM medicos";
        try {
            Connection conn = Conexao.getConnection();
            var pstmt = conn.prepareStatement(sql);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                //Paciente.setId(rs.getInt("id_cliente"));
                int id = (rs.getInt("id"));
                String nome = (rs.getString("nome"));
                String cpf = (rs.getString("cpf"));
                int idade = (rs.getInt("idade"));
                String email = (rs.getString("email"));
                String telefoneContato = (rs.getString("telefone"));
                String crm = (rs.getString("CRM"));
                Especialidades novaEspecialidade = (medico.getEspecialidades());
                medico = new Medico(id, nome, cpf, idade, email, telefoneContato, crm, novaEspecialidade);
                medicos.add(medico);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar Medico: " + e.getMessage());
        }
        return medicos;
    }


}

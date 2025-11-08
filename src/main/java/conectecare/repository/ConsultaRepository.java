package conectecare.repository;

import conectecare.model.Entity.Consulta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ConsultaRepository implements PanacheRepository<Consulta> {


    public List<Consulta> listarConsultasPorPaciente(Integer pacienteId) {
        return find(
                "SELECT c FROM Consulta c " +
                        "JOIN FETCH c.paciente p " +
                        "JOIN FETCH c.medico m " +
                        "LEFT JOIN FETCH p.patologia pat " +
                        "LEFT JOIN FETCH m.especialidade esp " +
                        "WHERE c.paciente.id = ?1 " +
                        "ORDER BY c.dataHora DESC",
                pacienteId
        ).list();
    }



    public List<Consulta> findByMedicoId(Integer medicoId) {
        return list("medico.id", medicoId);
    }

    public List<Consulta> findByPacienteId(Integer pacienteId) {
        return list("paciente.id", pacienteId);
    }
}

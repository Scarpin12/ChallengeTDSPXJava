package conectecare.repository;

import conectecare.model.Entity.Consulta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ConsultaRepository implements PanacheRepository<Consulta> {

    @Inject
    EntityManager entityManager;

    public List<Consulta> listarProximasConsultas() {
        String jpql = "SELECT c FROM Consulta c " +
                "JOIN c.paciente p_paciente " +
                "JOIN c.medico p_medico " +
                "JOIN p_medico.especialidade esp " +
                "JOIN p_paciente.patologia pat " +
                "ORDER BY c.dataHora ASC";

        return entityManager.createQuery(jpql, Consulta.class).getResultList();
    }

    public List<Consulta> findByMedicoId(Integer medicoId) {
        return List.of();
    }

    public List<Consulta> findByPacienteId(Integer pacienteId) {
        return List.of();
    }
}

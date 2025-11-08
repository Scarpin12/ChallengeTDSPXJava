package conectecare.repository;

import conectecare.model.Entity.Consulta;
import conectecare.model.Entity.Medico;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedicoRepository implements PanacheRepository<Medico> {

    @Inject
    ConsultaRepository consultaRepository;  // ← Injeta o repository


    public Optional<Medico> findByCrm(String crm) {
        return find("crm = ?1", crm).firstResultOptional();
    }

    public List<Medico> listarTodosMedicos() {
        return list("ORDER BY nome");
    }

    public List<Medico> findByEspecialidadeId(Integer especialidadeId) {
        return find("especialidade.id = ?1 ORDER BY nome", especialidadeId).list();
    }

    public List<Medico> listarMedicosComEspecialidades() {
        String jpql = "SELECT m FROM Medico m " +
                "LEFT JOIN FETCH m.especialidade " +
                "ORDER BY m.nome";

        return getEntityManager().createQuery(jpql, Medico.class).getResultList();
    }

    public Optional<Medico> findByPatologiaId(Integer patologiaId) {
        String jpql = "SELECT m FROM Medico m " +
                "JOIN m.especialidade e " +
                "JOIN e.patologias p " +
                "WHERE p.id = ?1";

        return find(jpql, patologiaId).firstResultOptional();
    }


}
package conectecare.repository;
import conectecare.model.Entity.Medico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedicoRepository implements PanacheRepository<Medico> {

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
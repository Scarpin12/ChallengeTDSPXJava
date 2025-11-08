package conectecare.repository;

import conectecare.model.Entity.Patologia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PatologiaRepository implements PanacheRepository<Patologia> {

    public List<Patologia> listarPatologia() {
        return listAll();
    }

    public Optional<Patologia> findByNome(String nomePatologia) {
        return find("nomePatologia", nomePatologia).firstResultOptional();
    }

}
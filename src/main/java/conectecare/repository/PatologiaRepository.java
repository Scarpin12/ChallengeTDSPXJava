package conectecare.repository;

import conectecare.model.Entity.Patologia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PatologiaRepository implements PanacheRepository<Patologia> {

    public List<Patologia> listarPatologia() {
        return listAll();
    }

}
package conectecare.service;
import conectecare.model.Entity.Patologia;
import conectecare.repository.PatologiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PatologiaService {

    @Inject
    PatologiaRepository patologiaRepository;

    public List<Patologia> listarPatologia() {
        return patologiaRepository.listarPatologia();
    }

    public Patologia buscarPorId(Integer id) {
        return patologiaRepository.findById(Long.valueOf(id));
    }

    public List<Patologia> listarPatologiaOrdenado() {
        return patologiaRepository.listarPatologia();
    }
}
package conectecare.repository;

import conectecare.model.DTO.CuidadorDto;
import conectecare.model.Entity.Cuidador;
import conectecare.model.Entity.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CuidadorRepository implements PanacheRepository<Cuidador> {

    @Inject
    PacienteRepository pacienteRepository;

    @Transactional
    public boolean vincularCuidador(Integer idCuidador, String cpfPaciente) {

        Cuidador cuidador = findById(Long.valueOf(idCuidador));
        if (cuidador == null) {
            return false;
        }

        Optional<Paciente> pacienteOpt = pacienteRepository.findByCpf(cpfPaciente);
        if (pacienteOpt.isEmpty()) {
            return false;
        }
        Paciente paciente = pacienteOpt.get();
        paciente.setCuidadorAssociado(cuidador);

        pacienteRepository.persist(paciente);

        return true;
    }

    @Transactional
    public boolean excluirCuidador(String cpf) {

        Optional<Cuidador> cuidadorOpt = find("cpfCuidador", cpf).firstResultOptional();

        if (cuidadorOpt.isEmpty()) {
            return false;
        }

        Cuidador cuidador = cuidadorOpt.get();

        List<Paciente> pacientesComEsteCuidador = pacienteRepository.find(
                "cuidadorAssociado.id = ?1", cuidador.getId()
        ).list();

        for (Paciente paciente : pacientesComEsteCuidador) {
            paciente.setCuidadorAssociado(null);
            pacienteRepository.persist(paciente);
        }

        delete(cuidador);

        return true;
    }

    @Transactional
    public void atualizaCuidador(Cuidador cuidador, String cpfValidacao) {

        Optional<Cuidador> cuidadorExistenteOpt = find("cpfCuidador", cpfValidacao)
                .firstResultOptional();

        if (cuidadorExistenteOpt.isPresent()) {
            Cuidador cuidadorExistente = cuidadorExistenteOpt.get();

            // Atualiza os campos
            cuidadorExistente.setNome(cuidador.getNome());
            cuidadorExistente.setIdade(cuidador.getIdade());
            cuidadorExistente.setEmail(cuidador.getEmail());
            cuidadorExistente.setTelefoneContato(cuidador.getTelefoneContato());
            cuidadorExistente.setCorrelacaoPaciente(cuidador.getCorrelacaoPaciente());

            persist(cuidadorExistente);
        }
    }

    public List<Cuidador> listarCuidadoresPacientes() {
        return list("ORDER BY nome");
    }

    public Optional<Cuidador> findByCpf(String cpf) {
        return find("cpfCuidador", cpf).firstResultOptional();
    }
}

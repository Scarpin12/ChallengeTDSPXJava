package conectecare.repository;
import conectecare.model.DTO.PacienteDto;
import conectecare.model.Entity.Paciente;
import conectecare.model.Entity.Patologia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PacienteRepository implements PanacheRepository<Paciente> {

    @Transactional
    public boolean excluirPaciente(String cpfPaciente) {

        Optional<Paciente> pacienteOpt = find("cpfPaciente", cpfPaciente).firstResultOptional();

        if (pacienteOpt.isEmpty()) {
            return false;
        }

        Paciente paciente = pacienteOpt.get();
//
//        long consultasExcluidas = delete("paciente", paciente);
//        if (consultasExcluidas > 0) {
//            System.out.println("Consultas excluídas: " + consultasExcluidas);
//        }
        delete(paciente);

        return true;
    }

    @Transactional
    public void atualizaPaciente(PacienteDto pacienteDto, String cpfValidacao) {

        Optional<Paciente> pacienteExistenteOpt = find("cpfPaciente", cpfValidacao)
                .firstResultOptional();

        if (pacienteExistenteOpt.isPresent()) {
            Paciente pacienteExistente = pacienteExistenteOpt.get();

            pacienteExistente.setNome(pacienteDto.getNome());
            pacienteExistente.setIdade(pacienteDto.getIdade());
            pacienteExistente.setEmail(pacienteDto.getEmail());
            pacienteExistente.setTelefoneContato(pacienteDto.getTelefoneContato());
            if (pacienteDto.getIdPatologia() != null) {
                Patologia patologia = getEntityManager().find(Patologia.class, Integer.valueOf(pacienteDto.getIdPatologia()));
                    if (patologia != null) {
                        pacienteExistente.setPatologia(patologia);
                    }
                }
            persist(pacienteExistente);
        }
    }

    public List<Paciente> listarTodosPacientes() {
        return list("ORDER BY nome");
    }

    public Optional<Paciente> findByCpf(String cpfPaciente) {
        return find("cpfPaciente", cpfPaciente).firstResultOptional();
    }
}
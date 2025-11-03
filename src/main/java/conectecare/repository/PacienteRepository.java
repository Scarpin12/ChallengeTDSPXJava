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
    public boolean excluirPaciente(String cpf) {
        // Busca o paciente pelo CPF
        Optional<Paciente> pacienteOpt = find("cpf", cpf).firstResultOptional();

        if (pacienteOpt.isEmpty()) {
            return false;
        }

        Paciente paciente = pacienteOpt.get();

        // 1. Exclui consultas do paciente (equivalente ao sqlExcluirConsulta)
        long consultasExcluidas = delete("paciente.id = ?1", paciente.getId());
        if (consultasExcluidas > 0) {
            System.out.println("Consultas excluídas: " + consultasExcluidas);
        }

        // 2. Exclui o paciente (equivalente ao sqlExcluirPaciente)
        delete(paciente);

        return true;
    }

    @Transactional
    public void atualizaPaciente(PacienteDto pacienteDto, String cpfValidacao) {
        // Busca o paciente existente pelo CPF de validação
        Optional<Paciente> pacienteExistenteOpt = find("cpf", cpfValidacao)
                .firstResultOptional();

        if (pacienteExistenteOpt.isPresent()) {
            Paciente pacienteExistente = pacienteExistenteOpt.get();

            // Atualiza os campos
            pacienteExistente.setNome(pacienteDto.getNome());
            pacienteExistente.setIdade(pacienteDto.getIdade());
            pacienteExistente.setEmail(pacienteDto.getEmail());
            pacienteExistente.setTelefoneContato(pacienteDto.getTelefone());
            if (pacienteDto.getIdPatologia() != null) {
                Patologia patologia = getEntityManager().find(Patologia.class, Integer.valueOf(pacienteDto.getIdPatologia()));
                    if (patologia != null) {
                        pacienteExistente.setPatologia(patologia);
                    }
                }


            // O persist() faz UPDATE automaticamente quando o objeto já tem ID
            persist(pacienteExistente);
        }
    }

    public List<Paciente> listarTodosPacientes() {
        String jpql = "SELECT p FROM Paciente p " +
                "LEFT JOIN FETCH p.patologia " +
                "ORDER BY p.nome";

        return getEntityManager().createQuery(jpql, Paciente.class).getResultList();
    }

    public Optional<Paciente> findByCpf(String cpf) {
        return find("cpf", cpf).firstResultOptional();
    }
}
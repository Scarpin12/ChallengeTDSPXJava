package conectecare.repository;

import conectecare.model.Entity.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PacienteRepository implements PanacheRepository<Paciente> {

    /**
     * Substitui: PacienteDao.inserirPaciente()
     * O método persist() já vem pronto - faz INSERT automático
     * USO: pacienteRepository.persist(paciente)
     */

    /**
     * Substitui: PacienteDao.excluirPaciente()
     */
    @Transactional
    public boolean excluirPaciente(String cpf) {
        // Busca o paciente pelo CPF
        Optional<Paciente> pacienteOpt = find("cpf = ?1 and tipo = 'PACIENTE'", cpf).firstResultOptional();

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

    /**
     * Substitui: PacienteDao.atualizaPaciente()
     */
    @Transactional
    public void atualizaPaciente(Paciente paciente, String cpfValidacao) {
        // Busca o paciente existente pelo CPF de validação
        Optional<Paciente> pacienteExistenteOpt = find("cpf = ?1 and tipo = 'PACIENTE'", cpfValidacao)
                .firstResultOptional();

        if (pacienteExistenteOpt.isPresent()) {
            Paciente pacienteExistente = pacienteExistenteOpt.get();

            // Atualiza os campos
            pacienteExistente.setNome(paciente.getNome());
            pacienteExistente.setIdade(paciente.getIdade());
            pacienteExistente.setEmail(paciente.getEmail());
            pacienteExistente.setTelefoneContato(paciente.getTelefoneContato());
            pacienteExistente.setPatologia(paciente.getPatologia());

            // O persist() faz UPDATE automaticamente quando o objeto já tem ID
            persist(pacienteExistente);
        }
    }

    /**
     * Substitui: PacienteDao.listarTodosPacientes()
     */
    public List<Paciente> listarTodosPacientes() {
        String jpql = "SELECT p FROM Paciente p " +
                "LEFT JOIN FETCH p.patologia " +
                "ORDER BY p.nome";

        return getEntityManager().createQuery(jpql, Paciente.class).getResultList();
    }

    /**
     * Método auxiliar: busca paciente por CPF
     */
    public Optional<Paciente> findByCpf(String cpf) {
        return find("cpf = ?1 and tipo = 'PACIENTE'", cpf).firstResultOptional();
    }
}
package conectecare.repository;

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
    PacienteRepository pacienteRepository;  // ← Injeta o repository

    @Transactional
    public boolean vincularCuidador(Integer idCuidador, String cpfPaciente) {
        // Busca o cuidador (no próprio repository)
        Cuidador cuidador = findById(Long.valueOf(idCuidador));  // ← Agora funciona
        if (cuidador == null) {
            return false;
        }

        // Busca o paciente pelo CPF (usando o repository injetado)
        Optional<Paciente> pacienteOpt = pacienteRepository.findByCpf(cpfPaciente);
        if (pacienteOpt.isEmpty()) {
            return false;
        }

        // Faz o vínculo
        Paciente paciente = pacienteOpt.get();
        paciente.setCuidadorAssociado(cuidador);

        // Salva usando o repository injetado
        pacienteRepository.persist(paciente);

        return true;
    }

    /**
     * Substitui: CuidadorDao.excluirCuidador()
     */
    @Transactional
    public boolean excluirCuidador(String cpf) {
        // Busca o cuidador pelo CPF
        Optional<Cuidador> cuidadorOpt = find("cpf = ?1 and tipo = 'CUIDADOR'", cpf).firstResultOptional();

        if (cuidadorOpt.isEmpty()) {
            return false;
        }

        Cuidador cuidador = cuidadorOpt.get();

        // 1. Remove vínculos com pacientes (equivalente ao sqlRemoverVinculos)
        List<Paciente> pacientesComEsteCuidador = find(
                "cuidadorAssociado.id = ?1", cuidador.getId()
        ).firstResult();

        for (Paciente paciente : pacientesComEsteCuidador) {
            paciente.setCuidadorAssociado(null);
            pacienteRepository.persist(paciente);
        }

        // 2. Exclui o cuidador (equivalente ao sqlExcluirCuidador)
        delete(cuidador);

        return true;
    }

    /**
     * Substitui: CuidadorDao.atualizaCuidador()
     */
    @Transactional
    public void atualizaCuidador(Cuidador cuidador, String cpfValidacao) {
        // Busca o cuidador existente pelo CPF de validação
        Optional<Cuidador> cuidadorExistenteOpt = find("cpf = ?1 and tipo = 'CUIDADOR'", cpfValidacao)
                .firstResultOptional();

        if (cuidadorExistenteOpt.isPresent()) {
            Cuidador cuidadorExistente = cuidadorExistenteOpt.get();

            // Atualiza os campos
            cuidadorExistente.setNome(cuidador.getNome());
            cuidadorExistente.setIdade(cuidador.getIdade());
            cuidadorExistente.setEmail(cuidador.getEmail());
            cuidadorExistente.setTelefoneContato(cuidador.getTelefoneContato());
            cuidadorExistente.setCorrelacaoPaciente(cuidador.getCorrelacaoPaciente());

            // O persist() faz UPDATE automaticamente quando o objeto já tem ID
            persist(cuidadorExistente);
        }
    }

    /**
     * Substitui: CuidadorDao.listarCuidadoresPacientes()
     */
    public List<Cuidador> listarCuidadoresPacientes() {
        String jpql = "SELECT c FROM Cuidador c " +
                "LEFT JOIN FETCH c.pacienteAssociado " +
                "ORDER BY c.nome";

        return getEntityManager().createQuery(jpql, Cuidador.class).getResultList();
    }

    /**
     * Método auxiliar: busca cuidador por CPF
     */
    public Optional<Cuidador> findByCpf(String cpf) {
        return find("cpf = ?1 and tipo = 'CUIDADOR'", cpf).firstResultOptional();
    }
}

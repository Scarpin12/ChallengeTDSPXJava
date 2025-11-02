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

    /**
     * Substitui: inserirMedico()
     * USO: medicoRepository.persist(medico)
     */

    /**
     * Busca médico por CRM
     */
    public Optional<Medico> findByCrm(String crm) {
        return find("crm = ?1", crm).firstResultOptional();
    }

    /**
     * Busca médico por CPF
     */
    public Optional<Medico> findByCpf(String cpf) {
        return find("cpf = ?1", cpf).firstResultOptional();
    }

    /**
     * Lista todos os médicos ordenados por nome
     */
    public List<Medico> listarTodosMedicos() {
        return list("ORDER BY nome");
    }

    /**
     * Busca médicos por ID da especialidade
     */
    public List<Medico> findByEspecialidadeId(Integer especialidadeId) {
        return find("especialidade.id = ?1 ORDER BY nome", especialidadeId).list();
    }

    /**
     * Busca médicos por nome (busca parcial)
     */
    public List<Medico> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ORDER BY nome", "%" + nome + "%").list();
    }

    /**
     * Verifica se CRM já existe
     */
    public boolean existsByCrm(String crm) {
        return count("crm = ?1", crm) > 0;
    }

    /**
     * Verifica se CPF já existe
     */
    public boolean existsByCpf(String cpf) {
        return count("cpf = ?1", cpf) > 0;
    }

    /**
     * Substitui: MedicoDao.excluirMedico()
     */
    @Transactional
    public boolean excluirMedico(String crm) {
        // Busca o médico pelo CRM
        Optional<Medico> medicoOpt = findByCrm(crm);

        if (medicoOpt.isEmpty()) {
            return false;
        }

        Medico medico = medicoOpt.get();

        // 1. Remove consultas vinculadas (equivalente ao sqlExcluirConsulta)
        List<Consulta> consultasDoMedico = consultaRepository.find("medico.id = ?1", medico.getId()).list();

        for (Consulta consulta : consultasDoMedico) {
            consultaRepository.delete(consulta);
        }

        if (!consultasDoMedico.isEmpty()) {
            System.out.println("Consultas excluídas: " + consultasDoMedico.size());
        }

        // 2. Exclui o médico
        delete(medico);

        System.out.println("Médico excluído com sucesso!");
        return true;
    }

    /**
     * Substitui: MedicoDao.atualizaMedico()
     */
    @Transactional
    public void atualizarMedico(Medico medico, String crmValidacao) {
        // Busca o médico existente pelo CRM de validação
        Optional<Medico> medicoExistenteOpt = findByCrm(crmValidacao);

        if (medicoExistenteOpt.isPresent()) {
            Medico medicoExistente = medicoExistenteOpt.get();

            // Atualiza os campos
            medicoExistente.setNome(medico.getNome());
            medicoExistente.setIdade(medico.getIdade());
            medicoExistente.setEmail(medico.getEmail());
            medicoExistente.setTelefoneContato(medico.getTelefoneContato());
            medicoExistente.setCrm(medico.getCrm());

            // Atualiza a especialidade se foi fornecida
            if (medico.getEspecialidades() != null) {
                medicoExistente.setEspecialidades(medico.getEspecialidades());
            }

            // O persist() faz UPDATE automaticamente quando o objeto já tem ID
            persist(medicoExistente);

            System.out.println("Médico atualizado com sucesso");
        }
    }

    /**
     * Substitui: MedicoDao.listarTodosMedicos()
     */
    public List<Medico> listarMedicosComEspecialidades() {
        String jpql = "SELECT m FROM Medico m " +
                "LEFT JOIN FETCH m.especialidade " +
                "ORDER BY m.nome";

        return getEntityManager().createQuery(jpql, Medico.class).getResultList();
    }

    /**
     * Busca médico que trata uma patologia específica
     */
    public Optional<Medico> findByPatologiaId(Integer patologiaId) {
        String jpql = "SELECT m FROM Medico m " +
                "JOIN m.especialidade e " +
                "JOIN e.patologias p " +
                "WHERE p.id = ?1";

        return find(jpql, patologiaId).firstResultOptional();
    }

    /**
     * Conta total de médicos
     */
    public long contarMedicos() {
        return count();
    }
}
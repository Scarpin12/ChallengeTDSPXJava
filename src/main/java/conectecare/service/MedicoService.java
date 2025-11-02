package conectecare.service;
import conectecare.model.Entity.Medico;
import conectecare.repository.MedicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedicoService {

    @Inject
    MedicoRepository medicoRepository;

    @Transactional
    public void cadastrarMedico(Medico medico) {
        // Valida se CRM já existe
        if (medicoRepository.existsByCrm(medico.getCrm())) {
            throw new RuntimeException("CRM já cadastrado");
        }

        // Valida se CPF já existe
        if (medicoRepository.existsByCpf(medico.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        // Salva o médico (INSERT automático)
        medicoRepository.persist(medico);
        System.out.println("Médico cadastrado com sucesso!");
    }

    public List<Medico> listarTodosMedicos() {
        return medicoRepository.listarTodosMedicos();
    }

    public List<Medico> listarMedicosComEspecialidades() {
        return medicoRepository.listarMedicosComEspecialidades();
    }

    public Medico buscarPorId(Integer id) {
        Medico medico = medicoRepository.findById(Long.valueOf(id));
        if (medico == null) {
            throw new NotFoundException("Médico não encontrado");
        }
        return medico;
    }


    public Medico buscarPorCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com CRM: " + crm));
    }

    public Medico buscarPorCpf(String cpf) {
        return medicoRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com CPF: " + cpf));
    }

    public List<Medico> buscarPorEspecialidade(Integer especialidadeId) {
        return medicoRepository.findByEspecialidadeId(especialidadeId);
    }

    public List<Medico> buscarPorNome(String nome) {
        return medicoRepository.findByNome(nome);
    }

    public Optional<Medico> buscarPorPatologia(Integer patologiaId) {
        return medicoRepository.findByPatologiaId(patologiaId);
    }

    @Transactional
    public void atualizarMedico(Medico medico, String crmValidacao) {
        medicoRepository.atualizarMedico(medico, crmValidacao);
    }

    @Transactional
    public boolean excluirMedico(String crm) {
        return medicoRepository.excluirMedico(crm);
    }


    public boolean crmExiste(String crm) {
        return medicoRepository.existsByCrm(crm);
    }


    public boolean cpfExiste(String cpf) {
        return medicoRepository.existsByCpf(cpf);
    }

    public long contarMedicos() {
        return medicoRepository.contarMedicos();
    }


    public Optional<Medico> buscarMedicoDisponivelParaPatologia(Integer patologiaId) {
        return medicoRepository.findByPatologiaId(patologiaId);
    }
}
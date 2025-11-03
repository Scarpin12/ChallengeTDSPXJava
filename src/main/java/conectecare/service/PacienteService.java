package conectecare.service;
import conectecare.model.DTO.PacienteDto;
import conectecare.model.Entity.Paciente;
import conectecare.model.Entity.Patologia;
import conectecare.repository.PacienteRepository;
import conectecare.repository.PatologiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    PatologiaRepository patologiaRepository;

    @Inject
    ConsultaService consultaService;


    @Transactional
    public Paciente cadastrarNovoPaciente(PacienteDto pacienteDto) {

        // 1. Cria o paciente (equivalente ao novoPaciente no BO)
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDto.getNome());
        paciente.setCpf(pacienteDto.getCpf());
        paciente.setIdade(pacienteDto.getIdade());
        paciente.setEmail(pacienteDto.getEmail());
        paciente.setSenha(pacienteDto.getSenha());
        paciente.setTelefoneContato(pacienteDto.getTelefone());

        // 2. Busca e seta a patologia (equivalente ao patologiaEscolhida)
        if (pacienteDto.getIdPatologia() != null ) {
            Patologia patologia = patologiaRepository.findById(Long.valueOf(pacienteDto.getIdPatologia()));
            if (patologia == null) {
                throw new NotFoundException("Patologia não encontrada");
            }
            paciente.setPatologia(patologia);
        }

        // 3. Salva o paciente (equivalente ao pacienteDAO.inserirPaciente)
        pacienteRepository.persist(paciente);

        // 4. Agenda consulta automática (equivalente ao consultaBO.cadastrarConsultaAutomatica)
        consultaService.cadastrarConsultaAutomatica(paciente, pacienteDto.getIdPatologia() );

        System.out.println("Paciente cadastrado com sucesso!\n");
        System.out.println("Consulta já agendada com sucesso!\n");
        return paciente;
    }


    @Transactional
    public Paciente atualizacaoCadastro(PacienteDto pacienteDto, String cpfValidacao) {

        // Cria objeto paciente com novos dados (equivalente ao novoAtualizaPaciente)
        Paciente pacienteAtualizado = new Paciente();
        pacienteAtualizado.setNome(pacienteDto.getNome());
        pacienteAtualizado.setCpf(pacienteDto.getCpf());
        pacienteAtualizado.setIdade(pacienteDto.getIdade());
        pacienteAtualizado.setEmail(pacienteDto.getEmail());
        pacienteAtualizado.setTelefoneContato(pacienteDto.getTelefone());

        // Busca patologia se informada
        if (pacienteDto.getIdPatologia() != null) {
            Patologia patologia = patologiaRepository.findById(Long.valueOf(pacienteDto.getIdPatologia()));
            pacienteAtualizado.setPatologia(patologia);
        }

        // Atualiza no banco (equivalente ao pacienteDAO.atualizaPaciente)
        pacienteRepository.atualizaPaciente(pacienteDto, cpfValidacao);

        System.out.println("Paciente Atualizado com sucesso!");
        return pacienteAtualizado;
    }

    public List<Paciente> listarTodosPacientes() {
        return pacienteRepository.listarTodosPacientes();
    }


    public Paciente buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));
    }

    @Transactional
    public boolean excluirPaciente(String cpf) {
        return pacienteRepository.excluirPaciente(cpf);
    }
}
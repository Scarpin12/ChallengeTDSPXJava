package conectecare.service;
import conectecare.model.DTO.PacienteDto;
import conectecare.model.Entity.Consulta;
import conectecare.model.Entity.Paciente;
import conectecare.model.Entity.Patologia;
import conectecare.repository.PacienteRepository;
import conectecare.repository.PatologiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;


import java.util.List;

import static io.quarkus.arc.ComponentsProvider.LOG;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    PatologiaRepository patologiaRepository;


    @Transactional
    public Paciente cadastrarNovoPaciente(PacienteDto pacienteDto) {

        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDto.getNome());
        paciente.setCpfPaciente(pacienteDto.getCpfPaciente());
        paciente.setIdade(pacienteDto.getIdade());
        paciente.setEmail(pacienteDto.getEmail());
        paciente.setSenha(pacienteDto.getSenha());
        paciente.setAceitarTermo(pacienteDto.getAceitarTermo());
        paciente.setTelefoneContato(pacienteDto.getTelefoneContato());

        if (pacienteDto.getIdPatologia() != null ) {
            Patologia patologia = patologiaRepository.findById(Long.valueOf(pacienteDto.getIdPatologia()));
            if (patologia == null) {
                throw new NotFoundException("Patologia não encontrada");
            }
            paciente.setPatologia(patologia);
        }

        pacienteRepository.persist(paciente);

        return paciente;
    }


    @Transactional
    public Paciente atualizacaoCadastro(PacienteDto pacienteDto, String cpfValidacao) {

        Paciente pacienteAtualizado = new Paciente();
        pacienteAtualizado.setNome(pacienteDto.getNome());
        pacienteAtualizado.setCpfPaciente(pacienteDto.getCpfPaciente());
        pacienteAtualizado.setIdade(pacienteDto.getIdade());
        pacienteAtualizado.setEmail(pacienteDto.getEmail());
        pacienteAtualizado.setTelefoneContato(pacienteDto.getTelefoneContato());

        if (pacienteDto.getIdPatologia() != null) {
            Patologia patologia = patologiaRepository.findById(Long.valueOf(pacienteDto.getIdPatologia()));
            pacienteAtualizado.setPatologia(patologia);
        }
        pacienteRepository.atualizaPaciente(pacienteDto, cpfValidacao);

        System.out.println("Paciente Atualizado com sucesso!");
        return pacienteAtualizado;
    }

    public List<Paciente> listarTodosPacientes() {
        return pacienteRepository.listarTodosPacientes();
    }


    public Paciente buscarPorEmail(String email) {
        return pacienteRepository.find("email", email).firstResult();
    }

    @Transactional
    public boolean excluirPaciente(String cpf) {
        return pacienteRepository.excluirPaciente(cpf);
    }
}
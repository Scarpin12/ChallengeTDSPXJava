package conectecare.service;

import conectecare.model.DTO.ConsultaDto;
import conectecare.model.Entity.Consulta;
import conectecare.model.Entity.Medico;
import conectecare.model.Entity.Paciente;
import conectecare.repository.ConsultaRepository;
import conectecare.repository.MedicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;

    @Inject
    MedicoRepository medicoRepository;


    public List<ConsultaDto> listarProximasConsultas(Integer pacienteId) {
        return consultaRepository.listarConsultasPorPaciente(pacienteId)
                .stream().map(ConsultaDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Consulta cadastrarConsultaAutomatica(Paciente paciente, Integer idPatologia) {
        Medico medico = buscarMedicoPorPatologia(idPatologia);

        if (medico != null) {
            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            consulta.setMedico(medico);
            consulta.setDataHora(LocalDateTime.parse(String.valueOf(LocalDateTime.now().plusDays(7)))); // 7 dias depois
            consulta.setStatus("AGENDADA");

            // Salva a consulta
            consultaRepository.persist(consulta);

            System.out.println("Consulta agendada para o paciente!");
            return consulta;
        } else {
            System.out.println("Nenhum médico encontrado para patologia " + idPatologia);
        }
        return null;
    }

    private Medico buscarMedicoPorPatologia(Integer idPatologia) {
        return medicoRepository.findByPatologiaId(idPatologia)
                .orElse(null);
    }

    public List<Consulta> listarPorPaciente(Integer pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> listarPorMedico(Integer medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    @Transactional
    public Consulta atualizarConsulta(Integer id, Consulta consultaAtualizada) {
        Consulta consulta = consultaRepository.findById(Long.valueOf(id));
        if (consulta != null) {
            consulta.setDataHora(consultaAtualizada.getDataHora());
            consulta.setStatus(consultaAtualizada.getStatus());
        }
        return consulta;
    }

    @Transactional
    public boolean deletarConsulta(Integer id) {
        return consultaRepository.deleteById(Long.valueOf(id));
    }
}

package conectecare.service;

import conectecare.model.DTO.ConsultaDto;
import conectecare.model.Entity.Consulta;
import conectecare.model.Entity.Medico;
import conectecare.model.Entity.Paciente;
import conectecare.repository.ConsultaRepository;
import conectecare.repository.MedicoRepository;
import conectecare.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;

    @Inject
    MedicoRepository medicoRepository;

    @Inject
    PacienteRepository pacienteRepository;


    @Transactional
    public Consulta cadastrarConsulta(ConsultaDto consultaDto) {
        try {
            Paciente paciente = pacienteRepository.findById(Long.valueOf(consultaDto.getPaciente()));
            if (paciente == null) {
                throw new NotFoundException("Paciente não encontrado com ID: " + consultaDto.getMedico());
            }

            Medico medico = medicoRepository.findById(Long.valueOf(consultaDto.getMedico()));
            if (medico == null) {
                throw new NotFoundException("Médico não encontrado com ID: " + consultaDto.getMedico());
            }

            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            consulta.setMedico(medico);
            consulta.setDataHora(consultaDto.getDataHora());
            consulta.setStatus(consultaDto.getStatus() != null ? consultaDto.getStatus() : "AGENDADA");
            consultaRepository.persist(consulta);

            return consulta;

        } catch (Exception e) {
            System.out.println(" Erro ao cadastrar consulta: " + e.getMessage());

        }
        return null;
    }



    public List<Consulta> listarPorPaciente(Integer pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }


    @Transactional
    public boolean deletarConsulta(Integer id) {
        return consultaRepository.deleteById(Long.valueOf(id));
    }

}
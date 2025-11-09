package conectecare.service;

// imports...

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

    public List<ConsultaDto> listarProximasConsultas(Integer pacienteId) {
        return consultaRepository.listarConsultasPorPaciente(pacienteId)
                .stream().map(ConsultaDto::new)
                .collect(Collectors.toList());
    }

    public Medico buscarMedicoPorPatologia(Integer idPatologia) {
        try {
            System.out.println("Buscando médico para patologia ID: " + idPatologia);
            Medico medico = medicoRepository.find("especialidade.id", idPatologia).firstResult();

            if (medico != null) {
                System.out.println("Médico encontrado: " + medico.getNome());
            } else {
                System.out.println("Nenhum médico encontrado para esta patologia");
            }
            return medico;
        } catch (Exception e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
            return null;
        }
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